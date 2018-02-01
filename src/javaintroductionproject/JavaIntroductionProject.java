
package javaintroductionproject;

import helpers.FileHelper;
import static helpers.SortHelper.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Punked
 */
public class JavaIntroductionProject {

    
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        /*  
        We create the list named "liveStockList" which is of <LiveStock> type. 
        to add objects into it we use the command "liveStockList.add(new XXXX(N,M)); "
        where XXXX can be chosen between Buffalo, Rabbit or Sheep, and "N" stands for ID
        and "M" stands for the starting weight of the animal.
        */
        
        List<LiveStock> liveStockList = new ArrayList<LiveStock>();
        liveStockList.add(new Buffalo(2, 230));
        liveStockList.add(new Sheep(45, 24));
        liveStockList.add(new Buffalo(1, 230));
        liveStockList.add(new Rabbit(12, 1));
        liveStockList.add(new Rabbit(11, 2));
        liveStockList.add(new Buffalo(8, 233));
        liveStockList.add(new Rabbit(10, 4));
        liveStockList.add(new Sheep(5, 26));
        liveStockList.add(new Sheep(7, 31));
        int t;
        do {
        String menu = "Available commands:\n"
                + "1 - Show the animals in the list\n"
                + "2 - Show the animals in the list ordered by their ID\n"
                + "3 - Show the animals in the list ordered by their kind and ID\n"
                + "4 - Save the list into a file (as objects which can be loaded back into the list)\n"
                + "5 - Load the list from a file (to make sure this works you can save the file first using\n"
                + "command '4', then use '8' to start the method and grow all the animal until all of them get sold\n"
                + "and then print the list to see if it's empty using '1'. Then you can procceed with '5' to load\n"
                + "the previous list and print it again with '1'. :)\n"
                + "6 - Grow the animals in the list by entering the months you choose in numbers\n"
                + "7 - Save the list into a text file output.txt as strings\n"
                + "8 - Show all the animals and all the months it took to grow them and sell them\n"
                + "9 - Exit the interface!"
                + "\n"
                + "Enter your command:\n";
        System.out.println(menu);
        Scanner enter = new Scanner(System.in);
        t = enter.nextInt();
        
        switch(t) {
         case 1 : 
                /*
                Prints the already generated list of LiveStock on the console.
                */
                for(LiveStock item : liveStockList){
                    item.show();
                    }
                System.out.println("-----------------------");
                break;
         case 2 :
                /*
                In Order to sort the already created list there are few methods along which we can choose
                We call them from the created SortHelper by typing into the code:
                to sort the animials by ID -> SortHelper.sortByID(XXXX);
                to sort the animials by kind -> SortHelper.sortByName(XXXX);
                to sort the animials by kind and ID -> SortHelper.sortByID(XXXX);
                where "XXXX" is the name of the list - liveStockList.
                */
                System.out.println("We have the following list of registered ordered by ID animals:");
                System.out.println("=================================================");
                sortByID(liveStockList);
                System.out.println("-----------------------");
                break;
         case 3 :
                /*
                In Order to sort the already created list there are few methods along which we can choose
                We call them from the created SortHelper by typing into the code:
                to sort the animials by ID -> SortHelper.sortByID(XXXX);
                to sort the animials by kind -> SortHelper.sortByName(XXXX);
                to sort the animials by kind and ID -> SortHelper.sortByID(XXXX);
                where "XXXX" is the name of the list - liveStockList.
                */
                System.out.println("We have the following list of registered ordered by ID animals:");
                System.out.println("=================================================");
                sortByIdAndName(liveStockList);
                System.out.println("-----------------------");
                break;
         case 4 :
                /*
                We call the SaveInFile() method from the package FileHelpers to save the current generated list
                in a file, so we can load it after that. Kind of a back-up.
                */
                FileHelper.SaveInFile(liveStockList);
                System.out.println("The file has been saved succesfully as 'LiveStock.txt' !");
                System.out.println("-----------------------");
                break;
         case 5 :
                /*
                We call the LoadFromFile() method from the package FileHelpers to load the previously saved list
                from a file. Note: this will work only if we used the SaveInFile() method from above.
                */
                liveStockList = FileHelper.LoadFromFile();
                System.out.println("The file has been loaded succesfully as 'LiveStock.txt' !");
                System.out.println("-----------------------");
                break;
         case 6 :
                /*
                This is to ask the user to enter desired number of months so he can see
                what will be the weight of the animal and if this animal has got to the max grow rate and sold.
                */
                System.out.println("Enter months (number from 0 to 1000) to see the weight of the animal.");
                int N = enter.nextInt();
                System.out.println("=================================================");
                for(LiveStock item : liveStockList){
                item.grow(N);
                }
                for (int i=liveStockList.size()-1;i>=0;i--) { //loop to check if the animalhas gotten to the max grow rate per kind
                        if  (liveStockList.get(i).currentWeight >= liveStockList.get(i).MAX_GROW_RATE) { //and get removed(sold) from the list
                            System.out.println("The animal with ID:"+liveStockList.get(i).ID+" is sold.");
                            liveStockList.remove(i);
                        }
                }
                System.out.println("-----------------------");
                break;
         case 7 :
                /*
                Here we print the list on the console and save it as a string in a file. 
                */
                System.out.println("The file has been saved succesfully as 'output.txt' !");
                FileHelper.SaveListIntoFile(liveStockList);
                System.out.println("-----------------------");
                break;
         case 8 :
                /*
                The method monthyGrow() will start a loop in which all the animals will start growing
                until they reach their max grow rate per kind and we will see the months it took for each single
                animal to be fully grown. Then again when we get to that point on the console we will have printed 
                a msg telling us that the animal will be sold. 
                */
                for(LiveStock item : liveStockList){
                item.monthlyGrow();
                }
                for (int i=liveStockList.size()-1;i>=0;i--) {
                        if  (liveStockList.get(i).currentWeight >= liveStockList.get(i).MAX_GROW_RATE) {
                            System.out.println("The animal with ID:"+liveStockList.get(i).ID+" was sold.");
                            liveStockList.remove(i);
                        }
                }
                System.out.println("-----------------------");
                break;
         case 9 :
                System.out.println("You have exited the interface!");
                t=0;
                break;
         default: System.out.println("Invalid command!");
        } 
        } while (t != 0);
    }
}
