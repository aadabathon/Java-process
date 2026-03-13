import java.util.Scanner;
public class BSTManager {

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		BST tree = new BST();
		char input = '-';  // Initialize to a character other than a menu selection.
		
		printMenu();
		while (input != 'Q') {
			input = '-';  // Force a new menu selection.
			while (input != 'A' && input != 'R' && input != 'F' && input != 'P' && 
				   input != 'O' && input != 'Q') {
				System.out.println("Make your selection:");
				input = scnr.nextLine().charAt(0);
			}
			
			if (input == 'A') {
				System.out.println("Enter an integer data value for the added node:");
				tree.insert(new Node(scnr.nextInt()));
				scnr.nextLine();
			} 
			else if (input == 'R') {
				System.out.println("Enter an integer data value of a node you'd like to remove:");
				tree.remove(new Node(scnr.nextInt()));
				scnr.nextLine();
			} 
			else if (input == 'F') {
				System.out.println("Enter the integer data of the node you'd like to find:");
				tree.find(new Node(scnr.nextInt()));
				scnr.nextLine();
			} 
			else if (input == 'P') {
				tree.printBSTInOrder();
			} 
			else if (input == 'O') {
				System.out.println("The root's value is " + tree.getRoot() + ".");
			}
		}
		scnr.close();
	}
	
	public static void printMenu() {
		System.out.println("A : Add a node");
		System.out.println("R : Remove a node");
		System.out.println("F : Find a node");
		System.out.println("P : Print the tree");
		System.out.println("O : Print the root's data");
		System.out.println("Q : Quit");
	}

}
