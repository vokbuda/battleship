package com.battlefield.boards;

import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.battlefield.player.Player;
import com.battlefield.settings.Status;
import com.battlefield.settings.Words;
import com.battlefield.ships.Ship;
import com.battlefield.ships.ShipHelper;
import com.battlefield.ships.ShipRepository;

/**
 * @author dubkov
 *
 */
public class BoardForDefence extends Board{
	
	/**
	 * Creates Board For Defence which belongs to concrete player
	 * @param player owner of this board 
	 */
	public BoardForDefence(Player player) {
		super(player);
		
	}
	/**
	 *  Removes all ships from defense board, replaces all ships with null values
	 */
	public void removeAllShips() {
		startTable.replaceAll((key, oldValue)
                -> null);
	}
	/**
	 *  implemented for attack any cell
	 * @param cell With this parameter it is possible to change state of Defence Board of enemies
	 */
	public void removeCellByShot(Cell cell) {
		Ship oldShip=(Ship) super.playerOwner.getBoardForDefence().startTable.get(cell);
		super.playerOwner.getBoardForDefence().startTable.put(cell, Words.DESTROYED);
		oldShip.getSpaceOccupied().remove(cell);
		if(oldShip.getSpaceOccupied().size()==0) {
			super.playerOwner.getShipRepo().removeShip(oldShip);
		}
	}
	
	/**
	 * @param cellsOccupied
	 * @return true if cells(lie vertically) don't touch any ship
	 */
	private boolean verticalCheckShip(TreeSet cellsOccupied) {
		Cell first=(Cell) cellsOccupied.first();
		Cell last=(Cell) cellsOccupied.last();
		int startIteration=first.getIdNumber()-1;
		char letter=first.getLetter();
		int finishIteration=last.getIdNumber()+1;
		List arrayChars=Words.listOfLetters;
		char letter_start=(char) arrayChars.get(Math.max(arrayChars.indexOf(letter)-1, 0));
		char letter_finish=(char) arrayChars.get(Math.min(arrayChars.indexOf(letter)+1, arrayChars.size()-1));
		char[] lettersArray= {letter_start,letter,letter_finish};
		while (startIteration<=finishIteration) {
			for (char c : lettersArray) {
				if(super.startTable.get(new Cell(c,startIteration)) !=null) {
					return false;
				}
			}
			startIteration++;
		}
		
		return true;
	}
	/**
	 * @param cellsOccupied
	 * @return true if cells(lie horizontally) don't touch any ship
	 */
	private boolean horizontalCheckShip(TreeSet cellsOccupied) {
		if(cellsOccupied==null||cellsOccupied.size()<2) {
			return false;
		}
		Cell first=(Cell) cellsOccupied.first();
		Cell last=(Cell) cellsOccupied.last();
		List arrayChars=Words.listOfLetters;
		List intsList=Words.listOfInts;
		int startIteration=Math.max(intsList.indexOf(first.getIdNumber())-1, 0);
		int finishIteration=Math.min(intsList.size()-1, intsList.indexOf(last.getIdNumber())+1) ;
		char firstLetter=first.getLetter();
		char lastLetter=last.getLetter();
		int firstIdx=Math.max(arrayChars.indexOf(firstLetter)-1,0) ;
		int lastIdx=Math.min(arrayChars.size()-1, arrayChars.indexOf(lastLetter)+1) ;
		int[] idsToCheck= {startIteration,intsList.indexOf(first.getIdNumber()),finishIteration};
		while(firstIdx<=lastIdx) {
			for (int i : idsToCheck) {
				if(i<0||i==Words.listOfInts.size())
					continue;
				if(super.startTable.get(new Cell((char) Words.listOfLetters.get(firstIdx), i+1))!=null) {
					return false;
				}
			}
			firstIdx++;
		}
		return true;
	}
	
