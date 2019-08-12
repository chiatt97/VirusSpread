/*
 * @Author: Chandler Hiatt
 * Thread
 * Last edited: 9-1-16
 * 
 */
package projectOne;

import javax.swing.JFrame;

public class myThread extends Thread {
	   private View viewObj;
	   private Grid gridObj;
	   private Controller c;
	   private JFrame frame;
	   
	   public myThread(View _view, Grid _grid, Controller _c) {
	      this.viewObj = _view;
	      this.gridObj = _grid;
	      this.c = _c;
	   }
	   
	   public void run() {
		  frame = viewObj.getFrame();
	      try {
	    	  while(viewObj.getNext() != false){
	    		  	
	    			frame.remove(gridObj.getGrid());
	    				frame.validate();
	    				frame.repaint();
	    			//Alter Values
	    				c.update();
	    				viewObj.setTox();
	    				viewObj.setTime();
	    				viewObj.setNumGens();
	    				
	    			
	    			//Repaint grid with new values
	    			frame.add(gridObj.getGrid());
	    				frame.validate();
	    				frame.repaint();
	    				
	    				
	    				viewObj.setFrame(frame);
	    				viewObj.stop();
	    				sleep(3000);
	    				
	    		}
	         
	      }
	      catch (InterruptedException e) {}
	   }
}

