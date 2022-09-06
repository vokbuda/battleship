package com.battlefield.player;

import com.battlefield.boards.BoardValidator;
import com.battlefield.boards.Cell;
import com.battlefield.printer.Printer;
import com.battlefield.settings.Status;
import com.battlefield.ships.Ship;

/**
 * @author dubkov
 *
 */
public class Human extends Player {

	/**
	 * @param name passed for user identification
	 * @param playerRepo which user belongs to
	 */
	public Human(String name, PlayerRepository playerRepo) {
		
		super(name, playerRepo);
		
	}
	/**
	 *  Downgrades ship cancelling certain cell(parameter) from ship
	 * @param cellToRemoveFromShip is cell to delete from ship
	 * @return Status.OK in case of success and Status.ProblemCell in any other case
	 */
	public Status downgradeShip(Cell cellToRemoveFromShip) {
		if(cellToRemoveFromShip==null) {
			throw new RuntimeException(Status.NullObject.getResult());
		}
		if(!BoardValidator.isCellNotOutOfRange(cellToRemoveFromShip)) {
			throw new RuntimeException(Status.CellIsOutOfRange.getResult());
		}
		
		Object[] resultTableDowngradeShip=this.boardForDefence.downgradeShipWithCell(cellToRemoveFromShip, shipRepo);
		if(!resultTableDowngradeShip[0].equals(Status.OK)) {
			return (Status) resultTableDowngradeShip[0];
		}
		Ship shipToRemove=(Ship)resultTableDowngradeShip[1];
		Ship shipToAdd=(Ship)resultTableDowngradeShip[2];
		Printer.INSTANCE.ShipBefore(shipToRemove.getTypeShip());
		Printer.INSTANCE.ShipAfter(shipToAdd.getTypeShip());
		shipRepo.removeShip((Ship)resultTableDowngradeShip[1]);
		shipRepo.add((Ship)resultTableDowngradeShip[2]);
		return Status.OK;
		
		
	}
	/**
	 *  Upgrades ship by addition cell to ship
	 * @param cellAddtoShip needs to be added to ship to upgrade
	 * @return Status OK, if operation is executed with success
	 */
	public Status upgradeShip(Cell cellAddtoShip) {
		Object[] resultTableUpdateShip=this.boardForDefence.upgradeShipWithCell(cellAddtoShip, shipRepo);
		if(!resultTableUpdateShip[0].equals(Status.OK)) {
			return (Status) resultTableUpdateShip[0];
		}
		Ship oldShip=(Ship)resultTableUpdateShip[1];
		Ship newShip=(Ship) resultTableUpdateShip[2];
		Printer.INSTANCE.ShipBefore(oldShip.getTypeShip());
		Printer.INSTANCE.ShipAfter(newShip.getTypeShip());
		return shipRepo.update(oldShip,newShip);	
	}
	/**
	 *  Removes cells from user's defence board
	 * @param cellToStart initial cell of ship
	 * @param cellToFinish last cell of ship
	 * @return Status.OK
	 */
	public Status removeShipByInitialAndFinalCell(Cell cellToStart,Cell cellToFinish) {
		Object[] result=this.boardForDefence.removeShipByInitialAndFinalCell(cellToStart, cellToFinish);
		if(!result[0].equals(Status.OK)) {
			return (Status) result[0];
		}
		Ship shipToRemove=(Ship) result[1];
		this.shipRepo.removeShip(shipToRemove);
		return Status.OK;
	}
	/**
	 * 
	 * @return returns available ships for addition to the board
	 */
	public StringBuilder getAvailableShips() {
		return this.shipRepo.getAvailableShips();
	}
	/**
	 *  Removes ship from defence board by cell associated with
	 * @param cellToRemove cell which will be removed from ship
	 * @return Status
	 */
	public Status removeShipByCell(Cell cellToRemove) {
		Object[] shipToRemoveResult=this.boardForDefence.removeShipByCell(cellToRemove);
		if (shipToRemoveResult[0].equals(Status.OK)) {
			Ship shipToRemove=(Ship) shipToRemoveResult[1];
			this.shipRepo.removeShip(shipToRemove);
			return Status.OK;
			}
		else {
			return (Status)shipToRemoveResult[0];
		}
		
		
	}
	@Override
	public String toString() {
		return "Human [Name=" + getName() + ", Type=" + getClass() + "]";
	}

}
