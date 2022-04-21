package elevatorproject;

public class elevatorA implements Runnable{
    char elevatorLabel;             //label for the elevator 'A' or 'B'
    MutEx mutexA = new MutEx();    //MutEx class to be handled by MutEx class
    char status;                  //elevator status 'U' = UP, 'D' = DOWN

    //constructor
    //accepts elevator label and MutEx object
    public elevatorA(char eL, MutEx m){
        elevatorLabel = eL;
        mutexA = m;
    }
    
    //run function to let MutEx class handle elevatorA thread
    @Override
    public synchronized void run(){
        try{
            mutexA.elevatorA();
        }catch(InterruptedException e){}
    }
}
