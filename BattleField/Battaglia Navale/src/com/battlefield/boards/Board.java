package com.battlefield.boards;

import java.util.HashMap;
import java.util.Map;

import com.battlefield.player.Player;
import com.battlefield.settings.Words;

/**
 * @author dubkov
 *
 */
public abstract class Board {
	protected final Map<Cell,Object> startTable=new HashMap<>();
	Player playerOwner;
	
	
	/**
	 * @param playerOwner sets up owner of Board
	 */
	public Board(Player playerOwner) {
		this.playerOwner=playerOwner;
		for(int iI=0;iI<Words.listOfInts.size();iI++) {
			for (int iL=0;iL<Words.listOfLetters.size();iL++) {
				
				startTable.put(new Cell(Words.listOfLetters.get(iL),Words.listOfInts.get(iI) ), null);
			}
			
		}
	}
	/**
	 * @return hashmap with cells and object(corresponds to board)
	 */
	public Map getBoardMap() {
		return this.startTable;
	}
	
	
	/**
	 * StringBuilder is used because of better performance
	 * @return board with cells
	 */
	public final StringBuilder showBoard() {
		StringBuilder table=new StringBuilder("    ");
		for (char l : Words.listOfLetters) {
			table.append("| ");
			table.append(l);
			table.append(" ");
			
		}
		table.append("|\n-----");
		for (char l : Words.listOfLetters) {
			table.append("----");
			
			
		}
		table.append("\n");
		for (int index=1;index<=Words.listOfInts.size();index++) {
			table.append(" "+Words.listOfInts.get(index-1)+" ".repeat(3-String.valueOf(index).length()));
			for (char l : Words.listOfLetters) {
				Cell cell=new Cell(l,Words.listOfInts.get(index-1));
				
				Character appendvalue=Words.ownerToLiteral.get(this.startTable.get(cell));
				
				if(this.startTable.get(cell)!=null&& !this.startTable.get(cell).getClass().equals(String.class)) {
					appendvalue='+';
				}
				
				table.append("| "+appendvalue+' ');
			}
			table.append("\n-----");
			for (char l : Words.listOfLetters) {
				table.append("----");
			}
			table.append("\n");
		}
		return table;
	}
	
	
	

}
