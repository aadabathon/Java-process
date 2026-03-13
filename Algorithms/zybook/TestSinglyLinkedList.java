import java.util.Scanner;

public class TestSinglyLinkedList {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        int input = -1;
        printMenu();
        while (input != 10) {
            //printMenu();
            input = scnr.nextInt();
            while (0 > input || input > 10) {
                System.out.println("Invalid choice. Please try again.");
                input = scnr.nextInt();
            }
            if (input == 1) {
                System.out.println("Enter an integer for the node data:");
                int data = scnr.nextInt();
                System.out.println("Enter an integer for the index:");
                list.add(scnr.nextInt(), data);
            }
            else if (input == 2) {
                System.out.println("Enter an integer for the node data:");
                int data = scnr.nextInt();
                list.add(data);
            }
            else if (input == 3) {
                System.out.println("Enter an integer for the node data:");
                int data = scnr.nextInt();
                list.addFirst(data);
            }
            else if (input == 4) {
                if (list.size() == 0) {
                    System.out.println("\nThe list is already empty. \n");
                }
                list.clear();
            }
            else if (input == 5) {
                System.out.println("Enter an integer for the index:");
                list.removeAt(scnr.nextInt());
            }
            else if (input == 6) {
                System.out.println("Enter the data of the node you'd like to remove:");
                list.remove(scnr.nextInt());
            }
            else if (input == 7) {
                System.out.println("Enter an integer for the node data:");
                int data = scnr.nextInt();
                System.out.println("Enter an integer for the index:");
                list.set(scnr.nextInt(), data);
            }
            else if (input == 8) {
                if (list.size() == 1) {
                    System.out.println("There is 1 node in the list.\n" );
                }
                else {
                    System.out.println("There are " + list.size() + " nodes in the list.");
                }
            }
            else if (input == 9) {
                list.printList();
            }
            if (input != 10) {
                System.out.println("\nEnter your next selection.");
            }
        }
        System.out.println("DONE");
        scnr.close();
    }
    public static void printMenu() {
        System.out.println("1 : Adds a node at a specified index");
        System.out.println("2 : Adds a node to the end of the list");
        System.out.println("3 : Adds a node to the front of the list");
        System.out.println("4 : Clear the list (remove all nodes)");
        System.out.println("5 : Removes the node at a specified index");
        System.out.println("6 : Removes the first instance of a node");
        System.out.println("7 : sets the node at a specified index to a new node");
        System.out.println("8 : returns the number of nodes in the list");
        System.out.println("9 : Print the list");
        System.out.println("10 : quit");
    }
}
