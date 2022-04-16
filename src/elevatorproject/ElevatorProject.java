
package elevatorproject; 

import java.util.ArrayList;
import java.util.Arrays;



public class ElevatorProject {
    static int MAXPASSENGERS = 3;

    //track passenger total weight and total num of passengers
    int total_weight, num_passengers;

    public static void main(String[] args) {

        // ArrayList<Integer> cArr = new ArrayList<Integer>(Arrays.asList(new Integer[]{4,2})) ;  //test array to store call floors for elevator A
        // ArrayList<Integer> dArr = new ArrayList<Integer>(Arrays.asList(new Integer[]{1,3}));   //test array to store destination floors for elevator A
        
        // ArrayList<Integer> cArrB = new ArrayList<Integer>(Arrays.asList(new Integer[]{4,2})) ; //test array to store call floors for elevator B
        // ArrayList<Integer> dArrB = new ArrayList<Integer>(Arrays.asList(new Integer[]{2,4})) ; //test array to store call floors for elevator B

        // elevatorA eA = new elevatorA(cArr, dArr, 'A');
        // elevatorB eB = new elevatorB(cArrB, dArrB, 'B');
        
        
        //Passengers[] psgs = new Passengers[MAXPASSENGERS]; //array of *3* passengers 

        //create *3* passengers
        // for(int i = 0 ; i < MAXPASSENGERS; i++){
        //  psgs[i] = new Passengers();
        //  psgs[i].setID(i+1);
        //  psgs[i].run();
        // //  System.out.println(psgs[i].ID);
        // //  System.out.println(psgs[i].getCallFloor());
        // //  System.out.println(psgs[i].getDestFloor());
        // //  System.out.println(psgs[i].dest_dir);
        // }

        elevatorController eC = new elevatorController();

        elevatorA eA = new elevatorA('A');
        elevatorA eB = new elevatorA('B');
        Thread elevA = new Thread(eA);
        Thread elevB = new Thread(eB);
        
        elevA.start();
        elevB.start();

    }
    
}
