package com.battlefield.ships;

import java.util.List;
import java.util.TreeSet;

import com.battlefield.boards.Cell;
import com.battlefield.settings.Status;
import com.battlefield.settings.Words;

/**
 * @author 150224
 *
 */
public enum ShipHelper {
	/**
	 * Gives access to all methods inside
	 */
	INSTANCE;
	/**
	 * @param listOfCells core of ship to check
	 * @param shipRepo where there will be controlled if it is possible to add ship
	 * @return appropriate ship with status inside of array of objects
	 */
	public static Object[] returnAppropriateShip(TreeSet<Cell> listOfCells,ShipRepository shipRepo) {
		
		int sizeListOfCells = listOfCells.size();
		Ship shipToDefine = null;
		List<String> types=Words.mapSizeToString.get(sizeListOfCells);
		if(types!=null) {
			for (String typeString : types) {
				if(typeString.equals(Words.BATTLESHIP)) {
					shipToDefine=new Battleship(listOfCells);
					if(shipRepo.checkQuantityIfAdd(shipToDefine)) {
						return new Object[] {Status.OK,shipToDefine};
					}
					
				}
				if(typeString.equals(Words.DESTROYER)) {
					shipToDefine=new Destroyer(listOfCells);
					if(shipRepo.checkQuantityIfAdd(shipToDefine)) {
						return new Object[] {Status.OK,shipToDefine};
					}
					
				}
				if(typeString.equals(Words.PATROL_BOAT)) {
					
					shipToDefine=new PatrolBoat(listOfCells);
					if(shipRepo.checkQuantityIfAdd(shipToDefine)) {
						
						return new Object[] {Status.OK,shipToDefine};
					}
				}
				if(typeString.equals(Words.SUBMARINE)) {
					
					shipToDefine=new Submarine(listOfCells);
					
					if(shipRepo.checkQuantityIfAdd(shipToDefine)) {
						
						return new Object[] {Status.OK,shipToDefine};
					}
					
				}
				if(typeString.equals(Words.CARRIER)) {
					shipToDefine=new Carrier(listOfCells);
					if(shipRepo.checkQuantityIfAdd(shipToDefine)) {
						return new Object[] {Status.OK,shipToDefine};
					}
				}
			}
		}
		return new Object[] {Status.isNoAppropriateShip};	
	}

}
