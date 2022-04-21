
package elevatorproject; 

public class ElevatorProject {
    public static void main(String[] args) {    
        MutEx mutex = new MutEx();
        Passengers psg = new Passengers(mutex);
        psg.run();

        elevatorA eA = new elevatorA('A', mutex);
        elevatorB eB = new elevatorB('B', mutex);

        Thread elevA = new Thread(eA);
        Thread elevB = new Thread(eB);
        elevA.setName("ElevatorA");
        elevB.setName("ElevatorB");
        
        elevA.start();
        elevB.start();
    }

}
