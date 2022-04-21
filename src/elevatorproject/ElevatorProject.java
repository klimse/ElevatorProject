
package elevatorproject; 

import java.util.ArrayList;

public class ElevatorProject {
    static int MAXPASSENGERS = 3;

    //track passenger total weight and total num of passengers
    int total_weight, num_passengers;
    static ArrayList<Integer> callArr;  //test array to store call floors for elevator A
    static ArrayList<Integer> destArr;   //test array to store destination floors for elevator A
    //boolean aMoving = true, bMoving = false;
    boolean flag = false;
    int taskCount = 1;
 
    public static void main(String[] args) {

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
        //setArr();   //setting call and dest arrays

        MutEx mutex = new MutEx();

        elevatorA eA = new elevatorA('A', mutex);
        elevatorB eB = new elevatorB('B', mutex);
        testingclass testing = new testingclass(mutex);
        Thread elevA = new Thread(eA);
        Thread elevB = new Thread(eB);
        elevA.setName("ElevatorA");
        elevB.setName("ElevatorB");

        testing.start();
 
        elevA.start();
        elevB.start();


    }
    
    // //to set values for dest and call arrays
    // public static void setArr(){
    //     testingclass test = new testingclass();
    //     callArr = test.getCallArr();
    //     destArr = test.getDestArr();
    // }
    
}
