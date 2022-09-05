package com.battlefield.boards;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.battlefield.settings.Status;
import com.battlefield.settings.Words;
import com.battlefield.ships.Ship;

/**
 * @author dubkov
 *
 */
public enum BoardHelper {
	/**
	 * represents instance of current one
	 */
	INSTANCE;
	/**
	 *  Generates cells for ship
	 * @param toStart the first cell of ship, can be last
	 * @param toFinish the last cell of ship, can be first
	 * @param boardForDefence board where generated ship will be added
	 * @return Object with TreeSet of cells to insert
	 */
	public Object[] generateSetForShip(Cell toStart,Cell toFinish,BoardForDefence boardForDefence){
		if(BoardValidator.isCellNotOutOfRange(toStart)&&BoardValidator.isCellNotOutOfRange(toFinish)) {
		Set<Cell> resultSet=new TreeSet();
		Ship shipToAdd=null;
		if(toStart.getIdNumber()==toFinish.getIdNumber()) {
			Object[] resultGenerationHorizontal=generateHorizontalShip(toStart, toFinish);
			if(resultGenerationHorizontal[0].equals(Status.OK)) {
				TreeSet<Cell> setOfCellsToInsert=(TreeSet<Cell>) resultGenerationHorizontal[1];
				if(!BoardValidator.horizontalCheckShip(setOfCellsToInsert,boardForDefence)) {
					return new Object[] {Status.ProblemCell,null};
				}
				return new Object[] {Status.OK,setOfCellsToInsert};
			}
			return new Object[] {resultGenerationHorizontal[0]};
			
		}
		if(toStart.getLetter()==toFinish.getLetter()) {
			Object[] resultGenerationVertical=generateVerticalShip(toStart, toFinish);
			if(resultGenerationVertical[0].equals(Status.OK)) {
				TreeSet<Cell> treeSetOfCellsToInsert=(TreeSet<Cell>) resultGenerationVertical[1];
				if(!BoardValidator.verticalCheckShip(treeSetOfCellsToInsert,boardForDefence)) {
					return new Object[] {Status.ProblemCell};
				}
				return new Object[] {Status.OK,treeSetOfCellsToInsert};
			}
			return new Object[] {resultGenerationVertical[0]};
		}
		return new Object[] {Status.ProblemCell};}
		return new Object[] {Status.ProblemCell};
	}
	/**
	 *  adds Ship to boardDefence
	 * @param ship ship which need to be added
	 * @param boardDefence board where cells will be updated with new values
	 * 
	 */
	public void addShipToBoardForDefence(Ship ship,BoardForDefence boardDefence) {
		if(ship!=null&&boardDefence!=null) {
		TreeSet<Cell> setOfSpaceOccupied=ship.getSpaceOccupied();
		for (Cell cell : setOfSpaceOccupied) {
			boardDefence.getBoardMap().put(cell, ship);
		}
		}
		
	}
	/**
	 *  generates cells for horizontal ship
	 * @param toStart the first Cell for horizontal ship
	 * @param toFinish the last Cell for horizontal ship
	 * @return Object with Status and cells generated for horizontal ship
	 */
	public Object[] generateHorizontalShip(Cell toStart,Cell toFinish){
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
		int indexFirst=arrayChars.indexOf(charStart);
		int indexLast=arrayChars.indexOf(charFinish);
		List sublist;
		if(indexFirst<indexLast) {
			sublist=arrayChars.subList(indexFirst, indexLast+1);}
		else {
			sublist=arrayChars.subList(indexLast, indexFirst+1);}
		listOfCellsToInsert=(TreeSet<Cell>) sublist.stream().map(e->new Cell((char) e,casellaStartId)).collect(Collectors.toCollection(TreeSet::new));
			
			
			
			
		return new Object[] {Status.OK,listOfCellsToInsert};
		}
		return new Object[] {Status.ProblemCell};
	}
	/**
	 * @param toStart the first Cell for horizontal ship
	 * @param toFinish the last Cell for horizontal ship
	 * @return Object with Status and TreeSet(cells inside)
	 */
	public Object[] generateVerticalShip(Cell toStart,Cell toFinish){
		if(BoardValidator.isCellNotOutOfRange(toStart)&&BoardValidator.isCellNotOutOfRange(toFinish)) {
			int casellaStartId=toStart.getIdNumber();
			char charStart=toStart.getLetter();
			int casellaFinishId=toFinish.getIdNumber();
			List arrayChars=Words.listOfLetters;
			List arrayInts=Words.listOfInts;
			char charFinish=toFinish.getLetter();
			TreeSet<Cell> treeSetOfCellsToInsert=new TreeSet<>();
			int maxBetweenTwoCells=Math.max(casellaFinishId, casellaStartId);
			int minBetweenTwoCells=casellaFinishId+casellaStartId-maxBetweenTwoCells;
			while (minBetweenTwoCells<=maxBetweenTwoCells) {
					treeSetOfCellsToInsert.add(new Cell(charStart, (int) arrayInts.get(minBetweenTwoCells-1)));
					minBetweenTwoCells++;
			}	
			return new Object[] {Status.OK,treeSetOfCellsToInsert};
		}
		return new Object[] {Status.ProblemCell};
		
	}

}
