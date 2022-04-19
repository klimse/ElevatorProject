package elevatorproject;

import java.util.ArrayList;

//direction 0 = down, 1 = up

public class elevatorA extends ElevatorProject implements Runnable
{
    //destination floor, current floor of lift, call from which floor, number of passengers, number of next floor lift is going to
    int destFloor, currFloor, callFloor,passengerCount, nextFloor, dir; 
    boolean isOverweight, isEmpty;
    char elevatorLabel;
    ArrayList<Integer> callArr = super.callArr;  
    ArrayList<Integer> destArr = super.destArr;

    public elevatorA(char eL){
        this.isEmpty = true;
        elevatorLabel = eL;
    }
    
    public elevatorA(ArrayList<Integer> nCallArr, ArrayList<Integer> nDestArr, char eL){
        setCallArr(nCallArr);
        setDestArr(nDestArr);
        this.isEmpty = true;
        elevatorLabel = eL;
    }
    
    //--------------------------get Methods---------------------------------------
    //returns destination floor
    public int getDest(){
        return this.destFloor;
    }
    
    //returns current floor lift is at
    public int getCurr(){
        return this.currFloor;
    }
    
    //returns floor the call is coming from
    public int getCall(){
        return this.callFloor;
    }
    
    //returns number of passengers in the lift
    public int getPassCount(){
        return this.passengerCount;
    }
    
    //returns next call
    public int getNext(){
        return this.nextFloor;
    }
    
    //returns direction the lift is going
    public int getDir(){
        return this.dir;
    }
    
    //returns the lift call array
    public ArrayList<Integer> getCallArr(){
        return callArr;
    }

    //returns the lift destination array
    public ArrayList<Integer> getDestArr(){
        return destArr;
    }
    
    //**************************set Methods***************************************

    //copies array to the call array
    public void setCallArr(ArrayList<Integer> newArr){
        callArr = newArr;
    }

    //copies array to the destination array
    public void setDestArr(ArrayList<Integer> newArr){
        destArr = newArr;
    }

    public void setDest(int dest){
        this.destFloor = dest;
    }
    
    public void setCurr(int curr){
        this.currFloor = curr;
    }
    
    public void setCall(int call){
        this.callFloor = call;
    }
    
    public void setPassCount(int count){
        this.passengerCount = count;
    }
    
    public void setNext(int next){
        this.nextFloor = next;
    }
    
    public void setDir(int direction){
        this.dir = direction;
    }
    
    public void setOverw(boolean status){
        isOverweight = status;
    }
    
    //================FUNCTIONS==================================
    
    @Override
    public synchronized void run(){
        operate();
    }

    //function for elevatorA operation
    public synchronized void operate(){
        setCurr(1); //elevators start at floor 1
        int taskCount = 1;

        //while(super.callArr.isEmpty() == false){  //loop until no more elevatorA calls left
            if(super.bMoving == false){
                super.aMoving = true;
                setCall(super.callArr.get(0));    //always get the first job on the array
                setDest(super.destArr.get(0));

                System.out.println("========Task: " + taskCount +"===========");   //testing to see how many times elevatorA completes an operation
                System.out.println("Elevator: " + this.elevatorLabel);
                System.out.println("Elevator A at: " + getCurr() + " Call coming from " + getCall() + " Destination: " + getDest());
                
                if(getCurr() != getCall())  //if lift is not at call floor
                    this.goCallFloor();

                if(getCall() > getDest()){  //elevator go downwards
                    this.moveDown();
                }else if (getCall() < getDest()){   //elevator go upwards
                    this.moveUp();
                }else{
                    System.out.println("Lift A is at Destination Floor");
                }
            
                taskCount++;
            }else if(super.callArr.isEmpty()){
                return;
            }
            super.aMoving = false;
        //}
        
    }

    //for elevatorA to go to Call Floor
    public synchronized void goCallFloor(){
        int begin = getCurr();  //current floor

        if(begin < getCall()){  //if lift lower than call floor
            for(int i = begin; i <= getCall(); i++){
                try {
                    setCurr(i);
                    System.out.println("Lift A is at floor "+ i);
                    Thread.sleep(1000);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }else if(begin > getCall()){    //if lift is higher than call floor
            for(int i = begin; i >= getCall(); i--){
                try {
                    setCurr(i);
                    System.out.println("Lift A is at floor "+ i);
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
        System.out.println("Lift A picks up passenger at floor "+ getCurr());
        super.callArr.remove(0); 

        //lift proceeds to UPWARDS Destination
        for(int i = getCurr()+1; i <= getDest(); i++){
            try {
                setCurr(i);
                if(getCurr() != getDest())
                 System.out.println("Lift A is at floor "+ i);
                else
                 System.out.println("Passenger alights at floor "+ i);
                Thread.sleep(1000);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        super.destArr.remove(0);
        returnFloor();
       
    }
    
    public synchronized void moveDown(){
        setDir(0); //set direction to down
        System.out.println("Lift A picks up passenger at floor "+ getCurr());
        super.callArr.remove(0); //remove call from array

        //lift proceeds to DOWNWARDS Destination
        for(int i = getCurr()-1; i >= getDest(); i--){
            try {
                setCurr(i);
                if(getCurr() != getDest())
                 System.out.println("Lift A is at floor "+ i);
               else
                 System.out.println("Passenger alights at floor "+ i);
                Thread.sleep(1000);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        super.destArr.remove(0);
        returnFloor();
    }
    
    //return lift to floor 1
    public void returnFloor(){
        if(super.callArr.isEmpty()){ //if no more calls
            System.out.println("No More calls, lift returning to ground floor");

            while(getCurr() != 1){
                try {
                    int i = getCurr();
                    i--;
                    setCurr(i);
                    System.out.println("Lift A is at floor "+ i);  
                    Thread.sleep(1000);                
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    
    public void overrideFloor(int floor){
        destArr.add(0,floor); //add override floor to the head of the array
    }
}
