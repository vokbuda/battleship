package com.battlefield.menu;

import com.battlefield.boards.Cell;
import com.battlefield.game.Game;
import com.battlefield.player.Human;
import com.battlefield.printer.Printer;
import com.battlefield.settings.Status;
import com.battlefield.settings.Words;

/**
 * @author dubkov
 *
 */
public class RemoveShipByCellMenu implements Menu{
	private String label=Words.REMOVE_SHIP_CHOOSING_ONE_CELL;
	private Menu parentMenu;
	
	/**
	 * @param parentMenu helps switch back to predecessor Menu
	 */
	public RemoveShipByCellMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}

	/**
	 *  Shows current menu
	 */
	@Override
	public void show() {
		MenuHelper.showInputCell();
	}

	/**
	 *  Launches current menu
	 */
	@Override
	public void launch() {
		this.show();
		Object[] cellInput= MenuHelper.generateOneCell();
		Status resultCellInput=(Status)cellInput[0];
		Cell cell=(Cell)cellInput[1];
		Status removedShip=((Human)Game.turngame.getPlayerTurnGame()).removeShipByCell(cell);
		Printer.INSTANCE.printStatus(removedShip);
		Game.executor.submit(parentMenu);
	}

	/**
	 * @return Returns name of this menu
	 */
	@Override
	public String label() {
		return this.label;
	}

	/**
	 *  Launches thread from where menu starts
	 */
	@Override
	public void run() {
		launch();
	}

}
