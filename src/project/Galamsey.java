/**
 * A class called Galamsey that record where �galamsey� occurs and how devastating it is in Africa.
 * This will help African security agencies and mining authorities better understand and detect
 * illegal mining.
 */

package project;

/**
 * @author Group 18
 * 
 */
public class Galamsey {

	/**
	 * Instance variables for vegetation colour (VegCol), colour value,
	 * position (longitude and latitude) and year of galamsey.
	 */
	private enum VegCol {Green, Yellow, Brown};
	VegCol vegCol;
	private int colVal;
	private double pos;
	private int year;
	
	/**
	 * Default constructor for Galamsey class
	 */
	public Galamsey() {
		
	}
	
	/**
	 * Overloaded constructor for Galamsey class
	 * Switch case to assign colour values based on vegetation colour
	 * 
	 * @param vegCol
	 * @param pos
	 * @param year
	 */
	public Galamsey(String vegCol, double pos, int year  ) {
		this.vegCol = VegCol.valueOf(vegCol);
		switch(this.vegCol) {
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
		this.pos = pos;
		this.year = year;
	}
	
	// Accessors and Mutators
	/**
	 * Accessor for vegetation colour
	 * @return the vegCol
	 */
	public VegCol getVegCol() {
		return vegCol;
	}

	/**
	 * Mutator for vegetation colour
	 * @param vegCol the vegCol to set
	 */
	public void setVegCol(String vegCol) {
		this.vegCol = VegCol.valueOf(vegCol);
		switch(this.vegCol) {
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

	/**
	 * Accessor for colour value
	 * @return the colValue
	 */
	public int getColVal() {
		return colVal;
	}

	/**
	 * Accessor for position (longitude and latitude)
	 * @return the pos
	 */
	public double getPos() {
		return pos;
	}

	/**
	 * Mutator for position (longitude and latitude)
	 * @param pos the pos to set
	 */
	public void setPos(double pos) {
		this.pos = pos;
	}

	/**
	 * Accessor for year of illegal mining
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Mutator for year of illegal mining
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	
	/**
	 * A method that creates a string representation of the Galamsey object 
	 */
	@Override
	public String toString() {
		return "The details of this event are:" + 
				"\nVegetation colour: " + vegCol + 
				"\nColour value: " + colVal + 
				"\nPosition (Longitude and Latitude): " + pos + 
				"\nYear of event: " + year;
	}

	/** 
	 * A method that checks if two objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Galamsey other = (Galamsey) obj;
		if (colVal != other.colVal)
			return false;
		if (Double.doubleToLongBits(pos) != Double.doubleToLongBits(other.pos))
			return false;
		if (vegCol != other.vegCol)
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Galamsey a = new Galamsey("Green", 49, 2019);
		Galamsey b = new Galamsey("Green", 49, 2019);
		System.out.println(a.equals(b));
		
		System.out.println(a.getVegCol());
		System.out.println(a.getColVal());
		System.out.println(a.getPos());
		System.out.println(a.getYear());
		
		a.setVegCol("Brown");
		System.out.println(a.getVegCol());
		System.out.println(a.getColVal());
		a.setPos(77);
		System.out.println(a.getPos());
		a.setYear(2018);
		System.out.println(a.getYear());
		System.out.println(a.toString());
	}

}
