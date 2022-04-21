package elevatorproject;

public class elevatorB implements Runnable{
    char elevatorLabel;
    MutEx mutexB = new MutEx();

     public elevatorB(char eL, MutEx m){
        elevatorLabel = eL;
        mutexB = m;
    }   
    @Override
    public synchronized void run(){    
        try{
            mutexB.elevatorB();
        }catch(InterruptedException e){}
    }

    
   
    

}
