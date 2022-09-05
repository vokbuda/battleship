package com.battlefield.menu;

import java.util.List;

import com.battlefield.boards.BoardHelper;
import com.battlefield.boards.Cell;
import com.battlefield.game.Game;
import com.battlefield.printer.Printer;
import com.battlefield.settings.Status;
import com.battlefield.settings.Words;
import com.battlefield.userInput.UserInputHandler;

/**
 * @author dubkov
 *
 */
public enum MenuHelper {
	/**
	 * Represents instance of this enum
	 */
	INSTANCE;
	/**
	 *  Shows submenu's name in console
	 * @param listOfMenus shows menus depending on parameter listOfMenus
	 */
	public static void showMenusFromList(List<Menu> listOfMenus) {
		for (int i=0;i<listOfMenus.size();i++) {
			Printer.INSTANCE.labelFormatMenu(i,listOfMenus.get(i).label());
		}
		Printer.INSTANCE.labelFormatMenu(listOfMenus.size(),Quit.INSTANCE.quitString());
	}
	/**
	 *  Shows message for input Cell
	 */
	public static void showInputCell() {
		Printer.INSTANCE.printString(Words.InputCell);
	}
	/**
	 *  Shows message for input of the first cell
	 */
	public static void showFirstInputCell() {
		Printer.INSTANCE.printString(Words.InputFirstCell);
	}
	/**
	 *  Shows message for input of the second cell
	 */
	public static void showSecondInputCell() {
		Printer.INSTANCE.printString(Words.InputSecondCell);
	}
	/**
	 *  Shows menus with back option
	 * @param listOfMenus shows menus in console depending on parameter
	 */
	public static void showMenusFromListWithBack(List<Menu> listOfMenus) {
		for (int i=0;i<listOfMenus.size();i++) {
			Printer.INSTANCE.labelFormatMenu(i,listOfMenus.get(i).label());
		}
		Printer.INSTANCE.labelFormatMenu(listOfMenus.size(),Quit.INSTANCE.goBackString());
		Printer.INSTANCE.labelFormatMenu(listOfMenus.size()+1,Quit.INSTANCE.quitString());
	}
	/**
	 *  Controls if number passed as parameter in menu lies in range
	 * @param resultScan initial scan
	 * @param menu current Menu
	 * @param sizeMenuList quantity of successors in listMenu
	 * @return Object with Status
	 */
	public static Object[] checkReturnedResultForNumberInput(Object[] resultScan,Menu menu,int sizeMenuList) {
		
		while(resultScan[0]!=Status.OK) {
			Status returned=(Status) resultScan[0];
			Printer.INSTANCE.printStatus(returned);
			menu.show();
			resultScan=UserInputHandler.INSTANCE.numberMenuInput(sizeMenuList);
		}
		return resultScan;
	}
	/**
	 *  Checks if input cell is correct
	 * @param resultScan initial scan
	 * @param cellInputLabel input result
	 * @return Object with status
	 */
	public static Object[] checkReturnedResultForCellInput(Object[] resultScan,String cellInputLabel) {
		while(resultScan[0]!=Status.OK) {
			Status notOk=(Status)resultScan[0];
			Printer.INSTANCE.printStatus(notOk);
			Status returned=(Status) resultScan[0];
			Printer.INSTANCE.printString(cellInputLabel);
			resultScan=UserInputHandler.INSTANCE.cellInput();
		}
		return resultScan;
	}
	/**
	 *  Take in input 2 cells and goes to parent Menu, or waits for correct input
	 * @param child menu child
	 * @return Object with status and 2 cells inside
	 */
	public static Object[] generateTwoCells(Menu child) {
		child.show();
		Object[] startCell=UserInputHandler.INSTANCE.cellInput();
		Object[] resultStartCell=MenuHelper.checkReturnedResultForCellInput(startCell,Words.InputFirstCell);
		Cell cellToStart=(Cell) resultStartCell[1];
		child.show();
		Object[] finishCell=UserInputHandler.INSTANCE.cellInput();
		Object[] resultFinishCell=MenuHelper.checkReturnedResultForCellInput(finishCell,Words.InputSecondCell);
		Cell cellToFinish=(Cell) resultFinishCell[1];
		return new Object[] {Status.OK,cellToStart,cellToFinish};
	}
	/**
	 *  takes two cells from input and generates cells for ship
	 * @param child menu child(successor)
	 * @param parent menu parent(predecessor)
	 * @return Object with status and cells to generate
	 */
	public static Object[] getCellsForShip(Menu child,Menu parent){
		child.show();
		Object[] startCell=UserInputHandler.INSTANCE.cellInput();
		Object[] resultStartCell=MenuHelper.checkReturnedResultForCellInput(startCell,Words.InputFirstCell);
		Cell cellToStart=(Cell) resultStartCell[1];
		child.show();
		Object[] finishCell=UserInputHandler.INSTANCE.cellInput();
		Object[] resultFinishCell=MenuHelper.checkReturnedResultForCellInput(finishCell,Words.InputSecondCell);
		Cell cellToFinish=(Cell) resultFinishCell[1];
		Object[] cellsToGenerateShip=BoardHelper.INSTANCE.generateSetForShip(cellToStart, cellToFinish, Game.turngame.getPlayerTurnGame().getBoardForDefence());
		if(cellsToGenerateShip[0]!=Status.OK) {
			Status notValidStatus=(Status)cellsToGenerateShip[0];
			Printer.INSTANCE.printStatus(notValidStatus);
			parent.launch();
		}
		return cellsToGenerateShip;
	}
	/**
	 * @return Object array with cell inside after taking in input cell formatted in this way- 'character number'
	 */
	public static Object[] generateOneCell() {
		Object[] cell=UserInputHandler.INSTANCE.cellInput();
		Object[] resultCell=MenuHelper.checkReturnedResultForCellInput(cell,Words.InputCell);
		
		return resultCell;
		
	}

}
