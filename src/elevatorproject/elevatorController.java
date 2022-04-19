/**
 * This class handles the delegation of call and destination arrays to the elevators
 * Calculates traversable floors to determine which elevator to pickup passengers to prevent starvation 
 * Also calculates overweight limit 1000kg
 */

package elevatorproject;
import java.util.ArrayList;
import java.util.Arrays;

public class elevatorController {

    boolean turnOne = true;
    
    public elevatorController(ArrayList<Integer> ncallArr, ArrayList<Integer> ndestArr){
        this.callArr = ncallArr;
        this.destArr = ndestArr;
    }

    public void run(){
        elevatorA eA = new elevatorA('A');
        elevatorB eB = new elevatorB('B');
        Thread elevA = new Thread(eA);
        Thread elevB = new Thread(eB);
        
        elevA.start();
        elevB.start();
    }

    public synchronized void One() throws InterruptedException{
        while(!turnOne){
            System.out.println("Thread elevA is waiting");
            wait();
        }
        //turn give to elevA
        elevA.operate();
        Thread.sleep(1500);
        turnOne = false;
        notify();
    }


    public synchronized void Two() throws InterruptedException{
        while(turnOne){
            System.out.println("ElevB is waiting");
            wait();
        }
        //gives turn to elevB
        elevB.operate();
        Thread.sleep(1500);
        turnOne = true;
        notify();
    }

}

// public class MutEx{
//     boolean turnOne = true;
//     elevatorA elevA;
//     elevatorB elevB;

//     public MutEx(elevatorA ea, elevatorB eb){
//         elevA = ea;
//         elevB = eb;
//     }

//     public synchronized void One() throws InterruptedException{
//         while(!turnOne){
//             System.out.println("Thread elevA is waiting");
//             wait();
//         }
//         //turn give to elevA
//         elevA.operate();
//         Thread.sleep(1500);
//         turnOne = false;
//         notify();
//     }


//     public synchronized void Two() throws InterruptedException{
//         while(turnOne){
//             System.out.println("ElevB is waiting");
//             wait();
//         }
//         //gives turn to elevB
//         elevB.operate();
//         Thread.sleep(1500);
//         turnOne = true;
//         notify();
//     }

    
// }
