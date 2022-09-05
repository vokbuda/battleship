package com.battlefield.menu;

import com.battlefield.boards.Cell;
import com.battlefield.game.Game;
import com.battlefield.game.ThreadStopGame;
import com.battlefield.settings.Status;
import com.battlefield.settings.Words;

/**
 * @author dubkov
 *
 */
public class AttackCellMenu implements Menu {
	private String label=Words.CHOOSE_CELL_TO_ATTACK;
	private Menu parentMenu;
	
	/**
	 * @param menu
	 * Creates AttackCellMenu for attack execution
	 */
	public AttackCellMenu(Menu menu) {
		this.parentMenu=menu;
		
	}
	@Override
	public void show() {
		MenuHelper.showInputCell();
	}

	/**
	 * 
	 *  Launches menu
	 */
	@Override
	public void launch() {
		this.show();
		Object[] cellInput= MenuHelper.generateOneCell();
		Status resultCellInput=(Status)cellInput[0];
		Cell cell=(Cell)cellInput[1];
		
		Game.turngame.getPlayerTurnGame().attack(cell);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(ThreadStopGame.continues) {
			Game.turngame.executionNextTurnAttack();
		}
		Game.executor.submit(parentMenu);
	}
	/**
	 *	@return Returns name of current Menu
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
