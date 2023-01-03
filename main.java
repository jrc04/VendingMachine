
/**
 * Write a description of class main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Scanner;
public class main
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        String input = "";
        System.out.println("#     # ####### #     # ######  ### #     #  #####  ");
        System.out.println("#     # #       ##    # #     #  #  ##    # #     # ");
        System.out.println("#     # #       # #   # #     #  #  # #   # #       ");
        System.out.println("#     # #####   #  #  # #     #  #  #  #  # #  #### ");
        System.out.println(" #   #  #       #   # # #     #  #  #   # # #     # ");
        System.out.println("  # #   #       #    ## #     #  #  #    ## #     # ");
        System.out.println("   #    ####### #     # ######  ### #     #  #####  ");
        System.out.println("                                                    ");
        System.out.println("#     #    #     #####  #     # ### #     # ####### ");
        System.out.println("##   ##   # #   #     # #     #  #  ##    # #       ");
        System.out.println("# # # #  #   #  #       #     #  #  # #   # #       ");
        System.out.println("#  #  # #     # #       #######  #  #  #  # #####   ");
        System.out.println("#     # ####### #       #     #  #  #   # # #       ");
        System.out.println("#     # #     # #     # #     #  #  #    ## #       ");
        System.out.println("#     # #     #  #####  #     # ### #     # ####### ");
        System.out.println("");
        System.out.println("====================================================");
        System.out.println("");
        while(true)
        {
            System.out.println("What would you like to do?");
            System.out.println("1: Add a drink to the machine.");
            System.out.println("2: Restock the machine.");
            System.out.println("3: See the machine's inventory.");
            System.out.println("4: Buy a drink.");
            System.out.println("");
            input = scan.nextLine();
            if(input == "1")
            {
                System.out.println("What is the name of the soda you are restocking?");
                String name = scan.nextLine();
                System.out.println("What is the bottle/can made out of?");
                String material = scan.nextLine();
                System.out.println("What is the volume?");
                double volume = scan.nextDouble();
                System.out.println("What is the height?");
                int height = scan.nextInt();
                System.out.println("What is the radius?");
                int radius = scan.nextInt();
                System.out.println("What is the price?");
                double price = scan.nextDouble();
                //I am aware that this below isn't working. My brain isn't either. Let's talk about it sometime.
                VendingMachine.loadMachine(volume, height, name, material, radius, price);
            }
        }
    }
}
