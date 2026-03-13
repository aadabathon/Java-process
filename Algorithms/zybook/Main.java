import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter Max Length (-1 for unbounded):");
        int maxLength = scan.nextInt();
        scan.nextLine(); 

        ArrayBasedStack<String> stack;
        if (maxLength < 0) {
            stack = new ArrayBasedStack<>();
        } else {
            stack = new ArrayBasedStack<>(maxLength);
        }

        System.out.println("Enter instruction (push, pop, peek, quit):");

        String instruction = scan.next();

        while (!instruction.equalsIgnoreCase("quit")) {
            if (instruction.equalsIgnoreCase("push")) {
                String value = scan.next();
                System.out.println("Was value added? " + stack.push(value));
            } else if (instruction.equalsIgnoreCase("pop")) {
                try {
                    System.out.println("Value Removed: " + stack.pop());
                } catch (Exception e) {
                    System.out.println("No values to remove!");
                }
            } else if (instruction.equalsIgnoreCase("peek")) {
                try {
                    System.out.println("Top value (Not removed): " + stack.peek());
                } catch (Exception e) {
                    System.out.println("No values to peek at!");
                }
            } else {
                System.out.println("Invalid instruction.");
            }

            System.out.println("Full stack: " + stack);
            instruction = scan.next();
        }

        scan.close();
    }
}
