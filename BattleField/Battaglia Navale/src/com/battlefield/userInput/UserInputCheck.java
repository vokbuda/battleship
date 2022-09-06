package com.battlefield.userInput;

import com.battlefield.settings.Words;

/**
 * @author dubkov
 *
 */
public enum UserInputCheck {
	/**
	 * represents instance of current enum
	 */
	INSTANCE;
	/**
	 * @param insertedString  checks this initial string
	 * @return true if format of cell is correct
	 */
	public boolean checkInputCell(String insertedString) {
		if(insertedString.contains(" ")) {
			if(Character.isLetter(insertedString.charAt(0))) {
				try{
					Integer.parseInt(insertedString.substring(2));
					
					return true;
				} catch(NumberFormatException ex){
					return false;
				}	
			}
			return false;
		}
		return false;
	}
	/**
	 * @param charId cell's char id
	 * @param intId cell's int id
	 * @return boolean if cell is in range
	 */
	public boolean checkCellInputRange(char charId,int intId) {
		if(Words.listOfLetters.indexOf(Character.toUpperCase(charId))!=-1&&Words.listOfInts.indexOf(intId)!=-1) {
			return true;
		}
		return false;
	}
	/**
	 * @param scan inserted number in scanner
	 * @param size is size of menu
	 * @return would return true if pressed menu is in rage
	 */
	public boolean checkIntInputRange(int scan,int size) {
		return scan<=size+1&&scan>0;
		
	}
	/**
	 * @param scan inserted number
	 * @param size in base of this parameter method checked if scan is less than size+2 and bigger than 0
	 * @return would return true if quit was pressed by user
	 */
	public boolean checkIntInputRangeQuit(int scan,int size) {
		return scan<=size+2&&scan>0;
	}

}
