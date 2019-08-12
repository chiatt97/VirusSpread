/*
 * @Author: Chandler Hiatt
 * View Class
 * Last edited: 9-1-16
 * 
 */
package projectOne;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class View {
	private JFrame mainFrame;
	private JPanel top, footer, topLeft, topRight, topCenter;
	private JButton exitButton, nextButton/*, bacteriaOne, bacteriaTwo, bacteriaThree*/;
	private JLabel toxLevelLabel, timePassed, numGens, col, row, toxLabel;
	private Controller contObj;
	private myThread thread;
	private Grid _grid;
	private int toxLevel;
	
	public View(Grid _grid, Controller c){
		this.contObj = c;
		this._grid = _grid;
		thread = new myThread(this, _grid, c);
		c.setView(this);
	}
	public void start(){
		
		//Add all the Panels to the mainFrame
		
		mainFrame = new JFrame("Project 1");
		mainFrame.setBackground(Color.black);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setSize(1050, 842);
		//mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	
		top = new JPanel();
		top.setLayout(new BorderLayout());
			top.setBorder(new EmptyBorder(10, 10, 0, 10));
			top.setPreferredSize(new Dimension(0, 75));
			top.setBackground(Color.decode("#000000"));
			
			topLeft = new JPanel();
				topLeft.setBackground(Color.black);
				topLeft.add(new JLabel(""));
				
			topCenter = new JPanel();
				topCenter.setLayout(new BoxLayout(topCenter ,BoxLayout.Y_AXIS));
				topCenter.setBackground(Color.black);
					
					toxLevelLabel = new JLabel("Toxicity Level: " + toxLevel);
						toxLevelLabel.setForeground(Color.white);
						toxLevelLabel.setHorizontalAlignment(SwingConstants.CENTER);
					
					timePassed = new JLabel("Time Passed: 0 minutes");
						timePassed.setForeground(Color.white);
						timePassed.setHorizontalAlignment(SwingConstants.CENTER);
					
					numGens = new JLabel("Generations: 0");
						numGens.setForeground(Color.white);
						
					topCenter.add(toxLevelLabel);
					topCenter.add(new JLabel(""));
					topCenter.add(timePassed);
					topCenter.add(numGens);
					
			topRight = new JPanel();
			topRight.setLayout(new BoxLayout(topRight ,BoxLayout.Y_AXIS));
				topRight.setBackground(Color.black);
					col= new JLabel("Column: 0");
						col.setForeground(Color.white);
					row = new JLabel("Row: 0");
						row.setForeground(Color.white);
					toxLabel = new JLabel("Tox Level: 0");
						toxLabel.setForeground(Color.white);
						
			topRight.add(col);
			topRight.add(row);
			topRight.add(toxLabel);
					top.add(topLeft);
					top.add(topCenter, BorderLayout.WEST);
					top.add(topRight, BorderLayout.EAST);
					setTox();
		/*
		 * 
		 * Footer
		 * 
		 */
		footer = new JPanel();
		//Exit Button
		exitButton = new JButton("Exit");
		exitButton.setHorizontalAlignment(SwingConstants.LEFT);
			exitButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
		         }
			});
		//Go Button
		nextButton = new JButton("Go");
			nextButton.setHorizontalAlignment(SwingConstants.RIGHT);
				nextButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						thread.start();
						
			         }
				});
		//
		//Add the contents to the footer
		//
		footer.add(exitButton/*, BorderLayout.EAST*/);
		footer.add(nextButton/*, BorderLayout.WEST*/);
		footer.setPreferredSize(new Dimension(0, 45));
		footer.setBackground(Color.decode("#000000"));
	//
	//Add Components to mainFrame
	//
	mainFrame.add(top, BorderLayout.PAGE_START);	mainFrame.add(footer, BorderLayout.PAGE_END);
	mainFrame.add(_grid.getGrid(), BorderLayout.CENTER);
	mainFrame.setVisible(true);
	}
	/*
	 * 
	 * 
	 * 
	 * 
	 * Getters
	 * and
	 * Setters
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public JFrame getFrame(){
		return this.mainFrame;
	}
	public void setFrame(JFrame frame){
		mainFrame = frame;
		mainFrame.repaint();
	}
	public Boolean getNext(){
		return this.nextButton.isEnabled();
	}
	public void setTox(){
		this.toxLevelLabel.setText("Toxicity Level: " + contObj.toxLevelSum());
	}
	public void setTime(){
		this.timePassed.setText("Time Passed: " + contObj.getTime() + " minutes");
	}
	public void setNumGens(){
		this.numGens.setText("Generations: " + contObj.getNumGens());
	}
	public void setCellCol(int col){
		this.col.setText("Column: " + col);
	}
	public void setCellRow(int row){
		this.row.setText("Row: " + row);
	}
	public void setCellTox(String tox){
		this.toxLabel.setText("Tox Level: " + tox);
	}
	/*
	 * 
	 * Check and see if tox level is over 10000000
	 * 
	 */
	public void stop(){
		if(contObj.toxLevelSum() > 10000000){
		this.nextButton.setEnabled(false);
		}
	}
}

