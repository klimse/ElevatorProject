
package elevatorproject; 

import java.util.ArrayList;
import java.util.Arrays;

public class ElevatorProject {

    public static void main(String[] args) {

        ArrayList<Integer> cArr = new ArrayList<Integer>(Arrays.asList(new Integer[]{4,2})) ;  //test array to store call floors for elevator A
        ArrayList<Integer> dArr = new ArrayList<Integer>(Arrays.asList(new Integer[]{1,3}));   //test array to store destination floors for elevator A
        
        ArrayList<Integer> cArrB = new ArrayList<Integer>(Arrays.asList(new Integer[]{4,2})) ; //test array to store call floors for elevator B
        ArrayList<Integer> dArrB = new ArrayList<Integer>(Arrays.asList(new Integer[]{2,4})) ; //test array to store call floors for elevator B

        elevatorA eA = new elevatorA(cArr, dArr, 'A');
        elevatorB eB = new elevatorB(cArrB, dArrB, 'B');
        eA.start(); //starts elevator A 
        eB.start(); //starts elevator B 
        //System.out.println(eB.isAlive());
    }
    
}
