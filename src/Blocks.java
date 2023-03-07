import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TimeZone;
/**
 * @author Bek
 * Blocks Object containing Block number, miner, transaction number, and timeStamp.
 *
 */
public class Blocks implements Comparable<Blocks>	{
	/**
	 * Block number.
	 */
	private int number; 
	/**
	 * ID of the miner who updated the block.
	 */
	private String miner; 
	/**
	 * arraylist containing all the blocks objects read from the file.
	 */
	private static ArrayList<Blocks> blocks; 
	
	
	/**
	 * a string containing a comma with no space.
	 */
	private final static String DELIMETER = ",";
	
	/**
	 * unix timeStamp, number of seconds since 1970 January 1.
	 */
	private long timeStamp;
	
	/**
	 * transaction number.
	 */
	public int transactions;
	
	
	/**
	 * constructs an empty Blocks object.
	 */
	public Blocks() { 
		
		
		 number = 0;
		 miner = null;
		 
		} 
	
	
	
	/**
	 * @param number block number
	 * This one constructs a block object, initialize the block number with the given number, and also initializes the miner null (keep it empty).
	 */
	public Blocks(int number) {
		
		
		 
		this.number = number;
		miner = null;
	} 
	
	/**
	 * @param number block number
	 * @param miner ID of the miner who updated the block
	 * This one constructs a block object, initialize the block number with the given number, and also initializes the miner with the given miner.
	 */
	public Blocks(int number, String miner) {
		this.number = number;
		this.miner = miner;
	} 
	
	
	/**
	 * @param number = a given block number.
	 * @param miner = a given miner ID of the miner who updated the block.
	 * @param timestamp = a given number which is a number of seconds since 1970 January 1.
	 * @param transactions = a given transactions number.
	 */
	public Blocks(int number, String miner, long timestamp, int transactions) {
		this.number = number;
		this.miner = miner;
		this.timeStamp = timestamp;
		this.transactions = transactions;
	}
	
	
	/**
	 * a helper method to convert from int to string.
	 * @param num = a given number to convert to string
	 * @return a string representation of int, if the int is 1 digit, adds a leading 0.
	 */
	private static String intToString(int num) {
	    String output = Integer.toString(num);
	    while (output.length() < 2) output = "0" + output;
	    return output;
	}
	
	
	/**
	 * @return Blocks number.
	 */
	public int getNumber() {
		return this.number;
	} 
	
	/**
	 * @return miner's ID.
	 */
	public String getMiner() {
		return this.miner;
	} 
	
	/**
	 * @return a copy of the blocks array.
	 */
	public static ArrayList<Blocks> getBlocks() {
		ArrayList<Blocks> blockCopy = new ArrayList<Blocks> (blocks.size());
		for (int i =0; i < blocks.size(); i++) {
			blockCopy.add(blocks.get(i));
		}
		return blockCopy;
	} 
	
	/**
	 * @return the transaction number of of the Blocks object.
	 */
	public int getTransactions() {
		return this.transactions;
	}
	
	/**
	 * @return a string representation of the Date in a format of "EEE, dd MMMMM YYYY HH:mm:ss z", also in CST timezone.
	 */
	public String getDate() {
		
		SimpleDateFormat form = new SimpleDateFormat("EEE, dd MMMMM YYYY HH:mm:ss z");
		Date d = new Date(timeStamp * 1000);
		form.setTimeZone(TimeZone.getTimeZone("CST"));
		
		return form.format(d);
	}
	
	
	/**
	 * prints the number of miners who updated the blockchain.
	 */
	public static void calUniqMiners() {
		int count;
		int result = 1;
		int i;
		int j;
		ArrayList <String> miners = new ArrayList<String>();
		ArrayList <Integer> freq = new ArrayList<Integer>();
		miners.add(blocks.get(0).getMiner());
		for (i = 1; i < blocks.size(); i++) {
			count = 0;
			for (j = i - 1; j >= 0; j--) {
				if (blocks.get(i).getMiner().equalsIgnoreCase(blocks.get(j).getMiner())) {
					count++;
				}
			}
			if ( count == 0) {
				result++;
				miners.add(blocks.get(i).getMiner());
			}
		}
		
		
		
		for (i = 0; i < miners.size(); i++) {
			count = 0;
			for (j=0; j <blocks.size();j++) {
				if (miners.get(i).equalsIgnoreCase(blocks.get(j).getMiner())) {
					count++;
				}
			}
			
			freq.add(count);
		}
		
		System.out.println("Number of unique Miners: " + result);
		System.out.println("");
		System.out.println("Each unique Miner and its frequency:");
		for (i = 0; i < miners.size(); i++) {
			System.out.println("Miner Address: " + miners.get(i));
			System.out.println("Miner Frequency: " + freq.get(i));
			if (i != miners.size()-1) {
			System.out.println("");
			}
		}
		
		
	} 
	
