/**
 * 
 */
package project;

import java.util.Scanner;

/**
 * @author Group_18
 *
 */
public class MonitoringIO extends Monitoring{

	public static void enob () {
		
	}
	
	public static void engal () {
		
	}
	
	public static void stats () {
		
		System.out.println("\nPlease find below a list of inputs for your stats actions:"
				+ "\nhiObAvg: To Get the Observatory with the largest average galamsey colour value"
				+ "\nmaxValEver: To get The largest Galamsey colour value ever recorded"
				+ "\ngivenList: To get all Galamsey with colour value greater than a given number");
		
		Scanner input = new Scanner(System.in);
		String task = input.nextLine();
		
		switch(task) {
		case "hiObAvg" : 
			System.out.println(hiObAvg().toString());
			start();
		case "maxValEver" : 
			System.out.println(maxValEver());
			start();
		case "givenList" : 
			System.out.println("Please input a number");
			int num  = input.nextInt();
			for(Galamsey obj : givenList(num)) {
				obj.toString();
			}
			start();
		}
		
	}
	
	public static void exit() {
		System.exit(0);
	}
	
	public static void start() {
		
		Scanner input = new Scanner(System.in);
		
		while (true) {
			System.out.println("\nPlease find below a list of input words and their respective actions."
					+ "\nEnter one of the words to begin:"
					+ "\n\nenob: To input Obsevatory data"
					+ "\nengal: To input Galamsey data"
					+ "\nstats: To view certain monitoring statistics about the recorded data"
					+ "\nexit: To end the program");
					
					 
			String task = input.nextLine();
			
			switch(task) {
			case "enob" : 
				enob();
				break;
			case "engal" : 
				engal();
				break;
			case "stats" : 
				stats();
				break;
			case "exit" : 
				exit();
				break;
			}

		}
	}
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Welcome to the Illegal Mining (Galamsey) Monitoring System."
				+ "\nThis program will help you keep track of Galamsey events and provide certain key statistics.");
		start();
		


	}
}
