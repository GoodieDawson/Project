/**
 * 
 */
package project;

import java.util.ArrayList;

/**
 * @author Group_18
 *
 */
public class Monitoring extends Observatory{

	private static ArrayList<Observatory> ObList = new ArrayList<Observatory>();
	
	/**
	 * A method that returns the observatory with the largest average "galamsey" colour value. 
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
	 * in all the observatories.
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
	 * A method that takes in a given number and returns a list of all "galamsey"
	 * recorded in all observatories with a colour value greater than the given number.
	 *
	 * @param num
	 * @return
	 */
	public static ArrayList<Galamsey> givenList(int num) {
		ArrayList<Galamsey> givList = new ArrayList<Galamsey>();
		if (ObList.isEmpty()) {
			System.out.println("No list can be created as there are no observatories");
		}
		else {
			for (Observatory obj : ObList) {
				givList.addAll(obj.aboveList(num));
			}
		}
		return givList;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

	}

}
