/**
 * A class called Observatory that extends properties and methods of the Galamsey class.
 * It stores and retrieves information about observatories that monitor different Galamsey events.
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
	 * of Galamsey events.
	 */

	private int obId;
	private String obName;
	private String obCount;
	private int obYear;
	private double obArea;
	private ArrayList<Galamsey> events;
	
	/**
	 * A default constructor that creates an empty observatory.
	 */
	public Observatory() {
		
	}
	
	/**
	 * An overloaded constructor without a list of events, in case there are 
	 * no pre-existing Galamsey events when the Observatory is being created.
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
	 * An overloaded constructor that creates an observatory with a list of existing Galamsey events.
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

	/**
	 * An overloaded constructor that creates an observatory without a list of Galamsey events.
	 * It also has an id for the observatory.
	 * @param name
	 * @param count
	 * @param year
	 * @param area
	 */
	public Observatory(int obId, String name, String count, int year, double area) {
		this.obId = obId;
		this.obName = name;
		this.obCount = count;
		this.obYear = year;
		this.obArea = area;
	}
	//Accessors and Mutators

	/**
	 * An accessor that returns the id of an observatory.
	 * @return the obId
	 */
	public int getObId() {
		return obId;
	}

	/**
	 * A mutator that allows the user to change the id of a pre-existing observatory.
	 * @param obId
	 */
	public void setObId(int obId) {
		this.obId = obId;
	}

	/**
	 * An accessor that returns the name of an observatory.
	 * @return the obName
	 */
	public String getObName() {
		return obName;
	}

	/**
	 * A mutator that allows the user to change the name of a pre-existing observatory.
	 * @param obName
	 */
	public void setObName(String obName) {
		this.obName = obName;
	}

	/**
	 * An accessor that returns the country in which an observatory is located.
	 * @return the obCount
	 */
	public String getObCount() {
		return obCount;
	}

	/**
	 * A mutator that allows the user to change the country in which an observatory is located.
	 * @param obCount
	 */
	public void setObCount(String obCount) {
		this.obCount = obCount;
	}

	/**
	 * An accessor that returns the year in which Galamsey observations started for an observatory.
	 * @return the obYear
	 */
	public int getObYear() {
		return obYear;
	}

	/**
	 * A mutator that allows the user to change the year in which Galamsey observations started for an observatory.
	 * @param obYear
	 */
	public void setObYear(int obYear) {
		this.obYear = obYear;
	}

	/**
	 * An accessor that returns the area covered by the observatory (in square kilometers).
	 * @return the obArea
	 */
	public double getObArea() {
		return obArea;
	}

	/**
	 * A mutator that allows the user to change the area covered by the observatory (in square kilometers).
	 * @param obArea
	 */
	public void setObArea(double obArea) {
		this.obArea = obArea;
	}

	/**
	 * An accessor that returns a list of Galamsey events.
	 * @return the events
	 */
	public ArrayList<Galamsey> getEvents() {
		return events;
	}
	
	/**
	 * A method that allows the user to add a Galamsey event to an observatory's list of events.
	 * @param obj
	 */
	public void addEvent(Galamsey obj) {
		this.events.add(obj);
	}

	/**
	 * A method that allows a user to remove a specific event from an observatory's list of events.
	 * @param i
	 */
	public void removeEvent(int i) {
		this.events.remove(i);
	}
	
	/**
	 * A method that returns the largest Galamsey colour value recorded by an observatory,
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
	 * A method that returns the average Galamsey colour value recorded by an observatory.
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
	 * A method that returns a list of all Galamsey events recorded, with a colour value
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

	@Override
	public String toString() {
		return "\nThe details of this Observatory are: " +
				"\nName: " + obName +
				"\nCountry: " + obCount +
				"\nYear: " + obYear +
				"\nArea: " + obArea
				;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

	}

}
