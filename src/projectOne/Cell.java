/*
 * @Author: Chandler Hiatt
 * Cell Class
 * Last edited: 9-1-16
 * 
 */
package projectOne;

import javax.swing.JLabel;

public class Cell {

	private int toxLevel;
	private JLabel cell;
	private String color;
	private boolean toxic;
	
	public Cell(JLabel _cell, String _color){
		this.cell = _cell;
		this.toxLevel = 0;
		this.color = _color;
		this.toxic = false;
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
	public int getToxLevel(){
		return toxLevel;
	}
	public void setToxLevel(int i){
		this.toxLevel = i;
	}
	public boolean isToxic(){
		return toxic;
	}
	public JLabel getLabel(){
		return cell;
	}
}
