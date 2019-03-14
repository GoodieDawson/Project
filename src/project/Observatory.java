/**
 * A class called Observatory that stores and retrieves information about
 * observatories that monitor different galamsey events.
 */

package project;

//Import statement
import java.util.ArrayList;

/**
 * @author Group_18
 *
 */
public class Observatory extends Galamsey {
	
	/**
	 * Instance variables for observatory name (obName), observatory country (obCount),
	 * observatory year (obYear), observatory area (in square kilometres), and a list
	 * of galamsey events.
	 */

	private String obName;
	private String obCount;
	private int obYear;
	private double obArea;
	private ArrayList<Galamsey> events;
	
	/**
	 * A default constructor
	 */
	public Observatory() {
		
	}
	
	/**
	 * An overloaded constructor without a list of events in case no events are recorded
	 * @param name
	 * @param count
	 * @param year
	 * @param area
	 */
	public Observatory(String name, String count, int year, double area) {
		this.obName = name;
		this.obCount = count;
		this.obYear = year;
		this.obArea = area;
		this.events = null;
	}
	
	/**
	 * An overloaded constructor with a list of events
	 * @param name
	 * @param count
	 * @param year
	 * @param area
	 * @param events
	 */
	public Observatory(String name, String count, int year, double area, ArrayList<Galamsey> events) {
		this.obName = name;
		this.obCount = count;
		this.obYear = year;
		this.obArea = area;
		this.events = events;
	}
	
	//Accessors and Mutators
	
	/**
	 * Accessor for observatory name
	 * @return the obName
	 */
	public String getObName() {
		return obName;
	}

	/**
	 * Mutator for observatory name
	 * @param obName the obName to set
	 */
	public void setObName(String obName) {
		this.obName = obName;
	}

	/**
	 * Accessor for observatory country
	 * @return the obCount
	 */
	public String getObCount() {
		return obCount;
	}

	/**
	 * Mutator for observatory country
	 * @param obCount the obCount to set
	 */
	public void setObCount(String obCount) {
		this.obCount = obCount;
	}

	/**
	 * Accessor for observatory year
	 * @return the obYear
	 */
	public int getObYear() {
		return obYear;
	}

	/**
	 * Mutator for observatory year
	 * @param obYear the obYear to set
	 */
	public void setObYear(int obYear) {
		this.obYear = obYear;
	}

	/**
	 * Accessor for observatory area
	 * @return the obArea
	 */
	public double getObArea() {
		return obArea;
	}

	/**
	 * Mutator for observatory area
	 * @param obArea the obArea to set
	 */
	public void setObArea(double obArea) {
		this.obArea = obArea;
	}

	/**
	 * Accessor for list of events
	 * @return the events
	 */
	public ArrayList<Galamsey> getEvents() {
		return events;
	}
	
	/**
	 * A method to add a Galamsey event to the observatory's list of events.
	 * @param obj
	 */
	public void addEvent(Galamsey obj) {
		this.events.add(obj);
	}

	/**
	 * A method to remove a specific event from the observatory's list of events.
	 * @param i
	 */
	public void removeEvent(int i) {
		this.events.remove(i);
	}
	
	/**
	 * A method that returns the largest Galamsey colour value recorded by the observatory
	 * @return
	 */
	public int maxColVal() {
		int val = 0;
		if (getEvents().isEmpty()) {
			System.out.println("The max value is zero as there are no recorded events for this "
					+ "observatory");
		}
		else {
			for (Galamsey obj: getEvents()) {
				if (obj.getColVal() > val) {val = obj.getColVal();}
			}
		}
		return val;
	}
	
	/**
	 * A method that returns the average Galamsey colour value recorded by the observatory
	 * @return
	 */
	public double avgColVal() {
		double val = 0;
		if (getEvents().isEmpty()) {
			System.out.println("There average value is zero as there are no recorded events for this "
					+ "observatory");	
		}
		else {
			for (Galamsey obj: getEvents()) {
				val = val + obj.getColVal();
			}
		}
		return val / events.size();
	}
	
	/**
	 * A method that returns a list of all "galamsey" events recorded with a colour value
	 * greater than a given number.
	 *  
	 * @param num
	 * @return
	 */
	public ArrayList<Galamsey> aboveList(int num) {
		ArrayList<Galamsey> numList = new ArrayList<Galamsey>();
		if (getEvents().isEmpty()) {
			System.out.println("No list can be created as there are no recorded events for this "
					+ "observatory");
		}
		else {
			for (Galamsey obj : getEvents()) {
				if (getColVal() > num) {numList.add(obj);}
			}
		}
		return numList;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

	}

}
