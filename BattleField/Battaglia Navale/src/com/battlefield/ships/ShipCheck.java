package com.battlefield.ships;

import java.util.TreeSet;

import com.battlefield.boards.Cell;
import com.battlefield.settings.Status;
import com.battlefield.settings.Words;

/**
 * @author dubkov
 *
 */
public enum ShipCheck {
	/**
	 * Gives access to all methods inside
	 */
	INSTANCE;
	/**
	 * @param treeSetCells cells to represent ship
	 * @return Status.OK if ship is Carrier
	 */
	public static Status checkCarrier(TreeSet<Cell> treeSetCells) {
		if(treeSetCells.size()==Words.MAX_SPACE_CARRIER) {
			return Status.OK;
		}
		return Status.isNoAppropriateShip;
		
	}
	/**
	 * @param treeSetCells cells to represent ship
	 * @return Status.OK if ship is Battleship
	 */
	public static Status checkBattleship(TreeSet<Cell> treeSetCells) {
		if(treeSetCells.size()==Words.MAX_SPACE_BATTLESHIP) {
			return Status.OK;
		}
		return Status.isNoAppropriateShip;
		
	}
	/**
	 * @param treeSetCells cells to represent ship
	 * @return Status.OK if ship is Destroyer
	 */
	public static Status checkDestroyer(TreeSet<Cell> treeSetCells) {
		if(treeSetCells.size()==Words.MAX_SPACE_DESTROYER_AND_SUBMARINE) {
			return Status.OK;
		}
		return Status.isNoAppropriateShip;
		
	}
	/**
	 * @param treeSetCells cells to represent ship
	 * @return Status.OK if ship is Submarine
	 */
	public static Status checkSubmarine(TreeSet<Cell> treeSetCells) {
		if(treeSetCells.size()==Words.MAX_SPACE_DESTROYER_AND_SUBMARINE) {
			return Status.OK;
		}
		return Status.isNoAppropriateShip;
		
	}	
	
	/**
	 *  @param treeSetCells cells to represent ship
	 *  @return Status.OK if ship is PatrolBoat
	 */
	public static Status checkPatrolBoat(TreeSet<Cell> treeSetCells) {
		if(treeSetCells.size()==Words.MAX_SPACE_PATROL_BOAT) {
			return Status.OK;
		}
		return Status.isNoAppropriateShip;
		
	}	
	
	

}