	/**
	 * @param A a given Blocks object.
	 * @param B a given Blocks object.
	 * @return the difference between the numbers of 2 given Blocks objects.
	 */
	public static int blockDiff(Blocks A, Blocks B) {
		return A.getNumber() - B.getNumber();
	} 
	
	/**
	 * @param num a given number.
	 * @return A Blocks object whose number is the same as the given number.
	 */
	public static Blocks getBlockByNumber (int num) {
		
		int count = 0;
		int index = 0;
		if (blocks == null) {
			return null;
		}
		else if (blocks != null) {
		for(int i = 0; i< blocks.size(); i++) {
			if (blocks.get(i).getNumber() == num) {
				index = i;
				count++;
			}
		}
		if (count == 0) {
			return null;
		}
		}
		
		
		return blocks.get(index);
	} 
	
	/**
	 * @param filename a given name of the file.
	 * @throws IOException = throws an exception if file does not exist.
	 */
	public static void readFile(String filename) throws IOException {
		String [] column;
	
		blocks = new ArrayList<Blocks>();
		Scanner scn = new Scanner (new FileReader (filename));
		Blocks a;
		
		while(scn.hasNextLine()) {
			column = scn.nextLine().split(DELIMETER);
			a = new Blocks(Integer.parseInt(column[0]), column[9], Long.valueOf(column[16]), Integer.parseInt(column[17]));
			blocks.add(a);
			
		}
		scn.close();
	} 
	
	/**
	 * @return returns a string containing about the information of the given Blocks object.
	 * 
	 */
	public String toString() {
		String result = "";
		if (this.number == 0 && this.miner == null) {
			result = "Empty Block";
		}
		else if (this.number != 0 && this.miner == null) {
			result = "Block Number: " + String.valueOf(number);
		}
		else if (this.number != 0 && this.miner != null){
			result = "Block Number: " + String.valueOf(number) + " Miner Address: " + miner;
		}
		return result;
	}

	
	@Override
	public int compareTo(Blocks o) {
		int a = this.getClass().getName().compareTo(o.getClass().getName());
		
		double b = this.number - (o.number);
		int c = (int) b;
		
		if (a == 0) {
			return c;
		}
		
		return a;
	} 
	
	/**
	 * sorts the blocks list in terms of their number.
	 */
	public static void sortBlocksByNumber() {
		
		blocks.sort(null);
		
		
	}
	
	/**
	 * @param first a given Blocks object.
	 * @param second a given Blocks object.
	 * prints out the difference in time between the first and the second Blocks object.
	 */
	public static void timeDiff(Blocks first, Blocks second) {
		if ( first == null) {
			System.out.print( "A given Block is null.");
			return;
		}
		else if ( second == null) {
			System.out.print("A given Block is null.");
			return;
		}
		
		long hour = Math.abs(first.timeStamp - second.timeStamp)/3600;
		
		long minute = (Math.abs(first.timeStamp - second.timeStamp)%3600)/60;
		
		long sec = ((Math.abs(first.timeStamp - second.timeStamp)%3600)%60);
		
		System.out.print("The difference in time between Block " + first.getNumber() + " and Block " + 
		second.getNumber() + " is "); 
		if (hour == 1) {
			System.out.print(hour + " hour, "  );
		}
		else {
			System.out.print(hour + " hours, "  );
		}
		
		if (minute == 1) {
			System.out.print(minute + " minute, and "  );
		}
		else {
			System.out.print(minute + " minutes, and "  );
		}
		
		if (sec == 1) {
			System.out.println(sec + " second."  );
		}
		
		else {
			System.out.println(sec + " seconds."  );
		}
		
		
		
		
		
		
		
	}
	
	/**
	 * @param a given Blocks object.
	 * @param a given Blocks object.
	 * @return the sum of all transaction numbers between two Blocks objects.
	 */
	public static int transactionDiff(Blocks first, Blocks second) {
		if (first == null || second == null) {
			return -1;
		}
		else if (first.getNumber() >= second.getNumber()) {
			return -1;
		}
		int sum = 0;
		
		for (int i = 1; i < second.getNumber() - first.getNumber();i++) {
			sum = sum + Blocks.getBlockByNumber(first.getNumber() + i).getTransactions();
		}
		
		
		return sum;
	}
	
	
	
	
	
	
	
}