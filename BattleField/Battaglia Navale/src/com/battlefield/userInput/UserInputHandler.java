package com.battlefield.userInput;

import java.util.Scanner;

import com.battlefield.boards.Cell;
import com.battlefield.settings.Status;

/**
 * @author dubkov
 *
 */
public enum UserInputHandler {
	/**
	 * represents instance of current enum
	 */
	INSTANCE;
	private static Scanner scanner=new Scanner(System.in);
	
	/**
	 *  Takes cell in input
	 * @return Object (Status, Cell)
	 */
	public Object[] cellInput() {
		String scannedString="";
		
		while(scannedString.isEmpty()&&scanner.hasNextLine()) {
			scannedString=scanner.nextLine();
		}
		if(UserInputCheck.INSTANCE.checkInputCell(scannedString)) {
			char idLetterCell=scannedString.charAt(0);
			int idIntCell=Integer.parseInt(scannedString.substring(2));
			if(UserInputCheck.INSTANCE.checkCellInputRange(idLetterCell,idIntCell)) {
				return new Object[] {Status.OK,new Cell(Character.toUpperCase(idLetterCell),idIntCell)};
			}
			return new Object[] {Status.ProblemCell,null};
			
		}else {
			
			return new Object[] {Status.ProblemCell,null};	
		}
		
	}
	/**
	 * @param size inserted int from keyboard
	 * @return Status.OK with int relative to number of Menu
	 */
	public Object[] numberMenuInputWithBackTurnOff(int size) {
		try {
			String scannedString=scanner.nextLine();
			while(scannedString.isEmpty()) {
				scannedString=scanner.nextLine();
			}
			int menuIntScanned=Integer.parseInt(scannedString);
			
			if(UserInputCheck.INSTANCE.checkIntInputRangeQuit(menuIntScanned, size)) {
				return new Object[] {Status.OK,menuIntScanned-1};
			}
			return new Object[] {Status.NumberIsNotValid};
		}catch(Exception e) {
			
			
			return new Object[] {Status.NumberIsNotValid};
		}
	}
	/**
	 *  takes number(corresponding to certain menu number) in input
	 * @param size number taken in input
	 * @return Status.OK if Menu 
	 */
	public Object[] numberMenuInput(int size) {
		try {
			String scannedString=scanner.nextLine();
			while(scannedString.isEmpty()) {
				scannedString=scanner.nextLine();
			}
			int menuIntScanned=Integer.parseInt(scannedString);
			
			if(UserInputCheck.INSTANCE.checkIntInputRange(menuIntScanned, size)) {
				return new Object[] {Status.OK,menuIntScanned-1};
			}
			return new Object[] {Status.NumberIsNotValid};
		}catch(Exception e) {
			return new Object[] {Status.NumberIsNotValid};
		}
		
		
	}
	/**
	 * @return Status.OK with inserted String if operation was successful
	 */
	public Object[] StringMenuInput() {
		String scannedString=scanner.nextLine();
		while(scannedString.isEmpty()) {
			scannedString=scanner.nextLine();
		}
		return new Object[] {Status.OK,scannedString};
	}
	
}
