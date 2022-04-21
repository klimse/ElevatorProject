package elevatorproject;

import java.util.ArrayList;


//direction 0 = down, 1 = up

public class elevatorB extends ElevatorProject implements Runnable{
    //destination floor, current floor of lift, call from which floor, number of passengers, number of next floor lift is going to
    int destFloor, currFloor, callFloor,passengerCount, nextFloor, dir; 
    boolean isOverweight;
    char elevatorLabel;
    ArrayList<Integer> callArr = super.callArr;  
    ArrayList<Integer> destArr = super.destArr;
    MutEx mutexB = new MutEx();

     public elevatorB(char eL, MutEx m){
        elevatorLabel = eL;
        mutexB = m;
    }
    
    public elevatorB(ArrayList<Integer> nCallArr, ArrayList<Integer> nDestArr, char eL){
        setCallArr(nCallArr);
        setDestArr(nDestArr);
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

    //copies array to the destination
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
        try{
            mutexB.elevatorB();
        }catch(InterruptedException e){}
    }

    
   
    

}
