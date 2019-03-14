/**
 * 
 */
package project;

import java.util.Scanner;
/**
 * @author Goodie Blake Dawson
 *
 */
public class MonitoringIO {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		while (true) {
			System.out.println("x");
			String task = input.nextLine();
			if (task.equals("exit")) {
				System.exit(0);
			}
		}

	}

}
