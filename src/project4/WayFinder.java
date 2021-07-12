package project4;

import java.util.ArrayList;
import java.util.LinkedList;
public class WayFinder {

/** This class is our main program (and only class for this project)!
 *The program expects at least 2 command line arguments and will check
 *to see that they are valid entries.
 *If they are not the program will terminate
 *
 *Afterwards it calls our wrapper method which in turn calls our 
 *recursive function
*
* @author Diana Yepes!
*/
	
	
	//global variable to keep track of how many ways through maze
	static int count=0;
	
	
	/**
	 * This method serves as a wrapper method that calls the recursive function.
	 * @param arr: an integer array --the puzzle we are trying to solve
	 * creates the parameters for our recursive function 
	 */
	public static void callRecursive (int[]arr) {
		LinkedList<ArrayList<String>> storage1 =new LinkedList<ArrayList<String>>();
		LinkedList<Integer> storage2=new LinkedList<Integer>();
		int pos=0;
		//our call to the recursive function
		recursive(arr,pos,storage1,storage2);
		//if count is not 0, there are ways through maze
		if(count!=0) {
			System.out.println("There are "+count+" ways through the puzzle.");
			
		}
		//if count is still 0, no ways through
		else {
			System.out.println("No way through this puzzle");
		}
	}
	
	
	/**
	 * This is the recursive function
	 * @param arr,pos,storage1,storage2: an array of integers, an integer variable,
	 * a stack of ArrayLists, and a stack of integers
	 *The two stacks are meant to keep track of what paths we've taken
	 *the integer pos is meant to keep track of what index we're at
	 */	
	public static void recursive (int[]arr,int pos,LinkedList<ArrayList<String>> storage1,LinkedList<Integer> storage2) {
		//a base case
		//if we reach the last index we have found one path
		//we print out the formatted arrays we stored in storage1
		
		if(pos==arr.length-1) {
			for(int i=0;i<storage1.size();i++) {
				System.out.println(storage1.get(storage1.size()-1-i));
			}
			//increment count since we found way through maze
			count=count+1;
			System.out.println();
		}
		
		//if the index can go left and that left value isn't already in our index storage2
		//we create our formatted array through steps, and push it to storage1
		//we push the left index to our index stack storage2
		if(pos-arr[pos]>=0 && !storage2.contains(pos-arr[pos])) {
			storage1.push((steps(pos,"L",arr)));
			storage2.push(pos-arr[pos]);
			//recurse with new left position and new stacks
			recursive(arr,pos-arr[pos],storage1,storage2);
			//if recursion ends we want to remove them from stacks: path ended
			storage2.pop();
			storage1.pop();
		}
		
		//if the index can go right and that right value isn't already in our index storage2
		//we create our formatted array through steps, and push it to storage1
		//we push the right index to our index stack storage2
		if(pos+arr[pos]<arr.length && !storage2.contains(pos+arr[pos])) {
			storage1.push((steps(pos,"R",arr)));
			storage2.push(pos+arr[pos]);
			//recurse with new right position
			recursive(arr,pos+arr[pos],storage1,storage2);
			//if recursion ends we want to remove them from stacks: path ended
			storage2.pop();
			storage1.pop();
		}
		
	}

	
	/**
	 * This is a method to format the string Arraylists
	 *We want each arraylist to have an "R" or "L" next to the entry performing an operation
	 * @param val: the index of the element in the puzzle that needs the "L" or "R"
	 * Left_Right: the String, either an "R" or "L". arr: our puzzle array
	 * @return a formatted String arraylist
	 */	
	public static ArrayList<String> steps (int val,String Left_right, int [] arr){
		ArrayList<String> formattedArr= new ArrayList<String>();
		//go through integer array, and if we get to the index, we concatenate it with the "L" or "R"
		for (int i=0;i<arr.length;i++) {
			if(i==val) {
				formattedArr.add(" "+arr[val]+Left_right);
			}
			//otherwise simply add it to Arraylist with spaces
			else {
				formattedArr.add(" "+arr[i]+" ");
			}
		}
		return formattedArr;
	}
	
	
	/**
	 * The main() method of this program. 
	 * @param args array of Strings provided on the command line when the program is started; 
	 * the inputs should all be numbers
	 */	
	public static void main(String[] args) {
		//checks that there are at least 2 command line inputs
		if (args.length < 2) {
			System.err.println("ERROR: Command line input must be at least 2 numbers!");
			System.exit (1);
		}
		//create an array of integers to transfer command line data into array
		int[] inputVals=new int[args.length];
		
		//go though command line args and try to convert the strings to integers for our created int array
		//if not possible, catch exceptions and terminate
		for(int i=0;i<args.length;i++) {
			try {
				inputVals[i]=Integer.parseInt(args[i]);
			}
			catch(NumberFormatException | NullPointerException e) {
				System.err.println("ERROR: Command line input must be integers!");
				System.exit (1);
			}
			//if values in array are negative or greater than 99, terminate
			if(inputVals[i]<0 || inputVals[i]>99) {
				System.err.println("ERROR: Command line input numbers must only be between 0-99!");
				System.exit (1);
			}
		}
		//finally, if last value is not 0, terminate
		if(inputVals[inputVals.length-1]!=0) {
			System.err.println("ERROR: Last command line input number must be 0!");
			System.exit (1);
		}
		//call our recursive function
		callRecursive(inputVals);	
	}
}
