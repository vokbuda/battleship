package com.battlefield.ships;

import java.util.TreeSet;

import com.battlefield.boards.Cell;
import com.battlefield.settings.Words;

/**
 * @author dubkov
 *
 */
public class Submarine extends Ship{
	
	
	/**
	 * constructor
	 * @param treeSetCells parameter is core of ship
	 */
	public Submarine(TreeSet treeSetCells) {
		super(verificationSizeSubmarine(treeSetCells),Words.SUBMARINE);
	}
	/**
	 * @param treeSetCells
	 * @return treeSetCells if quantity of cells corresponds to the type of ship 
	 */
	private static TreeSet<Cell> verificationSizeSubmarine(TreeSet<Cell> treeSetCells){
		if(treeSetCells==null||treeSetCells.size()!=Words.MAX_SPACE_SUBMARINE) {
			throw new IllegalArgumentException();
		}return treeSetCells;
	}
	

	@Override
	public String toString() {
		return "Submarine [TypeShip=" + getTypeShip() + ", Space=" + getSpaceOccupied()
				+ ", Class=" + getClass() + "]";
	}

	

	
	

}
