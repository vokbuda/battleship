package com.battlefield.ships;

import java.util.TreeSet;

import com.battlefield.boards.Cell;
import com.battlefield.settings.Words;

/**
 * @author dubkov
 *
 */
public class Battleship extends Ship {
	
	
	/**
	 * constructor
	 * @param treeSetCells cells created for Battleship
	 */
	public Battleship(TreeSet<Cell> treeSetCells) {
		super(verificationSizeBattleShip(treeSetCells),Words.BATTLESHIP);
	}
	/**
	 * @param treeSetCells
	 * @return treeSetCells if quantity of cells corresponds to the type of ship 
	 */
	private static TreeSet<Cell> verificationSizeBattleShip(TreeSet<Cell> treeSetCells){
		if(treeSetCells==null||treeSetCells.size()!=Words.MAX_SPACE_BATTLESHIP) {
			throw new IllegalArgumentException();
		}return treeSetCells;
	}

	

	@Override
	public String toString() {
		return "Battleship [TypeShip=" + getTypeShip() + ", Space=" + getSpaceOccupied()
				+ ", Class=" + getClass() + "]";
	}


	
	

	
	

}
