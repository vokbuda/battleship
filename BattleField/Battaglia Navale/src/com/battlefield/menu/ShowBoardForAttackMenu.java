package com.battlefield.menu;

import com.battlefield.game.Game;
import com.battlefield.player.Turn;
import com.battlefield.printer.Printer;
import com.battlefield.settings.Words;

/**
 * @author dubkov
 *
 */
public class ShowBoardForAttackMenu implements Menu {
	private String label=Words.SHOW_BOARD_FOR_ATTACK;
	private Menu parentMenu;
	private Turn turnPlayers;
	/**
	 * @param parentMenu is predecessor menu
	 * @param turnPlayers shows board for attack depending on this parameter
	 */
	public ShowBoardForAttackMenu(Menu parentMenu,Turn turnPlayers) {
		this.parentMenu=parentMenu;
		this.turnPlayers=turnPlayers;
		
	}
	@Override
	public void show() {
		Printer.INSTANCE.printStringBuilder(this.turnPlayers.getPlayerTurnGame().getBoardForAttack().showBoard());
	}

	@Override
	public void launch() {
		this.show();
		Game.executor.submit(this.parentMenu);
		// TODO Auto-generated method stub
		
	}

	@Override
	public String label() {
		// TODO Auto-generated method stub
		return this.label;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		launch();
		
	}

}
