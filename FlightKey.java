/**
 * Represents the key in the FlightNode. Stores origin, destination, date and
 * time. Implements Comparable<FlightKey>.
 */
public class FlightKey implements Comparable<FlightKey> {
	private String origin;
	private String destination;
	private String date;
	private String time;
	// keys: origin, destination, date, time
	// FILL IN CODE

	FlightKey(String or, String dest, String date, String time) {
		origin = or;
		destination = dest;
		this.date = date;
		this.time = time;

	}

	FlightKey(FlightKey other) {
		this(other.getOrigin(), other.getDestination(), other.getDate(), other.getTime());
	}

	// FILL IN CODE: Write getters for origin, destination, date and time

	public int compareTo(FlightKey o) {
		if (this.getOrigin().compareTo(o.getOrigin()) > 0) {
			return 1;
		} else if (this.getOrigin().compareTo(o.getOrigin()) < 0) {
			return -1;
		} else if (this.getDestination().compareTo(o.getDestination()) > 0) {
			return 1;
		} else if (this.getDestination().compareTo(o.getDestination()) < 0) {
			return -1;
		} else if (this.getDate().compareTo(o.getDate()) > 0) {
			return 1;
		} else if (this.getDate().compareTo(o.getDate()) < 0) {
			return -1;
		} else if (this.getTime().compareTo(o.getTime()) > 0) {
			return 1;
		} else if (this.getTime().compareTo(o.getTime()) < 0) {
			return -1;
		} else {
			return 0;
		}
	}
	
	public boolean flightCompareTo(FlightKey o) {
		if (this.getOrigin().compareTo(o.getOrigin()) == 0 &&
			this.getDestination().compareTo(o.getDestination()) == 0 &&
			this.getDate().compareTo(o.getDate()) == 0) {
			return true;
		} else {
			return false;
		}
			
	}
	public String toString() {
		return origin + " " + destination + " " + date + " " + time;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
