package elevatorproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Passengers extends Thread
{
    private int weight_passenger, weight_equip, total_weight, num_passengers, call_floor, dest_floor;
    private int dest_dir; //0 go down // 1 go up
    private boolean hospbed, hospequip;
    private int ID;
    
    //from class elevatorA
    private int destFloor, currFloor, callFloor,passengerCount, nextFloor, dir;
    private boolean isOverweight = false, isMoving, isStuck, isSurgeon, isEmpty;
 
    
    //declare an arraylist for floor numbers
    private ArrayList<Integer> floors = new ArrayList<>(Arrays.asList(4,2,3,1));
    private ArrayList<Integer> destArr = new ArrayList<>(); //araylist for destination floors
    private ArrayList<Integer> callArr = new ArrayList<>(); //arraylist for call floors
    private ArrayList<Integer> dest_dirArr = new ArrayList<>(); //arraylist for destination direction for each passenger

    private int index = (int)(Math.random()*floors.size());
    
    //int[] floors = {1,2,3,4};
    //String[] directions = {"U", "D"};
     private int directions[] = {0,1};

    
    //constructor
    public Passengers()
    {
        System.out.println("Passengers class is created.");
    }
    
    //=================================== FUNCTIONS ===================================//
    
    //pick a random 'call coming from' floor from arraylist
    public int randomCallFloor() 
    {
        call_floor = new Random().nextInt(floors.get(index));
                
        return call_floor;
    }
    
    //pcik a random 'destination' floor from arraylist
    public int randomDestFloor()
    {   
        //detail : destination floor cannot be the same as call floor
        do
        {
            dest_floor = new Random().nextInt(floors.get(index));
        } while (dest_floor == call_floor);
        
        return dest_floor;
    }

    //set passenger ID 
    public void setID(int id){
        this.ID = id;
    }

    //get passenger ID
    public int getID(){
        return this.ID;
    }

    public ArrayList<Integer> getDestArr(){
        return destArr;
    }

    public ArrayList<Integer> getCallArr(){
        return callArr;
    }

    public boolean isOverweight(){
        return isOverweight;
    }


    //OLD OLD OLD OLD OLD OLD OLD OLD OLD OLD OLD OLD OLD OLD OLD OLD OLD OLD OLD OLD OLD OLD OLD OLD OLD OLD OLD//
    
    public void run()
    {
        Random ran = new Random(); //creates a random class
        
        //random boolean - is there a hospital bed?     
        hospbed = ran.nextBoolean();
        if (hospbed == true)
        {
            System.out.println("\nThere is a hospital bed.\nAdded 600kg.");
            total_weight += 600;
            System.out.println("TOTAL WEIGHT = " + total_weight + " kg");
        }
        
        //random boolean - is there hospital equipment?
        hospequip = ran.nextBoolean();
        if (hospequip == true)
        {
            //randomly generate weight of hosiptal equipment
            //between 10 - 30 kg
            //max - min = 30 - 10 = 20
            weight_equip = 10 + (int)(Math.random()*20);
            System.out.println("\nThere is hospital equipment.\nAdded " + weight_equip + " kg.");
            total_weight += weight_equip;
            System.out.println("TOTAL WEIGHT = " + total_weight + " kg");
        }
        
        //passengers' weight, current floor, direction, desired floor
        for(int i=1; i<=5; i++)
        {
            setID(i);   /*set passenger ID*/

            //randomly generate passenger's weight
            //between 50 - 140 kg
            //max-min = 140-50 = 90
            weight_passenger = 40 + (int)(Math.random()*90);

            System.out.println("\nPassenger #" + i + " = " + weight_passenger + " kg");
            
            total_weight += weight_passenger;
            
            num_passengers ++;
            
            System.out.println("Current num of passengers = " + num_passengers);
            System.out.println("TOTAL WEIGHT = " + total_weight + " kg");
            
            //randomly select current floor from array
            call_floor = new Random().nextInt(floors.size()-1);
            System.out.println("Current floor       : " + floors.get(call_floor)); 
            
            //randomly select direction (up or down)   
            //if curr_floor == 4, not possible to go up
            if(call_floor == 4)
            {
                dest_dir = 0; //down
            }
            //if curr_floor == 1, not possible to go down
            else if (call_floor == 1)
            {
                dest_dir = 1; //up
            }
            else //floor is neither at 4(highest) or 1(lowest)
            {
                //System.out.println("PROBLEM");
                dest_dir = new Random().nextInt(directions.length);
                System.out.println("Direction           : " + directions[dest_dir]);
            }
            
            System.out.println("Direction           : " + dest_dir);

            //based on direction chosen, randomly select a destination floor which satisfies direction            
            
            if (dest_dir == 1) //UP direction chosen
            {
                System.out.println("they wanna go up"); //just a marker, remove later
                //validation check : dest_floor cant be equal or less than current floor
                do
                {
                    dest_floor = new Random().nextInt(floors.size()-1);
                }
                while (dest_floor <= call_floor);
            }
            else //DOWN direction chosen
            {
                System.out.println("they wanna go down"); //just a marker, remove later
                //validation check : dest_floor cant be equal or more than current floor
                do
                {
                    dest_floor = new Random().nextInt(floors.size()-1);
                }
                while (dest_floor >= call_floor);
            }        
            System.out.println("Destination floor   : " + floors.get(dest_floor));
            
        }       
        
        //Meessage when lift is over weight
        if(total_weight >= 1000)
        {
            isOverweight = true;
            System.out.println("\n!!LIFT FULL!! Weight has exceeded 1000KG!");
        }else
            isOverweight = false;

        callArr.add(call_floor);    //add call floor to call array
        destArr.add(destFloor);     //add destination floor to dest array
        dest_dirArr.add(dest_dir);  //add direction to the dest_dir array
            
    }  
    
}
