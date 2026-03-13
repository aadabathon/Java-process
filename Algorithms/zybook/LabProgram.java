import java.util.Scanner;

public class LabProgram {
   
   public static void drawTriangle(int baseLength) { 
       if (baseLength < 1) {
        return;
       } else {
        drawTriangle(baseLength-2);
        int lines = baseLength;
        int num = (baseLength+1)/2;
        int spaces = 10 - num;
        for (int i = 0; i < spaces; i++) {
          System.out.print(" ");
        }
        for (int i = 0; i < lines; i++) {
          System.out.print("*");
        }
   }
         System.out.println();
    }
	
	
   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);
      int baseLength;
      
      baseLength = scnr.nextInt();
      drawTriangle(baseLength);
   }
}
