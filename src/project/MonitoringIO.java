/**
 * 
 */
package project;

import java.util.Scanner;
/**
 * @author Goodie Blake Dawson
 *
 */
public class MonitoringIO extends Monitoring{
	

	public static void enob () {
		
	}
	
	public static void engal () {
		
	}
	
	public static void stats () {
		
	}
	
	public static void exit() {
		System.exit(0);
	}
	
	public static void start() {
		
		Scanner input = new Scanner(System.in);
		
		while (true) {
			System.out.println("\nPlease find below a list of inputs for your possible actions:"
					+ "\nenob: To enter Obsevatory data"
					+ "\nengal: To enter Galamsey data"
					+ "\nstats: To view certain importantant figures about the recorded data"
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
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Hello and Welcome."
				+ "\nThis is a program that monitors Galamsey events.");
		start();
		
			

		}

	}

}
