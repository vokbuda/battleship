package com.battlefield.boards;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import com.battlefield.settings.Status;
import com.battlefield.settings.Words;
import com.battlefield.ships.Ship;

/**
 * @author dubkov
 *
 */
public enum BoardValidator {
	/**
	 * With that INSTANCE there is possible to call methods inside of enum
	 */
	INSTANCE;
	/**
	 * 
	 * @param idNumberFirst id number of the first Cell
	 * @param idNumberLast id number of the last Cell
	 * @param letterFirst id letter of the first Cell
	 * @param letterLast id letter of the last Cell
	 * @return true if (letterFirst,idNumberFirst) and (letterLast,idNumberLast) lie on the same line
	 */
	public static boolean areProblemCells(int idNumberFirst,int idNumberLast,char letterFirst,char letterLast) {
		if(Words.listOfLetters.indexOf(letterFirst)==-1) {
			return false;
		}
		if(Words.listOfLetters.indexOf(letterLast)==-1) {
			return false;
		}
		if(letterFirst==letterLast)
			return true;
		else if (idNumberFirst==idNumberLast)
			return true;
		return false;
	}
	/**
	 * 
	 * @param cellsOccupied cells to be passed as parameter for check if there is some ship on adjacent cells
	 * @param boardForDefence Board for Defence where cells will be controlled
	 * @return true if it is possible to add horizontal ship(represented by cellsOccupied) to boardDefence
	 */
	public static boolean horizontalCheckShip(TreeSet cellsOccupied,BoardForDefence boardForDefence) {
		if(cellsOccupied==null&&boardForDefence==null) {
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
				if(boardForDefence.getBoardMap().get(new Cell((char) Words.listOfLetters.get(firstIdx), i+1))!=null) {
					return false;
				}
			}
			firstIdx++;
		}
		return true;
	}
	/**
	 * @param cellsOccupied cells to be passed as parameter for check if there is some ship on adjacent cells
	 * @param boardForDefence Board for Defence where cells will be controlled
	 * @return true if it is possible to add vertical ship(represented by cellsOccupied) to boardDefence
	 */
	public static boolean verticalCheckShip(TreeSet cellsOccupied,BoardForDefence boardForDefence) {
		if(cellsOccupied==null&&boardForDefence==null) {
			return false;
		}
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
				if(boardForDefence.getBoardMap().get(new Cell(c,startIteration)) !=null) {
					return false;
				}
			}
			startIteration++;
		}
		return true;
	}
	/**
	 * @param cell to count touches with
	 * @param startTable board where controls will be executed
	 * @return true if it's diagonal cells with ships
	 */
	public static boolean checkDiagonalTouches(Cell cell,Map startTable) {
		if(cell!=null&&startTable!=null&&BoardValidator.isCellNotOutOfRange(cell)) {
		char letterCell=cell.getLetter();
		int idCell=cell.getIdNumber();
		List lettersCells=Arrays.asList(Words.listOfLetters);
		List idsCells=Arrays.asList(Words.listOfInts);
		Cell leftUpCell=null;
		Cell rightUpCell=null;
		Cell leftDownCell=null;
		Cell rightDownCell=null;
		int indexUpCell=idsCells.indexOf(idCell)-1;
		int indexLetterleft=lettersCells.indexOf(letterCell)-1;
		int indexLetterRight=lettersCells.indexOf(letterCell)+1;
		int indexDownCell=idsCells.indexOf(idCell)+1;
		//Below we we'll check our letters
		if(indexUpCell>=0) {
			if(indexLetterleft>=0) {
				char letterLeft=(char) lettersCells.get(indexLetterleft);
				leftUpCell=new Cell(letterLeft,indexUpCell);
			}
			if(indexLetterRight>0) {
				char letterRight=(char) lettersCells.get(indexLetterRight);
				rightUpCell=new Cell(letterRight,indexUpCell);
			}
			
		}
		if(indexDownCell<idsCells.size()) {
			if(indexLetterleft>=0) {
				char letterLeft=(char) lettersCells.get(indexLetterleft);
				leftDownCell=new Cell(letterLeft,indexUpCell);
			}
			if (indexLetterRight>0) {
				char letterRight=(char) lettersCells.get(indexLetterRight);
				rightDownCell=new Cell(letterRight,indexUpCell);
			}
		}
		if(leftUpCell!=null&&startTable.get(leftUpCell)!=null) {
			return false;
		}
		else if (rightUpCell!=null&& startTable.get(rightUpCell)!=null) {
			return false;
		}
		else if (rightDownCell!=null&& startTable.get(rightDownCell)!=null) {
			return false;
		}
		else if(leftDownCell!=null&& startTable.get(leftDownCell)!=null) {
			return false;
		}
		return true;}
		return false;
	}
	/**
	 * @param cell
	 * @return true if row exists under cell(parameter)
	 */
	private static boolean ifDownRowExists(Cell cell) {
		if(Words.listOfInts.indexOf(cell.getIdNumber())+1>=Words.listOfLetters.size()){
			return  false;
		}
		return true;
	}
	/**
	 * @param cell
	 * @return true if row exists above cell(parameter)
	 */
	private static boolean ifUpRowExists(Cell cell) {
		if(Words.listOfInts.indexOf(cell.getIdNumber())-1<0){
			return  false;
		}
		return true;
	}
	/**
	 * @param cell
	 * @return true if column exists on the left of cell(parameter)
	 */
	private static boolean ifLeftColumnExists(Cell cell) {
		if(Words.listOfLetters.indexOf(cell.getLetter())-1<0){
			return  false;
		}
		
		return true;
	}
	/**
	 * @param cell
	 * @return true if column exists on the right of cell(parameter)
	 */
	private static boolean ifRightColumnExists(Cell cell) {
		if(Words.listOfLetters.indexOf(cell.getLetter())+1>=Words.listOfLetters.size()){
			return  false;
		}
		return true;
	}
	/**
	 * @param cell to count touches with
	 * @param startTable board to count if there are touches inside
	 * @return true if there is no ship near the cell
	 */
	public static Object[] checkShipNear(Cell cell,Map startTable) {
		int touches=0;
		int idIntCell=cell.getIdNumber();
		char charCell=cell.getLetter();
		List lettersCells=Words.listOfLetters;
		List idsCells=Words.listOfInts;
		Ship shipToReturn=null;
		if(ifLeftColumnExists(cell)) {
			char leftLetter=(char) lettersCells.get(lettersCells.indexOf(charCell)-1);
			Cell leftCell=new Cell(leftLetter,idIntCell);
			if((Ship) startTable.get(leftCell)!=null) {
				shipToReturn=(Ship) startTable.get(leftCell);
				touches++;
			}
		}
		if(ifRightColumnExists(cell)) {
			char rightLetter=(char) lettersCells.get(lettersCells.indexOf(charCell)+1);
			Cell rightCell=new Cell(rightLetter,idIntCell);
			if((Ship) startTable.get(rightCell)!=null) {
				shipToReturn=(Ship) startTable.get(rightCell);
				touches++;
			}
		}
		if(ifUpRowExists(cell)) {
			int topIdCell=(int) idsCells.get(idsCells.indexOf(idIntCell)-1);
			Cell topCell=new Cell(charCell,topIdCell);		
			if((Ship) startTable.get(topCell)!=null) {
				shipToReturn=(Ship) startTable.get(topCell);
				touches++;
			}
		}
		if(ifDownRowExists(cell)) {
			int downIdCell=(int) idsCells.get(idsCells.indexOf(idIntCell)+1);
			Cell downCell=new Cell(charCell,downIdCell);
			if((Ship) startTable.get(downCell)!=null) {
				shipToReturn=(Ship) startTable.get(downCell);
				touches++;
			}	
		}
		if(touches==1) {
			return new Object[] {Status.OK,shipToReturn};
		}
		return new Object[] {Status.ProblemCell};
	}
	/**
	 * @param checkCell will be passed for control if cell is out of range or not
	 * @return true if cell exists on table
	 */
	public static boolean isCellNotOutOfRange(Cell checkCell) {
		if(Words.listOfInts.indexOf(checkCell.getIdNumber())!=-1&&Words.listOfLetters.indexOf(checkCell.getLetter())!=-1){
			return true;
		}
		return false;
	}
}
