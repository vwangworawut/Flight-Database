/**
 * The class that represents a node in a flight skip list. 
 * Contains the key of type FlightKey and the data of type FlightData. 
 * Also stores the following pointers to FlightNode(s): next, down, prev and up.
 */
public class FlightNode {
	
	private FlightKey key;
	private FlightData data;
	private FlightNode next, down, prev, up;
	// FILL IN CODE, declare instance variables (make them private)

	FlightNode(FlightNode node) {
		this(node.getKey(), node.getData());
	}

	FlightNode(FlightKey key, FlightData data) {
		this.key = key;
		this.data = data;
		next = null;
		down = null;
		prev = null;
		up = null;
	}

	public FlightKey getKey() {
		return key;
	}

	public void setKey(FlightKey key) {
		this.key = key;
	}

	public FlightData getData() {
		return data;
	}

	public void setData(FlightData data) {
		this.data = data;
	}

	public FlightNode getNext() {
		return next;
	}

	public void setNext(FlightNode next) {
		this.next = next;
	}

	public FlightNode getDown() {
		return down;
	}

	public void setDown(FlightNode down) {
		this.down = down;
	}

	public FlightNode getPrev() {
		return prev;
	}

	public void setPrev(FlightNode prev) {
		this.prev = prev;
	}

	public FlightNode getUp() {
		return up;
	}

	public void setUp(FlightNode up) {
		this.up = up;
	}

}
