/**
 * This class handles the delegation of call and destination arrays to the elevators
 * Calculates traversable floors to determine which elevator to pickup passengers to prevent starvation 
 * Also calculates overweight limit 1000kg
 */

package elevatorproject;
import java.util.ArrayList;
import java.util.Arrays;

public class elevatorController {
    ArrayList<Integer> callArr = new ArrayList<Integer>(Arrays.asList(new Integer[]{4,2,3})) ;  //test array to store call floors for elevator A
    ArrayList<Integer> destArr = new ArrayList<Integer>(Arrays.asList(new Integer[]{1,3,2}));   //test array to store destination floors for elevator A
    
    ArrayList<Integer> callArrB = new ArrayList<Integer>(Arrays.asList(new Integer[]{4,2,1})) ; //test array to store call floors for elevator B
    ArrayList<Integer> destArrB = new ArrayList<Integer>(Arrays.asList(new Integer[]{2,4,3})) ; //test array to store call floors for elevator B

    //method to calculate traversable floors
    private void calcTravFloors(){
        //int curr = e.getCurr();
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

        //sort calls to new arrays
       // sortCalls();

        elevatorA eA = new elevatorA('A');
        elevatorB eB = new elevatorB('B');
        Thread elevA = new Thread(eA);
        Thread elevB = new Thread(eB);
        
        elevA.start();
        elevB.start();

        do{
            //if statement to check for closest floors curr
            
            //assign the current floor, let the individual elevator calculate if they are closest the floor, return to the class
            //loop assign curr floor and dest floor

        }while(!destArr.isEmpty());

       // return elevator to floor 1
    }

}
