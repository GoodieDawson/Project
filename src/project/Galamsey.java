/**
 * A class called Galamsey that record where Galamsey occurs and how devastating it is in Africa.
 * This will help African security agencies and mining authorities better understand and detect
 * illegal mining.
 */

package project;

/**
 * @author Group_18
 * 
 */
public class Galamsey {
	
	/**
	 * A position class that takes the longitude and latitude of the Galamsey area
	 * Sample format is: 5.5862° N, 0.1752° W (the angle in degrees followed by the direction)
	 */
	public class Position {
		private double lon;
		private double lat;
		
		/**
		 * A constructor that takes the longitude and latitude of the Galamsey area as
		 * parameters for the Position class
		 * @param lon
		 * @param lat
		 */
		private Position(double lon, double lat) {
			this.lon = lon;
			this.lat = lat;
			
		}

		/**
		 * @return the lon
		 */
		public double getLon() {
			return lon;
		}

		/**
		 * @param lon the lon to set
		 */
		public void setLon(double lon) {
			this.lon = lon;
		}

		/**
		 * @return the lat
		 */
		public double getLat() {
			return lat;
		}

		/**
		 * @param lat the lat to set
		 */
		public void setLat(double lat) {
			this.lat = lat;
		}

		/**
		 * A method that creates a string representation of the Position object with
		 * the longitude and latitude
		 */
		@Override
		public String toString() {
			return "Position" 
					+"\nLongitude: " + lon + "Latitude: " + lat;
		}
	}

	/**
	 * Instance variables for Galamsey: vegetation colour (VegCol) of the area and its 
	 * associated colour value (ColVal), position (longitude and latitude) of the area 
	 * and year (year) of Galamsey event
	 */
	private enum VegCol {Green, Yellow, Brown};
	VegCol vegCol;
	private int colVal;
	private Position pos;
	private int year;
	private int id;
	private double lon;
	private double lat;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * A default constructor for Galamsey class
	 */
	public Galamsey() {
		
	}
	
	/**
	 * An overloaded constructor for the Galamsey class
	 * A switch case to assign colour values to Galamsey events based on their vegetation colour
	 * 
	 * @param vegCol
	 * @param pos
	 * @param year
	 */
	public Galamsey(String vegCol, double lon, double lat, int year) {
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
		this.pos = new Position(lon, lat);
		this.year = year;
		this.lon = lon;
		this.lat = lat;
	}

	public Galamsey(int id, String vegCol, double lon, double lat, int year) {
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
		this.pos = new Position(lon, lat);
		this.year = year;
		this.id = id;
		this.lon = lon;
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	// Accessors and Mutators
	/**
	 * This accessor returns the vegetation colour of the area of the Galamsey event
	 * @return the vegCol
	 */
	public VegCol getVegCol() {
		return vegCol;
	}

	/**
	 * This mutator allows you to set the vegetation colour of a Galamsey event
	 * It also assigns a colour value to a Galamsey event after the vegetation color is input or changed
	 * @param vegCol
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
	 * This accessor returns the colour value of a Galamsey event
	 * @return the colValue
	 */
	public int getColVal() {
		return colVal;
	}

	/**
	 * This accessor returns the position (longitude and latitude) of a Galamsey event
	 * @return the pos
	 */
	public Position getPos() {
		return pos;
	}

	/**
	 * This mutator allows you to set the position (longitude and latitude) of a Galamsey event
	 * @param pos
	 */
	public void setPos(double lon, double lat) {
		this.pos = new Position(lon, lat);
	}

	/**
	 * This accessor returns the year of a Galamsey event
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * This mutator allows you to set the year of the illegal mining activity for a Galamsey event
	 * @param year
	 */
	public void setYear(int year) {
		this.year = year;
	}

	
	/**
	 * A method that creates a string representation of the Galamsey object with relevant
	 * details such as the area's vegetation colour and assigned colour value, position and year
	 * of occurrence
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
		if (pos == null) {
			if (other.pos != null)
				return false;
		} else if (!pos.equals(other.pos))
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

	}

}
