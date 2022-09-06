package com.battlefield.menu;

import com.battlefield.game.Game;
import com.battlefield.printer.Printer;
import com.battlefield.settings.Words;

/**
 * @author dubkov
 *
 */
public class ShowDefenceBoardMenu implements Menu {
	private String label=Words.SHOW_DEFENCE_BOARD;
	private Menu parentMenu;
	/**
	 * Constructor
	 * @param parentMenu is predecessor Menu
	 */
	public ShowDefenceBoardMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}

	@Override
	public void show() {
		Printer.INSTANCE.printStringBuilder(Game.turngame.getPlayerTurnGame().getBoardForDefence().showBoard());
		
	}

	@Override
	public void launch() {
		this.show();
		Game.executor.submit(parentMenu);
		
	}

	@Override
	public String label() {
		return this.label;
	}

	@Override
	public void run() {
		this.launch();
	}

}
