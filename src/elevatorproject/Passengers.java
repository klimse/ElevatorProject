package elevatorproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Passengers extends Thread
{
    int weight_passenger, total_passweight, weight_equip, total_weight, call_floor, dest_floor;
    String passname;
    boolean isOverweight = false;
    int num_passengers = 5;
    
    //declare an arraylist for floor numbers
    ArrayList<Integer> floors = new ArrayList<>(Arrays.asList(1,2,3,4));
    
    ArrayList<Integer> destArr = new ArrayList<>(); //araylist for destination floors
    ArrayList<Integer> callArr = new ArrayList<>(); //arraylist for call floors
    ArrayList<String> passArr = new ArrayList<>();
     
    MutEx mutex = new MutEx();

    public Passengers(MutEx m){
        mutex = m;
    }
  
    //=================================== FUNCTIONS ===================================//
    Random ran = new Random();
    
    //randomly select a passenger's name from arraylist
    public String randomPassName() 
    {
           //declare an arraylist for names of passengers
    ArrayList<String> passengernames = new ArrayList<>(Arrays.asList("Aisyah","Bella","Chris","Daisy","Eric",
                                                                        "Fiona","Gary","Hanna","Iman","Justin",
                                                                        "Kayla","Larry","Mandy","Nurul","Olivia"));
      
        //select a random number of passengers from 1-5
        //num_passengers = 1 + (int)(Math.random()*5);
        System.out.println("There are " + num_passengers + " passengers waiting.\n");
        passArr.clear();

        for (int i=1; i<=num_passengers; i++)
        {
            int name = ran.nextInt(passengernames.size());
            passname = passengernames.get(name);
            passArr.add(passname);

            //prevents same name from being called again
            passengernames.remove(name);
            
            System.out.println(passname + "\tis waiting...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("\t");
        return passname;
    }
    
    //randomly generate passenger and equipment weight
    public int randomWeight()
    {
        do{
            //generate passenger weight (between 50-100kg) for the appropriate number of passengers
            for (int i=1; i<=num_passengers; i++)
            {
                weight_passenger = 50 + (int)(Math.random()*100);    
                total_passweight += weight_passenger;
            }
            randomPassName();
        
            System.out.println("Weight of passenger(s) = " + total_passweight + " kg");
            
            //generate equipment weight (between 300-1500kg)
            weight_equip = 300 + (int)(Math.random()*1200);
            System.out.println("Weight of equipment    = " + weight_equip + " kg");
            
            //sum up to get total weight
            total_weight = weight_passenger + weight_equip;
            System.out.println("Total weight on board  = " + total_weight + " kg");
            
            //check whether it is overweight or not
            if(total_weight > 1000)
            {
                isOverweight = true;
                System.out.println("Too Heavy!! Please exit the elevator.\n");
                total_weight = 0;
            }else
                isOverweight = false;

        }while(isOverweight == true);

        return weight_passenger;
    }
    
    //generate a random 'call coming from' floor from arraylist
    public int randomCallFloor() 
    {
        do{
        call_floor = ran.nextInt(floors.size());   
        }while(call_floor==0);//to prevent call_floor being 0

        return call_floor;
    }
    
    //generate a random 'destination' floor from arraylist
    public int randomDestFloor()
    {   
        //logic : destination floor cannot be the same as call floor and be 0
        do{
            dest_floor = ran.nextInt(floors.size());
        } while (dest_floor == call_floor || dest_floor == 0); 
  
        return dest_floor;
    }

    //generates random call and destination floors and assigns them to arrays
    public void setArr(){
        for(int i = 0; i < num_passengers; i++){ //generate 5 tasks for the 5 passengers
            callArr.add(randomCallFloor());
            destArr.add(randomDestFloor());
        }

        mutex.getArray(callArr, destArr, passArr);   //pass to the mutex class
    }

    //run method to generate random weight, passengers, call and destination arrays
    public void run()
    {       
        randomWeight();
        setArr();
    }  
}