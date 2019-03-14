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
	
	public static Exit() {
		System.exit(0);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Hello and Welcome.\n"
				+ "This is a program that monitors Galamsey events."
		
		Scanner input = new Scanner(System.in);

		while (true) {
			System.out.println("Please find below a list of inputs for your possible actions");
					 
			String task = input.nextLine();
			
			switch(input) {
			case Green : 
				this.colVal = 1;
				break;
			case Yellow : 
				this.colVal = 2;
				break;
			case Brown : 
				this.colVal = 3;
				break;
		}
			

		}

	}

}