	/**
	 *  Adds ship to shipRepository
	 * @param toStart Cell from which one ships starts
	 * @param toFinish last Cell of ship
	 * @param shipRepo represents ShipRepo where all modification will apply
	 * @return Object with status and ship generated
	 */
	public Object[] generateShipOnTable(Cell toStart,Cell toFinish,ShipRepository shipRepo) {
		if(BoardValidator.isCellNotOutOfRange(toStart)&&BoardValidator.isCellNotOutOfRange(toFinish)) {
			int casellaStartId=toStart.getIdNumber();
			char charStart=toStart.getLetter();
			Ship shipToAdd=null;
			int casellaFinishId=toFinish.getIdNumber();
			List arrayChars=Words.listOfLetters;
			List arrayInts=Words.listOfInts;
			char charFinish=toFinish.getLetter();
			if(!BoardValidator.areProblemCells(casellaStartId, casellaFinishId, charStart, charFinish))
				return new Object[] {Status.ProblemCell,null};
			TreeSet<Cell> listOfCellsToInsert=new TreeSet<Cell>();
			if(casellaStartId==casellaFinishId) {
				int indexFirst=arrayChars.indexOf(charStart);
				int indexLast=arrayChars.indexOf(charFinish);
				List sublist;
				if(indexFirst<indexLast) {
					sublist=arrayChars.subList(indexFirst, indexLast+1);}
				else {
					sublist=arrayChars.subList(indexLast, indexFirst+1);}
				listOfCellsToInsert=(TreeSet<Cell>) sublist.stream().map(e->new Cell((char) e,casellaStartId)).collect(Collectors.toCollection(TreeSet::new));
				
				if(!horizontalCheckShip(listOfCellsToInsert)) {
					return new Object[] {Status.ProblemCell,null};
				}
			}else {
			int indexFirst=arrayInts.indexOf(casellaStartId);
			int indexLast=arrayInts.indexOf(casellaFinishId);
			int maxBetweenTwoCells=Math.max(indexFirst, indexLast);
			int minBetweenTwoCells=indexFirst+indexLast-maxBetweenTwoCells;
				while (minBetweenTwoCells<=maxBetweenTwoCells) {
					listOfCellsToInsert.add(new Cell(charStart, (int) arrayInts.get(minBetweenTwoCells)));
					minBetweenTwoCells++;
				}
				if(!verticalCheckShip(listOfCellsToInsert)) {
					return new Object[] {Status.ProblemCell};
				}		
			}
			Object[] result=ShipHelper.returnAppropriateShip(listOfCellsToInsert,shipRepo);
			if(!result[0].equals(Status.OK))
				return result;
			shipToAdd=(Ship) result[1];
			if(!shipRepo.checkQuantityIfAdd(shipToAdd))
				return new Object[] {Status.IsNotPossibleAddShip};
			BoardHelper.INSTANCE.addShipToBoardForDefence(shipToAdd, this);
			return new Object[] {Status.OK,shipToAdd};
		}
		return new Object[] {Status.ProblemCell};
		
	}
	/**
	 *  Removes ship by start Cell and finish Cell
	 * @param initialCell the first Cell which belongs to ship which method will remove
	 * @param finalCell the last Cell which belongs to ship which method will remove
	 * @return Status with removed ship
	 */
	public Object[] removeShipByInitialAndFinalCell(Cell initialCell,Cell finalCell) {
		if(super.startTable.get(initialCell)!=null&&super.startTable.get(finalCell)!=null&&super.startTable.get(finalCell).equals(super.startTable.get(initialCell))) {
			Ship shipToRemove=(Ship)super.startTable.get(finalCell);
			TreeSet<Cell> listCellsToRemove=shipToRemove.getSpaceOccupied();
			for (Cell cell : listCellsToRemove) {
				super.startTable.put(cell,null);
			}
			return new Object[] {Status.OK,shipToRemove};
		}else {
			return new Object[] {Status.ProblemCell};
		}
	}
	
	/**
	 *  Method removes Ship which contains cell
	 * @param removeShip with selected cell it is possible to remove concrete ship
	 * @return Status and ship to remove
	 */
	public Object[] removeShipByCell(Cell removeShip) {
		Ship shipToRemove=(Ship) super.startTable.get(removeShip);
		if(shipToRemove==null || !shipToRemove.getClass().getSuperclass().equals(Ship.class)) {
			return new Object[] {Status.IsNoShip};
		}
		TreeSet<Cell> cellsList=shipToRemove.getSpaceOccupied();
		for (Cell cell : cellsList) {
			super.startTable.put(cell, null);
		}
		return new Object[] {Status.OK,shipToRemove};
	}
		
