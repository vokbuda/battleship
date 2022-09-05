package com.battlefield.menu;

import com.battlefield.game.Game;
import com.battlefield.printer.Printer;
import com.battlefield.settings.Status;
import com.battlefield.settings.Words;

/**
 * @author 150224
 *
 */
public class RemoveAllShipsFromDefenceBoardMenu implements Menu {
	private String label=Words.REMOVE_ALL_SHIPS_FROM_DEFENCE_BOARD;
	private Menu parentMenu;
	/**
	 * Constructor
	 * @param parentMenu is predecessor Menu
	 */
	public RemoveAllShipsFromDefenceBoardMenu(Menu parentMenu) {
		this.parentMenu=parentMenu;
	}
	@Override
	public void show() {
				
	}

	/**
	 *  Launches current Menu
	 */
	@Override
	public void launch() {
		Status result=Game.turngame.getPlayerTurnGame().removeAllShipsFromDefenceTable();
		Printer.INSTANCE.printStatus(result);
		Game.executor.submit(this.parentMenu);		
	}

	/**
	 *  Returns label of current menu
	 */
	@Override
	public String label() {
		return this.label;
	}
	/**
	 * Starts thread with current Menu
	 */
	@Override
	public void run() {
		launch();
		
	}

}
