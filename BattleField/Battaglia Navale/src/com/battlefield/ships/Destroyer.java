package com.battlefield.ships;

import java.util.TreeSet;

import com.battlefield.boards.Cell;
import com.battlefield.settings.Words;

/**
 * @author dubkov
 *
 */
public class Destroyer extends Ship{
	
	
	
	/**
	 * constructor
	 * @param listOfCells is core of ship
	 */
	public Destroyer(TreeSet<Cell> listOfCells) {
		super(verificationSizeDestroyer(listOfCells),Words.DESTROYER);
	}
	private static TreeSet<Cell> verificationSizeDestroyer(TreeSet<Cell> treeSetCells){
		if(treeSetCells==null||treeSetCells.size()!=Words.MAX_SPACE_DESTROYER_AND_SUBMARINE) {
			throw new IllegalArgumentException();
		}return treeSetCells;
	}
	
	@Override
	public String toString() {
		return "Destroyer [TypeShip=" + getTypeShip() + ", Space=" + getSpaceOccupied()
				+ ", Class=" + getClass() + "]";
	}
	
	

}
