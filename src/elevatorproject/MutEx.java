package elevatorproject;

import java.util.ArrayList;

public class MutEx {
    boolean flag = true;
    int taskCount = 1;
    ArrayList<Integer> callArr;
    ArrayList<Integer> destArr;
    ArrayList<String> passArr;
    int currA = 1, currB = 1, currFloor;
    char elevLabel;

    public synchronized void elevatorA() throws InterruptedException{
    
        while(!callArr.isEmpty()){
            while(flag == false)
            {
                wait();
                Thread.sleep(1000);    
            }

            flag = true;    //for elevatorB to wait
            operate();
            notify();
            //System.out.print("Elevator A done\n");
        }
    }

    public synchronized void elevatorB() throws InterruptedException{
        while(!callArr.isEmpty()){
            while(flag == true){
                wait();
                Thread.sleep(1000);    
            }
            flag = false; //for elevatorA to wait
            operate();
            notify();
            //System.out.print("Elevator B done\n");
        }
    }

    public void getArray(ArrayList<Integer> nCallArr, ArrayList<Integer> nDestArr, ArrayList<String> nPassArr){
        this.callArr = nCallArr;
        this.destArr = nDestArr;
        this.passArr = nPassArr;

        //testing
        // for(int i = 0; i < callArr.size(); i++){
        //     System.out.println("Passenger calls from: "+ callArr.get(i));
        //     System.out.println("Passenger destination to: "+ destArr.get(i));
        // }
    }

    public synchronized void operate(){
        if(this.callArr.isEmpty()){
            return; //ends function if no more calls
        }
        //check for which elevator is assigned to fetch passenger
        if(Thread.currentThread().getName() == "ElevatorA"){
            this.currFloor = currA;
            elevLabel = 'A';
        }else if(Thread.currentThread().getName() == "ElevatorB"){
            this.currFloor = currB;
            elevLabel = 'B';
        }

        System.out.println("===========Task: " + this.taskCount +"==============");   //how many times elevator completed call -> destination
        System.out.println(Thread.currentThread().getName() +" at: " + this.currFloor + 
                            "\nCall coming from floor: " + this.callArr.get(0) + 
                            "\nDestination floor: " + this.destArr.get(0));
        
        if(currFloor!= this.callArr.get(0))  //if lift is not at call floor
            goCallFloor();
        if(this.callArr.get(0) > this.destArr.get(0)){  //elevator goes downwards
            moveDown();
        }else if (this.callArr.get(0) < this.destArr.get(0)){   //elevator goes upwards
            moveUp();
        }else{
            System.out.println(Thread.currentThread().getName() + " is at Destination Floor");
        }
        passArr.remove(0);

        this.taskCount++;

        if(Thread.currentThread().getName() == "ElevatorA"){
            currA = this.currFloor;
        }else if(Thread.currentThread().getName() == "ElevatorB"){
            currB = this.currFloor;
        }

        this.flag = !this.flag; //change flag to allow other elevator to operate  
    }

    
    public synchronized void goCallFloor(){
        int begin = currFloor;  //current floor

        if(begin < this.callArr.get(0)){  //if lift lower than call floor
            for(int i = begin; i <= this.callArr.get(0); i++){
                try {
                    this.currFloor = i;
                    System.out.println("Lift " + elevLabel +" is at floor "+ i);
                    Thread.sleep(1000);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }else if(begin > this.callArr.get(0)){    //if lift is higher than call floor
            for(int i = begin; i >= this.callArr.get(0); i--){
                try {
                    this.currFloor = i;
                    System.out.println("Elevator "+ elevLabel  + " is at floor "+ i);
                    Thread.sleep(1000);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
    
    //processes elevatorA UP operations (when destination is above call floor)
    public synchronized void moveUp(){
        //System.out.println("Lift "+ elevLabel +" picks up passenger at floor "+ this.currFloor);
        System.out.println("Elevator "+ elevLabel +" picks up "+ passArr.get(0) +" at floor "+ this.currFloor);
        this.callArr.remove(0); 

        //lift proceeds to UPWARDS Destination
        for(int i = currFloor+1; i <= this.destArr.get(0); i++){
            try {
                this.currFloor = i;
                if(this.currFloor != this.destArr.get(0))
                 System.out.println("Elevator "+ elevLabel +" is at floor "+ i);
                else
                 System.out.println(passArr.get(0) +" alights at floor "+ i);
                Thread.sleep(1000);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        this.destArr.remove(0);
        returnFloor();
       
    }
    
    public synchronized void moveDown(){
        //System.out.println("Lift "+ elevLabel +" picks up passenger at floor "+ this.currFloor);
        System.out.println("Elevator "+ elevLabel +" picks up "+ passArr.get(0) +" at floor "+ this.currFloor);
        this.callArr.remove(0); //remove call from array

        //lift proceeds to DOWNWARDS Destination
        for(int i = this.currFloor-1; i >= this.destArr.get(0); i--){
            try {
                this.currFloor = i;
                if(this.currFloor != this.destArr.get(0))
                 System.out.println("Lift "+ elevLabel +" is at floor "+ i);
               else
                 System.out.println(passArr.get(0) +" alights at floor "+ i);
                Thread.sleep(1000);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        this.destArr.remove(0);
        returnFloor();
    }
    
    //return lift to floor 1
    public void returnFloor(){
        if(this.callArr.isEmpty()){ //if no more calls
            System.out.println("No More calls, lift returning to ground floor");

            while(currFloor != 1){
                try {
                    int i = this.currFloor;
                    i--;
                    currFloor = i;
                    System.out.println("Lift "+ elevLabel +" is at floor "+ i);  
                    Thread.sleep(1000);                
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
