package com.battlefield.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import com.battlefield.boards.BoardValidator;
import com.battlefield.boards.Cell;
import com.battlefield.settings.Status;
import com.battlefield.settings.Words;
import com.battlefield.ships.Ship;

/**
 * @author dubkov
 *
 */
public class Computer extends Player implements RandomAttack{
	private Map rappresentationCellsComputer=new HashMap<>();
	/**
	 *  this randomizer is created for better generation of random cells, uses thread
	 */
	private ThreadLocalRandom randomizer=ThreadLocalRandom.current();
	private List<Cell> keysMap;
	/**
	 *  cellsAttacksRemains is used for attack remains cells
	 */
	private List<Cell> cellsAttacksRemains;
	
	/**
	 * aim of this boolean is to differ computer instances
	 */
	public static int counterCreatedInstances;
	Computer(PlayerRepository repo) {
		super(Words.COMPUTER+(++counterCreatedInstances),repo);
		this.keysMap= (List) super.getBoardForDefence().getBoardMap().
				keySet().stream().
				collect(Collectors.toList());
		this.cellsAttacksRemains=(List) super.getBoardForAttack().getBoardMap().
				keySet().stream().
				collect(Collectors.toList());
		generateShipsOnTableDefence();
	}
	//Method written below is recursion
	/**
	 *  Generates ships on Defense board 
	 * 	Recursion function created because of problem when remaining ship can't be put on Defense Board(Ex. Patrol Boat and there is just one cell for that)
	 * @return Object with Status.OK 
	 *
	 */
	public Object[] generateShipsOnTableDefence() {
		this.keysMap= (List) super.getBoardForDefence().getBoardMap().
				keySet().stream().
				collect(Collectors.toList());
		Map shipsMap=(Map) Words.shipsWithSizes;
		List<String> typeShips= (List<String>) shipsMap.keySet().stream().collect(Collectors.toList());
		for (String typeShip : typeShips) {
			List characteristics=(List) shipsMap.get(typeShip);
			int quantityShips=(int) characteristics.get(1);
			//Below we are iterating over array of ships 
			int size=(int) characteristics.get(0);
			for (int i = 0; i < quantityShips; i++) {
				if (this.keysMap.size()<size) {
					super.removeAllShipsFromDefenceTable();
					return generateShipsOnTableDefence();
				}else {
					generateRandomShip(size);
				}
			}
		}
		return new Object[]{Status.OK};
		
		
		
		//Here we need generate ship on board automatically
		
		
	}
	/**
	 *  Generates random ship by size
	 * @param size
	 * @return void
	 */
	private void generateRandomShip(int size) {
		if(size>=0) {
		outerLoop:
		while(true) {
			Cell startCell=chooseRandomCell(this.keysMap);
			Object[] resultCells=cellsFourDirections(startCell, size);
			while(!resultCells[0].equals(Status.OK)) {
				startCell=chooseRandomCell(this.keysMap);
				resultCells=cellsFourDirections(startCell,size);
			}
			List<Cell> listCells=(List<Cell>) resultCells[1];
			for (Cell cell : listCells) {
				Object[] result=super.addShip(startCell, cell);
				if(result[0]==Status.OK) {
					Ship ship=(Ship) result[1];
					Cell firstCell=ship.getSpaceOccupied().first();
					Cell lastCell=ship.getSpaceOccupied().last();
					List<Cell> cellsToRemove=new ArrayList<>();
					if(firstCell.getIdNumber()==lastCell.getIdNumber()) {
						cellsToRemove=horizontalShip(ship.getSpaceOccupied());
					}else {
						cellsToRemove=verticalShip(ship.getSpaceOccupied());
					}
					this.keysMap.removeAll(cellsToRemove);
					return;
				}
				
			}
		}
		}
		
	}
	/**
	 *  Generates cells for vertical ship
	 * @param cellsOccupied
	 * @return List with cells
	 */
	private List verticalShip(TreeSet cellsOccupied) {
		if(cellsOccupied==null||cellsOccupied.size()==0) {
			throw new RuntimeException(Status.NullObject.getResult());
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
		List<Cell> listCells=new ArrayList<>();
		while (startIteration<=finishIteration) {
			for (char c : lettersArray) {
				listCells.add(new Cell(c,startIteration)) ;
			}
			startIteration++;
		}
		
		return listCells;
	}
	
	/**
	 *  Generates horizontal cells for ship
	 * @param cellsOccupied
	 * @return List with cells
	 */
	private List horizontalShip(TreeSet cellsOccupied) {
		if(cellsOccupied==null||cellsOccupied.size()==0) {
			throw new RuntimeException(Status.NullObject.getResult());
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
		List<Cell> listOfCellsToRemove=new ArrayList<>();
		while(firstIdx<=lastIdx) {
			for (int i : idsToCheck) {
				if(i<0||i==Words.listOfInts.size())
					continue;
				listOfCellsToRemove.add(new Cell((char) Words.listOfLetters.get(firstIdx), i+1));
			}
			firstIdx++;
		}
		return listOfCellsToRemove;
		
	}
	
	/**
	 *  Generation of cells for ship of certain length
	 * @param cell finds 4 cells depending on lengthShip
	 * @param lengthShip helps to define last cell
	 * @return Object with status and list of cells if 
	 */
	public Object[] cellsFourDirections(Cell cell, int lengthShip) {
		if(BoardValidator.isCellNotOutOfRange(cell)&&lengthShip>1) {
			lengthShip--;
			List<Cell> cellsList=new ArrayList<>();
			List idInts=Words.listOfInts;
			List idLetters=Words.listOfLetters;
			int idCell=cell.getIdNumber();//here we need index of this value
			char letterCell=cell.getLetter();//here we need index of this
			int indexLetter=idLetters.indexOf(letterCell);
			int indexIdCell=idInts.indexOf(idCell);
			if(indexLetter+lengthShip<idLetters.size()) {
				cellsList.add(new Cell((char) idLetters.get(indexLetter+lengthShip),idCell));
			}
			if (indexLetter-lengthShip>=0) {
				cellsList.add(new Cell((char) idLetters.get(indexLetter-lengthShip),idCell));
			}
			if(indexIdCell+lengthShip<idInts.size()) {
				cellsList.add(new Cell(letterCell,(int) idInts.get(indexIdCell+lengthShip)));
			}
			if (indexIdCell-lengthShip>=0) {
				cellsList.add(new Cell(letterCell,(int) idInts.get(indexIdCell-lengthShip)));
			}
			if(cellsList.size()!=0) {
				return new Object[] {Status.OK,cellsList};
			}
			return new Object[] {Status.ProblemCell};
		}
		return new Object[] {Status.ProblemCell};
	}
	/**
	 * @param listCells selects random cell depending on list
	 * @return cell to attack from valid listCells
	 */
	public Cell chooseRandomCell(List listCells) {
		if(listCells==null) {
			throw new RuntimeException(Status.NullObject.getResult());
		}
		return (Cell) listCells.get(randomizer.nextInt(0,listCells.size()));
		
	}
	//Also we should generate attack which can be used for attack not used cells
	
	/**
	 *  attacks All other players
	 */
	@Override
	public void randomAttackCell() {
		Cell cellToAttack=chooseRandomCell(cellsAttacksRemains);
		cellsAttacksRemains.remove(cellToAttack);
		boardForAttack.attack(cellToAttack, super.getPlayerRepo(), (Player)this);		
	}
	/**
	 * @return List of cells with remains cells to attack
	 */
	public List<Cell> getCellsAttacksRemains() {
		List<Cell> listCellsRemains=new ArrayList<>(cellsAttacksRemains);
		return listCellsRemains;
	}
	@Override
	public String toString() {
		return "Computer [Name=" + getName() + ", Type=" + getClass() + "]";
	}

}
