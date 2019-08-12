/*
 * @Author: Chandler Hiatt
 * Grid Class
 * Last edited: 9-1-16
 * 
 */
package projectOne;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Grid extends JLabel {

	private JPanel gridContainer = new JPanel();
	private JLabel[][] cellLabels = new JLabel[25][25];
	private int[][] nums = new int[25][25];
	private int randomNum;
	private String color = "white";
	private View view;
	
	public Grid(){
		Random rand = new Random();
		
		gridContainer.setLayout(new GridLayout(25,25,0,0));
		
		for(int i = 0; i < 25; i++){
			for(int j =0; j < 25; j++){
				randomNum = rand.nextInt(250);
				
				//Create new Cell Object to hold the JLabel
				
				Cell cellObj = new Cell(new JLabel("",SwingConstants.CENTER), color);

				//Assign attributes to 2d array of JLabels
				
				cellLabels[i][j] = cellObj.getLabel();
				cellLabels[i][j].setBorder(new LineBorder(Color.black));
				cellLabels[i][j].setBackground(Color.decode("#99ff99"));
				cellLabels[i][j].setOpaque(true);
				
				//nums is the array that holds the value of toxicity on each respected cell
				
				nums[i][j] = randomNum;
				cellLabels[i][j].setName(""+randomNum);
				gridContainer.add(cellLabels[i][j]);
			}
		}
	}
	/*
	 * 
	 * 
	 * Getters
	 * and
	 * Setters
	 * 
	 * 
	 * 
	 */
	public JPanel getGrid(){
		return gridContainer;
	}
	public void setGrid(JPanel _grid){
		this.gridContainer = _grid;
	}
	public void setView(View _view){
		this.view = _view; 
	}
	public View getView(){
		return this.view;
	}
	public int[][] getVals(){
		return nums;
	}
	public void setVals(int[][] _nums){
		this.nums = _nums;
	}
}
