package com.battlefield.menu;

import com.battlefield.game.Game;
import com.battlefield.printer.Printer;
import com.battlefield.settings.Words;

/**
 * @author dubkov
 *
 */
public class QuantityUsersMenu implements Menu {
	private String label=Words.SHOW_ALL_PARTICIPANTS;
	private Menu parentMenu;
	/**
	 * Constructor
	 * @param parentMenu is predecessor
	 */
	public QuantityUsersMenu(Menu parentMenu) {
		this.parentMenu=parentMenu;
	}
	/**
	 *  Shows quantity of players and who they are
	 */
	@Override
	public void show() {
		Printer.INSTANCE.printStringBuilder(Game.playerRepo.showSituation());
	}

	/**
	 *  Shows menu and go back
	 */
	@Override
	public void launch() {
		this.show();
		Game.executor.submit(this.parentMenu);
		
		
	}

	/**
	 * @return Returns name of current Menu
	 */
	@Override
	public String label() {
		return this.label;
	}
	/**
	 *  Launches thread with current Menu
	 */
	@Override
	public void run() {
		launch();
		
	}

}
