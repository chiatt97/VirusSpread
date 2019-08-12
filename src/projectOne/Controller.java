/*
 * @Author: Chandler Hiatt
 * Controller Class
 * Last edited: 9-1-16
 * 
 */
package projectOne;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Controller {
	private JPanel gridPanel;
	private Grid grid;
	private JLabel[][] cellLabels = new JLabel[25][25];
	private int[][] nums = new int[25][25];
	private int count = 1;
	private int sum;
	private View view;
	
	public Controller(Grid _grid){
		this.grid = _grid;
	}
	public void update(){
		gridPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(25,25,0,0));
		doubleVals();
		virusSpread();
		toxLevelSum();
		nums = grid.getVals();
		
		for(int i =0; i < 25; i++){
			for(int j =0; j < 25; j++){
				cellLabels[i][j] = new JLabel("",SwingConstants.CENTER);
				cellLabels[i][j].setBorder(new LineBorder(Color.black));
				cellLabels[i][j].setBackground(Color.decode(""+changeColors(nums[i][j])));
				cellLabels[i][j].setOpaque(true);
				int row = i+1;
				int col = j+1;
				
				//Add mouseListener to each individual cell so you can check 
				//its position and its Toxicity level on click.
				
				cellLabels[i][j].addMouseListener(new MouseAdapter() { 
				      public void mousePressed(MouseEvent e) { 
				          view.setCellTox(((JLabel) e.getSource()).getName()); 
				          view.setCellCol(col);
				          view.setCellRow(row);
				        } 
				      });
				
				//Update cells with new values
				
				cellLabels[i][j].setName(""+nums[i][j]);
				gridPanel.add(cellLabels[i][j]);
			}
		}
		grid.setGrid(gridPanel);
	}
	
	public void doubleVals(){
		int[][] oldNums = grid.getVals();
		for(int i = 0; i < 25; i++){
			for(int j = 0; j < 25; j++){
				int temp = oldNums[i][j];
				temp *= 2;
				nums[i][j] = temp; 
			}
		}
		grid.setVals(nums);
	}
	public void virusSpread(){
		int[][] oldNums = grid.getVals();
		boolean[][] isSpread = new boolean[25][25];
		for (int i=0; i<25; i++) 
		{
			for (int j=0; j<25; j++) 
			{
				if(oldNums[i][j] > 1000){
					for (int x=-1; x<2; x++) 
					{
						for (int y=-1; y<2; y++) 
						{
							if (valid(i,j,x,y))
							{
								//if(isSpread[i+x][j+y] == false){
									oldNums[i+x][j+y] += 10;
									//isSpread[i+x][j+y] = true;
								//}
							}	
						}
					}
				}
			}
		}
		grid.setVals(oldNums);
	}
	
	private boolean valid(int i, int j, int x, int y)
	{
		boolean result = true;
		if ((x==0) && (y==0))
			result = false;
		else if (((i+x)<0) || ((i+x)>=25))
			result = false;
		else if (((j+y)<0) || ((j+y)>=25))
			result = false;
		
		return result;
	}
	public String changeColors(int i){
		String color = "#99ff99";
		if(i > 200){
			color = "#1aff66";
			if(i > 500){
				color = "#ffff33";
				if(i > 1000){
					color = "#ffa500";
					if(i > 4000){
						color = "#ff0000";
					}
				}
			}
		}
		return color;
	}
	public int toxLevelSum(){
		int[][] nums = grid.getVals();
		for(int i = 0; i < 25; i++){
			for(int j = 0; j < 25; j++){
				sum += nums[i][j];
			}
		}
		return sum;
	}
	public double getTime(){
		double timePassed = count * 6.3;
		count++;
		return timePassed;
	}
	public int getNumGens(){
		return count - 1;
	}
	public void setView(View _view){
		this.view = _view;
	}
}
