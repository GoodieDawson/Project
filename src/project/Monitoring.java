/**
 * 
 */
package project;

import java.util.ArrayList;

/**
 * @author Goodie Blake Dawson
 *
 */
public class Monitoring extends Observatory{

	private static ArrayList<Observatory> ObList = new ArrayList<Observatory>();
	
	public static double hiObAvg () {
		double hiAvg = 0;
		
		for (Observatory ob : ObList) {
			int obSum = 0;
			
			for (Galamsey galam : ob.getEvents()) {
				obSum = obSum + galam.getColVal();
			}
			
			double obAvg = obSum / ob.getEvents().size();
			if (obAvg > hiAvg) { hiAvg = obAvg; }
		}
		
		return hiAvg;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