	/**
	 *  Downgrades ship of player on board
	 * @param cell cell will be removed from ship to downgrade after all controls
	 * @param shipRepo ShipRepository where there will be happen updates
	 * @return Object with Status and downgraded ship and old one
	 */
	public Object[] downgradeShipWithCell(Cell cell,ShipRepository shipRepo) {
		if(BoardValidator.isCellNotOutOfRange(cell)&& startTable.get(cell)!=null&&shipRepo!=null) {
			Ship shipToDowngrade=(Ship) super.startTable.get(cell);
			if(shipToDowngrade.getSpaceOccupied().first().equals(cell)||shipToDowngrade.getSpaceOccupied().last().equals(cell)) {
				if(shipRepo.checkQuantityIfRemove(shipToDowngrade)) {
					shipRepo.removeShip(shipToDowngrade);
				}
				else {
					return new Object[] {Status.ProblemCell,null};
				}
				TreeSet<Cell> spaceOccupiedByShip=shipToDowngrade.getSpaceOccupied();
				spaceOccupiedByShip.remove(cell);
				Object[] resultReturnAppropriate=ShipHelper.returnAppropriateShip(spaceOccupiedByShip,shipRepo);
				if(resultReturnAppropriate[0]!=Status.OK) {
					return new Object[] {resultReturnAppropriate[0]};
				}
				Ship shipUpdated=(Ship) resultReturnAppropriate[1];
				if(!shipRepo.checkQuantityIfAdd(shipUpdated)||!shipRepo.checkQuantityIfRemove(shipToDowngrade)) {
					spaceOccupiedByShip.add(cell);
					return new Object[] {Status.IsNotPossibleModifyShip};
				}
				TreeSet<Cell> cellsToAdd=shipUpdated.getSpaceOccupied();
				for (Cell cellToUpdate : cellsToAdd) {
					super.startTable.put(cellToUpdate, shipUpdated);
				}
				super.startTable.put(cell, null);
				return new Object[] {Status.OK,shipToDowngrade,shipUpdated};
			}else {
				
				return new Object[] {Status.ProblemCell,null};
			}
		}
		return new Object[] {Status.ProblemCell,null};
	}
	 
	/**
	 *  Method removes one ship from ShipRepository and adds another
	 * @param cell to upgrade Ship
	 * @param shipRepo ShipRepository where can be found upgraded ship
	 * @return Object with status, added ship to shipRepository and deleted ship from ShipRepository
	 */
	public Object[] upgradeShipWithCell(Cell cell,ShipRepository shipRepo) {
		if(!BoardValidator.isCellNotOutOfRange(cell)) {
			return new Object[] {Status.ProblemCell};
		}
		if(!BoardValidator.checkDiagonalTouches(cell,startTable)) {
			return new Object[] {Status.IsNotPossibleModifyShip,null};
		}
		Object[] result=BoardValidator.checkShipNear(cell,startTable);
		if(result[0]!=Status.OK) {
			return new Object[] {result[0],null};
		}
		Ship shipToUpdate=(Ship) result[1];
		TreeSet<Cell> cellsInsideShipToUpdate=shipToUpdate.getSpaceOccupied();
		cellsInsideShipToUpdate.add(cell);
		Object[] returnedResult=ShipHelper.returnAppropriateShip(cellsInsideShipToUpdate,shipRepo);
		if(!returnedResult[0].equals(Status.OK)) {
			return returnedResult;
		}
		Ship newShip=(Ship) returnedResult[1];
		if(shipRepo.checkQuantityIfAdd(newShip)&&shipRepo.checkQuantityIfRemove(shipToUpdate)) {
			for (Cell cellToUpdate : newShip.getSpaceOccupied()) {
				super.startTable.put(cellToUpdate, newShip);
			}
			return new Object[] {Status.OK,shipToUpdate,newShip};
		}
		return new Object[] {Status.IsNotPossibleModifyShip };
	}
}
