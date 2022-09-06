package com.battlefield.ships;

import java.util.TreeSet;

import com.battlefield.boards.Cell;
import com.battlefield.settings.Words;

/**
 * @author dubkov
 *
 */
public class PatrolBoat extends Ship{
	
	/**
	 * constructor
	 * @param treeSetCells is core of PatrolBoat
	 */
	public PatrolBoat(TreeSet treeSetCells) {
		super(verificationSizePatrolBoat(treeSetCells),Words.PATROL_BOAT);
		
	}
	/**
	 * @param treeSetCells
	 * @return treeSetCells if quantity of cells corresponds to the type of ship 
	 */
	private static TreeSet<Cell> verificationSizePatrolBoat(TreeSet<Cell> treeSetCells){
		if(treeSetCells==null||treeSetCells.size()!=Words.MAX_SPACE_PATROL_BOAT) {
			throw new IllegalArgumentException();
		}return treeSetCells;
	}
	@Override
	public String toString() {
		return "PatrolBoat [TypeShip()=" + getTypeShip() + ", Space=" + getSpaceOccupied()
				+ ", Class=" + getClass() + "]";
	}
	

}
