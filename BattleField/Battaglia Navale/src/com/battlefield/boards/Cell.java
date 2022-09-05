package com.battlefield.boards;

import com.battlefield.settings.Status;

/**
 * @author dubkov
 *
 */
public class Cell implements Comparable<Cell>{
	private char letter;
	private int idNumber;
	/**
	 * constructor Cell
	 * @param letter represents Cell's letter id
	 * @param idNumber reprents Cell's number id
	 */
	public Cell(char letter, int idNumber) {
		
		this.letter = letter;
		this.idNumber = idNumber;
	}
	/**
	 * @return letter of Cell
	 */
	public char getLetter() {
		return letter;
	}
	
	/**
	 * @return number of Cell
	 */
	public int getIdNumber() {
		return idNumber;
	}
	@Override
	public String toString() {
		return String.format("Cell(%c, %d)", this.letter,this.idNumber);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idNumber;
		result = prime * result + Character.hashCode(letter);
		return result;
	}
	@Override 
	public boolean equals(Object o) {
		if (o == this)
		return true;
		if (!(o instanceof Cell))
		return false;
		Cell cell = (Cell)o;
		return cell.idNumber == idNumber && cell.letter == letter;
	}
	
	@Override
	public int compareTo(Cell o) {
		if(o==null)
			throw new RuntimeException(Status.NullObject.getResult());
		return (Integer.compare(o.idNumber, this.idNumber)+Integer.compare(o.letter, this.letter))*-1;
	}
}
