package elevatorproject;

//elevator B thread
public class elevatorB implements Runnable{
    char elevatorLabel;              //label for the elevator 'A' or 'B'
    MutEx mutexB = new MutEx();     //MutEx class to be handled by MutEx class
    char status;                   //elevator status 'U' = UP, 'D' = DOWN

    //constructor
    //accepts elevator label and MutEx object
     public elevatorB(char eL, MutEx m){
        elevatorLabel = eL;
        mutexB = m;
    }   

    //run function to let MutEx class handle elevatorB thread
    @Override
    public synchronized void run(){    
        try{
            mutexB.elevatorB();
        }catch(InterruptedException e){}
    }
}
