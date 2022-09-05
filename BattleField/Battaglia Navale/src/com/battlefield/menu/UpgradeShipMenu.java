package com.battlefield.menu;

import com.battlefield.boards.Cell;
import com.battlefield.game.Game;
import com.battlefield.player.Human;
import com.battlefield.printer.Printer;
import com.battlefield.settings.Status;
import com.battlefield.settings.Words;
import com.battlefield.userInput.UserInputHandler;

/**
 * @author dubkov
 *
 */
public class UpgradeShipMenu implements Menu {
	private String label=Words.UPGRADE_SHIP;
	private Menu parentMenu;
	private int invalidCellSinput=0;
	/**
	 * constructor
	 * @param parentMenu predecessor
	 */
	public UpgradeShipMenu(Menu parentMenu) {
		this.parentMenu=parentMenu;
	}
	
	/**
	 *  Shows menu
	 */
	@Override
	public void show() {
		Printer.INSTANCE.printString(Words.InputCell);
	}

	/**
	 *  Starts current menu
	 */
	@Override
	public void launch() {
		this.show();
		Object[] resultScan=UserInputHandler.INSTANCE.cellInput();
		if(resultScan[0]!=Status.OK) {
			
			Status returned=(Status) resultScan[0];
			Printer.INSTANCE.printStatus(returned);
			
		}
		
		Cell cellToAdd=(Cell) resultScan[1];
		Status upgradedShip=((Human)Game.turngame.getPlayerTurnGame()).upgradeShip(cellToAdd);
		Printer.INSTANCE.printStatus(upgradedShip);
		
		Game.executor.submit(this.parentMenu);
	}

	/**
	 * @return returns Name of Menu
	 */
	@Override
	public String label() {
		return this.label;
	}

	/**
	 *  Launches thread which starts menu
	 */
	@Override
	public void run() {
		launch();
	}

}
