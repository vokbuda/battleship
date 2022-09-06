package com.battlefield.ships;

import java.util.TreeSet;

import com.battlefield.boards.Cell;

/**
 * @author dubkov
 *
 */
public abstract class Ship {
	private TreeSet<Cell> spaceOccupied;
	private String typeShip;
	Ship(String typeShip){
		this.typeShip=typeShip;
		
	}
	Ship(TreeSet<Cell> listOfCells, String typeShip) {
		this.typeShip = typeShip;
		this.spaceOccupied = listOfCells;
	}
	/**
	 * @return Returns type of ship
	 */
	public String getTypeShip() {
		return this.typeShip;
	}

	

	/**
	 * @return returns cells inside of ship
	 */
	public TreeSet<Cell> getSpaceOccupied() {
		return this.spaceOccupied;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((spaceOccupied == null) ? 0 : spaceOccupied.hashCode());
		result = prime * result + ((typeShip == null) ? 0 : typeShip.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ship other = (Ship) obj;
		if (spaceOccupied == null) {
			if (other.spaceOccupied != null)
				return false;
		} else if (!spaceOccupied.equals(other.spaceOccupied))
			return false;
		if (typeShip == null) {
			if (other.typeShip != null)
				return false;
		} else if (!typeShip.equals(other.typeShip))
			return false;
		return true;
	}
}
