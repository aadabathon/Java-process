import java.util.Scanner;
public class findmax {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int Max = 0;
        int currentVal;
        int numValues;

        
        numValues = scnr.nextInt();

        for (int i = 0; i < numValues; i++) { 
            currentVal = scnr.nextInt();
            if (i == 0){
                Max = currentVal;
            }
            else if (currentVal > Max){
                Max = currentVal;
            }
        }
        System.out.println(Max);
        scnr.close();
    }
}
    