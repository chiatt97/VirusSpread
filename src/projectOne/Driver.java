/*
 * @Author: Chandler Hiatt
 * Driver Class
 * Last edited: 9-1-16
 * 
 */
package projectOne;

public class Driver {

	public static void main(String[] args) {
		
		Grid gridObj = new Grid();
	    Controller contObj = new Controller(gridObj);
		View viewObj = new View(gridObj, contObj);
		
		viewObj.start();
	}

}
