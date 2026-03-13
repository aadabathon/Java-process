import java.util.ArrayList;
import java.util.Scanner;

public class PhotoLineups {

   // TODO: Write method to create and output all permutations of the list of names.
   public static void printAllPermutations(ArrayList<String> permList, ArrayList<String> nameList) {

     if (nameList.isEmpty()) {
          for (int i = 0; i < permList.size(); i++) {
               if (i > 0) {
                   System.out.print(", ");
               }
               System.out.print(permList.get(i));
           }
           System.out.println();
     }
        else{ 
          for (int i = 0; i < nameList.size(); i++) {
             String currentName = nameList.get(i);
             permList.add(currentName);
             ArrayList<String> remainingNames = new ArrayList<>(nameList);
             remainingNames.remove(i);
             printAllPermutations(permList, remainingNames);
             permList.remove(permList.size() - 1);
        }
     }
   }

   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);
      ArrayList<String> nameList = new ArrayList<String>();
      ArrayList<String> permList = new ArrayList<String>();
      String name;
      
      // TODO: Read a list of names into nameList; stop when -1 is read. Then call recursive method.
      while (true){
           name = scnr.next();
           if (name.equals("-1")){
               break;
           }
           nameList.add(name);
      }
          printAllPermutations(permList, nameList);
   }
}
