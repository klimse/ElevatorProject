package elevatorproject;

import java.util.ArrayList;

public class MutEx {
    boolean flag = true;
    int taskCount = 0;
    ArrayList<Integer> callArr;
    ArrayList<Integer> destArr;

    public synchronized void elevatorA() throws InterruptedException
    {
        while(flag == false)
        {
            wait();
        }
        
        flag = true;
    }

    public synchronized void elevatorB() throws InterruptedException
    {
        while(flag == true){
            wait();
        }
    }

    public void getArray(ArrayList<Integer> nCallArr, ArrayList<Integer> nDestArr){
        this.callArr = nCallArr;
        this.destArr = nDestArr;

        for(int i = 0; i < callArr.size(); i++){
            System.out.println("Passenger calls from: "+ callArr.get(i));
            System.out.println("Passenger destination to: "+ destArr.get(i));
        }
    }

    public synchronized void operate(){
        
        if(this.callArr.isEmpty()){
            return;
        }
       // while(this.callArr.isEmpty() == false){  //loop until no more elevatorA calls left
        // if(this.flag==true){
        //     try{
        //     wait();
        //     }catch(InterruptedException e){}
        // } else{ 
            while(!this.callArr.isEmpty()){
                    System.out.println("========Task: " + this.taskCount +"===========");   //testing to see how many times elevatorA completes an operation
                    System.out.println("Elevator: " + Thread.currentThread().getName());
                    System.out.println("Elevator" + Thread.currentThread().getName() +"at: " + getCurr() + " Call coming from " + this.callArr.get(0) + " Destination: " + this.destArr.get(0));
                    
                    if(getCurr() != getCall())  //if lift is not at call floor
                        goCallFloor();

                    if(getCall() > getDest()){  //elevator go downwards
                        moveDown();
                    }else if (getCall() < getDest()){   //elevator go upwards
                        moveUp();
                    }else{
                        System.out.println(Thread.currentThread().getName() + " is at Destination Floor");
                    }
                
                    this.taskCount++;
                    this.flag = !this.flag;
                    notify();
                }
       // }
            
      //  }
        
    }

    
    public synchronized void goCallFloor(){
        int begin = getCurr();  //current floor

        if(begin < getCall()){  //if lift lower than call floor
            for(int i = begin; i <= getCall(); i++){
                try {
                    setCurr(i);
                    System.out.println("Lift B is at floor "+ i);
                    Thread.sleep(1000);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }else if(begin > getCall()){    //if lift is higher than call floor
            for(int i = begin; i >= getCall(); i--){
                try {
                    setCurr(i);
                    System.out.println("Lift B is at floor "+ i);
                    Thread.sleep(1000);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
    
    //processes elevatorA UP operations (when destination is above call floor)
    public synchronized void moveUp(){
        setDir(1); //set direction to up
        System.out.println("Lift B picks up passenger at floor "+ getCurr());
        this.callArr.remove(0); 

        //lift proceeds to UPWARDS Destination
        for(int i = getCurr()+1; i <= getDest(); i++){
            try {
                setCurr(i);
                if(getCurr() != getDest())
                 System.out.println("Lift B is at floor "+ i);
                else
                 System.out.println("Passenger alights at floor "+ i);
                Thread.sleep(1000);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        this.destArr.remove(0);
        returnFloor();
       
    }
    
    public synchronized void moveDown(){
        setDir(0); //set direction to down
        System.out.println("Lift B picks up passenger at floor "+ getCurr());
        this.callArr.remove(0); //remove call from array

        //lift proceeds to DOWNWARDS Destination
        for(int i = getCurr()-1; i >= getDest(); i--){
            try {
                setCurr(i);
                if(getCurr() != getDest())
                 System.out.println("Lift B is at floor "+ i);
               else
                 System.out.println("Passenger alights at floor "+ i);
                Thread.sleep(500);
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

            while(getCurr() != 1){
                try {
                    int i = getCurr();
                    i--;
                    setCurr(i);
                    System.out.println("Lift B is at floor "+ i);  
                    Thread.sleep(1000);                
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
