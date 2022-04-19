/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package elevatorproject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Kathryn
 */
public class testingclass extends ElevatorProject {
    
    static ArrayList<Integer> callArr = new ArrayList<Integer>(Arrays.asList(new Integer[]{4,2,3}));  //test array to store call floors for elevator A
    static ArrayList<Integer> destArr = new ArrayList<Integer>(Arrays.asList(new Integer[]{1,3,2}));   //test array to store destination floors for elevator A
    
    public static ArrayList<Integer> getCallArr(){
        return callArr;
    }
    
     public static ArrayList<Integer> getDestArr(){
        return destArr;
    }
}
