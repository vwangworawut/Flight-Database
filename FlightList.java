import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/** The class that represents the flight database using a skip list */
public class FlightList {
	
	private FlightNode head;
	private FlightNode tail;
	private int height;
	// FILL IN CODE: needs to store the head, the tail and height of the skip
	// list


	public FlightList() {
		FlightKey head_key = new FlightKey("aaa", "aaa", "aaa", "aaa");
		FlightData head_data = new FlightData("aaa", 0.0);
		FlightNode head_ = new FlightNode (head_key, head_data);

		FlightKey tail_key = new FlightKey("zzz", "zzz", "zzz", "zzz");
		FlightData tail_data = new FlightData("zzz", 0.0);
		FlightNode tail_ = new FlightNode(tail_key, tail_data);
		
		head_.setNext(tail_);
		tail_.setPrev(head_);
		head = head_;
		tail = tail_;
		
		height = 1;

		
	}

	/** Reads flight data from the file and inserts it into this skip list */
	public FlightList(String filename) {
		this();
		String line = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			while ((line = reader.readLine()) != null) {
				String banana[] = line.split(" ");
				FlightKey key = new FlightKey(banana[0], banana[1], banana[2], banana[3]);
				FlightData data = new FlightData(banana[4], Double.parseDouble(banana[5]));
				insert(key,data);
			}
		} catch(IOException e) {
			System.out.println(e);
		}		
	}

	/**
	 * Returns true if the node with the given key exists in the skip list,
	 * false otherwise. This method needs to be efficient.
	 * 
	 * @param key
	 * @return
	 */
	public boolean find(FlightKey key) {
		FlightNode current = head;
		while (true) {
			while (current.getNext().getKey() != this.tail.getKey() 
					&& current.getNext().getKey().compareTo(key) <= 0) {
				current = current.getNext();
			}
			if (current.getKey().compareTo(key) == 0) {
				return true;
			}
			if (current.getDown() != null) {
				current = current.getDown();
			} else {
				return false;
			}
		}
	}

	public FlightNode find_node_place(FlightKey key) {
		FlightNode current = head;
		while (true) {
			while (current.getNext().getKey() != this.tail.getKey() 
					&& current.getNext().getKey().compareTo(key) <= 0) {
				current = current.getNext();
			}
			if (current.getDown() != null) {
				current = current.getDown();
			} else {
				break;
			}
		}
		return current;
	}


	/**
	 * Insert a (key, value) pair to the skip list. Returns true if it was able
	 * to insert.
	 */
	public boolean insert(FlightKey key, FlightData data) {
		int Height = 1;
		int head = 0;
		Random r = new Random();
		
		FlightNode current = find_node_place(key);
		if (current.getKey().compareTo(key) == 0) {
			return false;
		}
		FlightNode inserted = new FlightNode(key,data);
		inserted.setNext(current.getNext());
		inserted.setPrev(current);
		current.getNext().setPrev(inserted);
		current.setNext(inserted);

		while (r.nextInt(2) == head) {
			if (Height >= this.height) {
				FlightKey head_key = new FlightKey("aaa", "aaa", "aaa", "aaa");
				FlightData head_data = new FlightData("aaa", 0.0);
				FlightNode head_ = new FlightNode (head_key, head_data);

				FlightKey tail_key = new FlightKey("zzz", "zzz", "zzz", "zzz");
				FlightData tail_data = new FlightData("zzz", 0.0);
				FlightNode tail_ = new FlightNode(tail_key, tail_data);
				
				head_.setNext(tail_);
				head_.setDown(this.head);
				tail_.setPrev(head_);
				tail_.setDown(this.tail);
				this.head.setUp(head_);
				this.tail.setUp(tail_);
				this.head = head_;
				this.tail = tail_;
		
				this.height ++;
			}
			FlightNode top = new FlightNode(key,data);
			
			while (current.getUp() == null) {
				current = current.getPrev();
			}
			current = current.getUp();
			
			top.setPrev(current);
			top.setNext(current.getNext());
			top.setDown(inserted);

			current.getNext().setPrev(top);
			current.setNext(top);
			top.setUp(inserted);
			
			inserted = top;
			Height++;
		}
	
		return true; // don't forget to change it
	}

	/**
	 * Returns the list of nodes that are successors of a given key. Refer to
	 * the project pdf for a detailed description of the method.
	 * 
	 * @param key
	 * @return
	 */
	public ArrayList<FlightNode> successors(FlightKey key) {
		ArrayList<FlightNode> arr = new ArrayList<FlightNode>();
		FlightNode current = this.find_node_place(key);
		while (current.getDown() != null) {
			current = current.getDown();
		}
		while (current.getNext() != null) {
			current = current.getNext();
			if (current.getKey().flightCompareTo(key) == true) {
				int curr_time = Integer.parseInt(current.getKey().getTime().substring(0, 2));
				int key_time = Integer.parseInt(key.getTime().substring(0, 2));
				if (curr_time > key_time) {
					arr.add(current);
				}
			}
		}
		return arr;
	}

	/**
	 * Returns the list of nodes that are predecessors of a given key. Refer to
	 * the project pdf for a detailed description of the method.
	 * 
	 * @param key
	 * @return
	 */
	public ArrayList<FlightNode> predecessors(FlightKey key) {
		ArrayList<FlightNode> arr = new ArrayList<FlightNode>();
		FlightNode current = this.head;
		FlightNode s = this.find_node_place(key);
		while (current.getDown() != null) {
			current = current.getDown();
		}
		while (current.getNext() != s) {
			current = current.getNext();
			if (current.getKey().flightCompareTo(key) == true) {
				int curr_time = Integer.parseInt(current.getKey().getTime().substring(0, 2));
				int key_time = Integer.parseInt(key.getTime().substring(0, 2));
				if (curr_time < key_time) {
					arr.add(current);
				}
			}
		}
		current = s;
		int curr_time = Integer.parseInt(current.getKey().getTime().substring(0, 2));
		int key_time = Integer.parseInt(key.getTime().substring(0, 2));
		if (curr_time < key_time) 
			arr.add(current);
			
		return arr;
	}

	/**
	 * Prints the SkipList (prints the elements on all levels starting at the
	 * top. Each level should be printed on a separate line.
	 */
	public void print() {
		int Height = this.height;
		FlightNode current = this.head;
		FlightNode head = this.head;
		for (int i = 0; i < Height; i++) {
			while (current != null) {
				System.out.print(current.getKey() + " ");
				current = current.getNext();
			}
			System.out.println();
			head = head.getDown();
			current = head;
		}
	}

	/**
	 * Returns a list of nodes that have the same origin and destination cities
	 * and the same date as the key, with departure times within the given time
	 * frame of the departure time of the key.
	 */
	public ArrayList<FlightNode> findFlights(FlightKey key, int timeFrame) {
		ArrayList<FlightNode> resFlights = new ArrayList<FlightNode>();
		int key_time = Integer.parseInt(key.getTime().substring(0, 2));
		ArrayList<FlightNode> pred = this.predecessors2(key);
		ArrayList<FlightNode> succ = this.successors2(key);
		
		
		for (FlightNode predecessor : pred) {
			int time_pred = Integer.parseInt(predecessor.getKey().getTime().substring(0, 2));
			if (Math.abs(key_time - time_pred) <= timeFrame) {
				resFlights.add(predecessor);
			}
		}
		for (FlightNode successor : succ) {
			int time_succ = Integer.parseInt(successor.getKey().getTime().substring(0, 2));
			if (Math.abs(key_time - time_succ) <= timeFrame) {
				resFlights.add(successor);
			}
		}

		return resFlights;
	}

	//returns array with the key included for find flights
	public ArrayList<FlightNode> predecessors2(FlightKey key) {
		ArrayList<FlightNode> arr = new ArrayList<FlightNode>();
		FlightNode current = this.head;
		FlightNode s = this.find_node_place(key);
		while (current.getDown() != null) {
			current = current.getDown();
		}
		while (current.getNext() != s) {
			current = current.getNext();
			if (current.getKey().flightCompareTo(key) == true) {
				int curr_time = Integer.parseInt(current.getKey().getTime().substring(0, 2));
				int key_time = Integer.parseInt(key.getTime().substring(0, 2));
				if (curr_time < key_time) {
					arr.add(current);
				}
			}
		}
		current = s;
		int curr_time = Integer.parseInt(current.getKey().getTime().substring(0, 2));
		int key_time = Integer.parseInt(key.getTime().substring(0, 2));
		if (curr_time <= key_time) 
			arr.add(current);
			
		return arr;
	}

	public ArrayList<FlightNode> successors2(FlightKey key) {
		ArrayList<FlightNode> arr = new ArrayList<FlightNode>();
		FlightNode current = this.find_node_place(key);
		while (current.getDown() != null) {
			current = current.getDown();
		}
		while (current.getNext() != null) {
			current = current.getNext();
			if (current.getKey().flightCompareTo(key) == true) {
				int curr_time = Integer.parseInt(current.getKey().getTime().substring(0, 2));
				int key_time = Integer.parseInt(key.getTime().substring(0, 2));
				if (curr_time >= key_time) {
					arr.add(current);
				}
			}
		}
		return arr;
	}
}
