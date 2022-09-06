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
public class DowngradeShipMenu implements Menu{
	private String label=Words.DOWNGRADE_SHIP;
	private Menu parentMenu;
	
	
	/**
	 * Constructor
	 * @param parentMenu is predecessor
	 */
	public DowngradeShipMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}

	@Override
	public void show() {
		MenuHelper.showInputCell();
	}

	/**
	 *  Launches Menu, takes cells in input and downgrade ship
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
		Status downgradedShip=((Human)Game.turngame.getPlayerTurnGame()).downgradeShip(cellToAdd);
		Printer.INSTANCE.printStatus(downgradedShip);
		Game.executor.submit(this.parentMenu);
	}
	/**
	 *	@return Returns name of current Menu
	 */
	@Override
	public String label() {
		// TODO Auto-generated method stub
		return this.label;
	}

	/**
	 *	Launches thread with menu
	 */
	@Override
	public void run() {
		launch();
		
	}

}
