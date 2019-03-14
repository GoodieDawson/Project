/**
 * 
 */
package project;

/**
 * @author Goodie Blake Dawson
 *
 */
public class Monitoring extends Observatory{
	
	/**
	 * A method that returns the largest "galamsey" colour ever recorded.
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
