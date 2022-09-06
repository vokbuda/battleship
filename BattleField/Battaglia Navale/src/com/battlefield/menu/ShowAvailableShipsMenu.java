package com.battlefield.menu;

import com.battlefield.game.Game;
import com.battlefield.player.Human;
import com.battlefield.printer.Printer;
import com.battlefield.settings.Words;

/**
 * @author dubkov
 *
 */
public class ShowAvailableShipsMenu implements Menu {
	private String label=Words.SHOW_AVAILABLE_SHIP;
	private Menu parentMenu;
	/**
	 * constructor
	 * @param parentMenu is predecessor
	 */
	public ShowAvailableShipsMenu(Menu parentMenu) {
		this.parentMenu=parentMenu;
	}
	
	/**
	 *  Shows menu in console
	 */
	@Override
	public void show() {
		Printer.INSTANCE.printStringBuilder(((Human)Game.turngame.getPlayerTurnGame()).getAvailableShips());
	}

	/**
	 *  Starts menu
	 */
	@Override
	public void launch() {
		this.show();
		Game.executor.submit(this.parentMenu);
	}

	/**
	 * @return Returns menu's name
	 */
	@Override
	public String label() {
		return this.label;
	}

	/**
	 *  Runs thread which calls method launch
	 */
	@Override
	public void run() {
		launch();
	}

}
