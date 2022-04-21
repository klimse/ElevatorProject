package elevatorproject;

import java.util.ArrayList;

//MutEx class handles Elevator A and B takes turns to handle calls.
public class MutEx {
    boolean flag = true;                    //flag to lock and unlock Elevator A and B threads
    int taskCount = 1;                      //number of tasks done by the elevators
    ArrayList<Integer> callArr;             //array containing call floors
    ArrayList<Integer> destArr;             //array containing destination floors
    ArrayList<String> passArr;              //array containing passenger names
    int currA = 1, currB = 1, currFloor;    //current floors for elevator A and B
    char elevLabel, status;                 //elevator label A or B, and elevator status 'U' = UP, 'D' = DOWN

    //elevator A operation, runs when flag == true
    public synchronized void elevatorA() throws InterruptedException{
        while(!callArr.isEmpty()){
            while(flag == false)    //if flag == false, elevatorA is locked
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

    //elevator B operation, runs when flag == false
    public synchronized void elevatorB() throws InterruptedException{
        while(!callArr.isEmpty()){
            while(flag == true){    //if flag == true, elevatorB is locked
                wait();
                Thread.sleep(1000);    
            }
            flag = false; //for elevatorA to wait
            operate();
            notify();
            //System.out.print("Elevator B done\n");
        }
    }

    //assigns call, destination and passenger arrays to MutEx class' local arrays.
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

    //main operation function for elevators
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

    //elevator traverses to go to the current call floor
    public synchronized void goCallFloor(){
        int begin = currFloor;  //current floor

        if(begin < this.callArr.get(0)){  //if lift lower than call floor
            status = 'U'; 
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
            status = 'D';
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
    
    //elevator UP operations (when destination is above call floor)
    public synchronized void moveUp(){
        status = 'U';       
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
    
    //elevator DOWN operation (when destination is below the call floor)
    public synchronized void moveDown(){
        status = 'D';
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
