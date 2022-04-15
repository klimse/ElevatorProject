/**
 * This class handles the delegation of call and destination arrays to the elevators
 * Calculates traversable floors to determine which elevator to pickup passengers to prevent starvation 
 * Also calculates overweight limit 1000kg
 */

package elevatorproject;
import java.util.ArrayList;

public class elevatorController {
    ArrayList<Integer> callArr = new ArrayList<Integer>();  
    ArrayList<Integer> destArr = new ArrayList<Integer>();

    //method to calculate traversable floors
    private void calcTravFloors(elevatorA e){
        int curr = e.getCurr();
    }

    //method to sort the calls
    private void sortCalls(){
        calcTravFloors();
    }
        //calculate traversable, consider overweight limit  
            //pass the calls to the elevator

    

    //check if the elevators are empty

    //method to pass the calls to the elevator after CREATING ELEVATOR THREADS
    public void run(){
        //initiate passengers
        Passengers psg = new Passengers();
        psg.run();

        //assigns call and dest arrays generated from psg class
        callArr = psg.getCallArr();
        destArr = psg.getDestArr();

        //sort calls to new arrays
        sortCalls();

        //assign to the elevators
        elevatorA eA = new elevatorA();
        elevatorB eB = new elevatorB();
        Thread elevatorA = new Thread(eA);
        Thread elevatorB = new Thread(eB);

        do{
            
            //assign the current floor, let the individual elevator calculate if they are closest the floor, return to the class
            //loop assign curr floor and dest floor

        }while(!destArr.isEmpty());

    }

}
