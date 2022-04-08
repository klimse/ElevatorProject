
package elevatorproject; 

import java.util.ArrayList;
import java.util.Arrays;

public class ElevatorProject {

    public static void main(String[] args) {

        ArrayList<Integer> cArr = new ArrayList<Integer>(Arrays.asList(new Integer[]{4,2,3,1})) ;  //test array to store call floors
        ArrayList<Integer> dArr = new ArrayList<Integer>(Arrays.asList(new Integer[]{1,3,2,4}));   //test array to store destination floors

        elevator eA = new elevator(cArr, dArr, 'A');
        elevator eB = new elevator(cArr, dArr, 'B');
        eA.start(); //starts elevator A
        eB.start(); //starts elevetor B
    }
    
}
