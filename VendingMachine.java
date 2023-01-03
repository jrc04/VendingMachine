/**
  * Sample implementation of Vending Machine in Java
  */
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class VendingMachine {

    //-- TWO implementations of storing the PopCan(s):
    //(1) Use Java's ArrayList of PopCan(s) to keep track on inventory
    //(2) Use an actual array of PopCan(s) to keep track on inventory
    
    //{MEMBER VARIABLES}
    Scanner scan = new Scanner(System.in);
    Random rand = new Random();
    //-- keep track of total sales
    private int sales;
    //-- keep track of current item being processed
    private PopCan item;
    //-- keep track of current balance of item being processed
    private double balance;
    //-- determine a quantity capacity for the machine. Either constant or defined at run-time
    private int capacity;
    //-- array of strings to record transactions: "Brand,price,amount customer paid,good/bad"
    private ArrayList<Transaction> transactions;
    //Note: good/bad is based on if the customer had enough funds to cover the cost
    //Also, look into ArrayList of Strings to keep track of them
    //Note from Joey: Considering there's several things to keep track of, I think it's better to make this it's own object.
    private ArrayList<PopCan> inventory;
    //{METHODS}
    //-- create two constructors: 
    //(1) default --> set the current balance to $50, created the array(s) to a default capacity
    public VendingMachine()
    {
        sales = 0;
        PopCan item;
        balance = 50.0;
        capacity = 64;
        this.transactions = new ArrayList<Transaction>();
        this.inventory = new ArrayList<PopCan>();
    }
    //(2) one paramter --> set the the current balance to the passing paramter, 
        //created the array(s) to a default capacity
    public VendingMachine(double balance)
    {
        sales = 0;
        PopCan item;
        this.balance = balance;
        int capacity = 64;
        this.transactions = new ArrayList<Transaction>();
        this.inventory = new ArrayList<PopCan>();
    }
    //(3) two paramter --> set the the current balance to the passing paramter and the capacity size
        //created the array(s)
    public VendingMachine(double balance, int capacity)
    {
        int sales = 0;
        PopCan item;
        this.balance = balance;
        this.capacity = capacity;
        this.transactions = new ArrayList<Transaction>();
        this.inventory = new ArrayList<PopCan>();
    }
    //Getters
    public int getSales()
    {
        return sales;
    }
    public PopCan getItem()
    {
        return item;
    }
    public double getBalance()
    {
        return balance;
    }
    public int getCapacity()
    {
        return capacity;
    }
    public ArrayList getTransactions()
    {
        return transactions;
    }
    public ArrayList getInventory()
    {
        return inventory;
    }
    //-- create a method to load pop cans to the machine. Use a file to store the PopCan(s)
        // File line comma-separate format: Brand,Material,Price,Volume,Height,Radius
        // Note from Joey: I didn't learn how to make stuff with files in class. I'll make an ArrayList.
    public boolean loadMachine(double volume, int height, String name, String material, int radius, double price)
    {
        if(inventory.size() < capacity)
        {
            PopCan newcan = new PopCan(volume, height, name, material, radius, price);
            inventory.add(newcan);
            return true;
        }
        else
        {
            System.out.println("The machine is full.");
        }
        return false;
    }
    //-- create a method to load pop can using prompts. It uses a loop with some condition to
         //stop it. always checking for full capacity`
    public void reloadMachine()
    {
        System.out.println("Which type of pop can would you like to reload?");
        String type = scan.nextLine();
        boolean success = false;
        PopCan can = (PopCan)inventory.get(0);
        if(inventory.isEmpty())
        {
            System.out.println("You must load the machine with pop cans first before you can stock more.");
            return;
        }
        for(int i = 0; i < inventory.size(); i++)
        {
            can = (PopCan)inventory.get(i);
        }
        if(type.equals(can.getName()))
        {
            success = true;
            System.out.println("How many would you like to load?");
            int amount = scan.nextInt();
            int counter = 0;
            for(int j = amount; (j > 0) && (capacity - inventory.size() > 0); j--)
            {
                inventory.add(can);
                counter++;
            }
            if((amount != counter) && (capacity - inventory.size() != 0))
            {
                System.out.println("Restocked " + counter + " drinks, since the machine was full.");
            }
            else
            {
                    System.out.println("Restocked " + counter + " drinks.");
            }
        }
        if(!success)
        {
            System.out.println("Was unable to find a soda named " + type + ".");
        }
    }
    //-- create a method that passes a string as a brand name to check if there exists inventory
        // and returns its price as a double. If no brand exists add some exception handling
    public double checkInventory(String name)
    {
        boolean exists = false;
        PopCan finalcan = (PopCan)inventory.get(0);
        if (inventory.isEmpty())
        {
            System.out.println("The machine is empty. Try filling it with pop cans!");
            return -1.0;
        }
        for (int i = 0; i < inventory.size(); i++)
        {
            PopCan can = (PopCan)inventory.get(i);
            if(name.equals(can.getName()))
            {
                exists = true;
                finalcan = can;
                break;
            }
        }
        if(exists)
        {
            return finalcan.getPrice();
        }
        System.out.println("No cans were found of that name.");
        return -1.0;
    }
    //-- create a method to purchase a PopCan item. Two parameters: brand and amount. Use the previous
         //method to help with the transaction. If item exists, check if the customer's amount > price of
         //the can. You can determine what the return type etc. removal of can & update total sales etc
        // Note: the machine doesn't return back change if more the amount > price
         //** Add a random generator that generates a random number from [0-1]. There's 7% chance that the
        // machine will malfunction. Which implies the customer's money is eaten by the machine w/o their
        // can item. This probability of malfunction is checked for every transaction
    public void purchase(String name, int amount)
    {
        double price = checkInventory(name);
        if(price == -1.0)
        {
            System.out.println("No stock was detected.");
        }
        else
        {
            for(int i = amount; i > 0; i--)
            {
                PopCan can = (PopCan)inventory.get(i);
                if (can.getName() == name)
                {
                    boolean success = false;
                    System.out.println("Please pay at least " + price + " dollars.");
                    double payment = scan.nextDouble();
                    if(payment < price)
                    {
                        while (payment < price)
                        {
                            System.out.println("Please pay at least " + (price - payment) + " more dollars.");
                            payment += scan.nextDouble();
                        }
                    }
                    int randomgen = rand.nextInt(100);
                    if(randomgen > 6)
                    {
                        success = true;
                        inventory.remove(can);
                        System.out.println("The vending machine dispenses your can. Enjoy your digital soda!");
                    }
                    else
                    {
                        System.out.println("The vending machine eats your money, and doesn't dispense the can.");
                    }
                    balance += payment;
                    transactions.add(new Transaction(success, can, payment));
                }
                else
                {
                    System.out.println("No more stock detected.");
                    break;
                }
            }
        }
    }
    //-- create a method to display all the transactions upto that point. Both good/bad transactions
         //This should be a void return w/o any parameters
    public void displayTransactions()
    {
        String output = "";
        //output will be empty to start: this for loop should add inventory's contents and make a new line for each transaction.
        if(!transactions.isEmpty())
            for(int i = 0; i < transactions.size(); i++)
            {
                output = output + "Transaction " + (i+1) + ": " + ((Transaction)transactions.get(i)).toString() + "\n";
            }
        System.out.println(output);
    }
    //-- create a file to take the current inventory and load into a file. Using
    //the same file format as above: Brand,Material,Price,Volume,Height,Radius
    public void documentInventory()
    {
        try{
            String[] data = new String[6];
            String line = "";
            File file = new File("inventory.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while((line = br.readLine()) != null)
            {
                int len = line.length();
                int pos1 = 0;
                int pos2 = 0;
                for(int i = 0; pos2 > -1; i++)
                {
                    pos2 = line.indexOf(",", pos1);
                    /*The last token:*/
                    System.out.println(i + " " + pos1 + " " + pos2);
                    if(pos2 == -1 && pos1 < len)
                    {
                        data[i] = line.substring(pos1, len).trim();
                    }
                    /*The other tokens:*/
                    else
                    {
                        data[i] = line.substring(pos1, pos2).trim();
                    }
                    pos1 = pos2 + 1;
                }
                if (!loadMachine(Double.parseDouble(data[3]), Integer.parseInt(data[4]), data[0], data[1], Integer.parseInt(data[5]), Double.parseDouble(data[2])))
                {
                    break;
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    /**
    public void documentInventory()
    {
        try
        {
            File file = new File("inventory.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            String temp;
            char current;
            String name = "";
            String material = "";
            double price = 0;
            double volume = 0;
            int height = 0;
            int radius = 0;
            //temppoint is recording where in the line we are
            int temppoint = 0;
            while((line = br.readLine()) != null)
            {
                for(int i = 0; i < 5; i++)
                {
                    temp = "";
                    for(int j = temppoint; j < line.length(); j++)
                    {
                        current = line.charAt(j);
                        // | For troubleshooting:
                        // V
                        //System.out.println(current);
                        if(current == ',' && current != ' ')
                        {
                            temppoint++;
                            break;
                        }
                        else if (current != ' ')
                        {
                            temp += current;
                        }
                        temppoint++;
                    }
                    //There is 100%, definitely, absolutely a better way to do this.
                    // | Also for troubleshooting
                    // V
                    //System.out.println(temp);
                    if(i == 0)
                    {
                        name = temp;
                    }
                    if(i == 1)
                    {
                        material = temp;
                    }
                    if(i == 2)
                    {
                        //Had to look up how to convert a string to a double and int.
                        //Somehow this is giving me an empty string error. Need to get some sleep. We'll talk about it I guess.
                        price = Double.parseDouble(temp);
                    }
                    if(i == 3)
                    {
                        volume = Double.parseDouble(temp);
                    }
                    if(i == 4)
                    {
                        height = Integer.parseInt(temp);
                    }
                    if(i == 5)
                    {
                        radius = Integer.parseInt(temp);
                    }
                }
                loadMachine(volume, height, name, material, radius, price);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    */    
    public void fileInventory()
    {
        try
        {
            File file = new File("inventory.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while((line = br.readLine()) != null)
            {
                System.out.println(line);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    //-- create a toString() method to display the current inventory to the screen. This format
         //is left upto you.
    public String toString()
    {
        String output = "";
        if(!inventory.isEmpty())
        {
            for(int i = 0; i < inventory.size(); i++)
            {
            output = output + "Slot" + (i+1) + ": " + "\n" + (((PopCan)inventory.get(i)).toString());
            }
        }
        return output;
    }
 }
