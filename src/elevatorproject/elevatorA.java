package elevatorproject;

public class elevatorA implements Runnable{

    char elevatorLabel;
    MutEx mutexA = new MutEx();

    public elevatorA(char eL, MutEx m){
        elevatorLabel = eL;
        mutexA = m;
    }
      
    @Override
    public synchronized void run(){
        try{
            mutexA.elevatorA();
        }catch(InterruptedException e){}
    }
}
