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
	
	/**
	 * A method that returns the largest average "galamsey" value
	 * @return
	 */
	public static Observatory hiObAvg () {
		double hiAvg = 0;
		Observatory hiOb = null;
		
		for (Observatory ob : ObList) {
			int obSum = 0;
			
			for (Galamsey galam : ob.getEvents()) {
				obSum = obSum + galam.getColVal();
			}
			
			double obAvg = obSum / ob.getEvents().size();
			if (obAvg > hiAvg) { hiOb = ob; }
		}
		
		return hiOb;
	}
	
	/**
	 * A method that returns the largest "galamsey" colour ever recorded
	 * @return
	 */
	
	public static int maxValEver() {
		int max = 0;
		if (ObList.isEmpty()) {
			System.out.println("The max value cannot be determined as there are no observatories");	
		}
		else {
			for (Observatory obj: ObList) {
					if (obj.maxColVal() > max) {max = obj.maxColVal();}
			}
		}
		return max;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
