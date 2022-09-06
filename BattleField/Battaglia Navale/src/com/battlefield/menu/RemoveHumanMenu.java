package com.battlefield.menu;

import com.battlefield.game.Game;
import com.battlefield.player.Human;
import com.battlefield.player.Player;
import com.battlefield.printer.Printer;
import com.battlefield.settings.Status;
import com.battlefield.settings.Words;
import com.battlefield.userInput.UserInputHandler;

/**
 * @author dubkov
 *
 */
public class RemoveHumanMenu implements Menu {
	private String label=Words.REMOVE_USER;
	private Menu parentMenu;

	
	/**
	 * constructor
	 * @param parentMenu is predecessor Menu
	 */
	public RemoveHumanMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}

	/**
	 *  Shows current Menu
	 */
	@Override
	public void show() {
		Printer.INSTANCE.printString(Words.INSERT_USERNAME);		
	}

	/**
	 *  launches Menu
	 */
	@Override
	public void launch() {
		this.show();
		Object[] resultScan=UserInputHandler.INSTANCE.StringMenuInput();
		
		while(resultScan[0]!=Status.OK) {
			Status returned=(Status) resultScan[0];
			Printer.INSTANCE.printStatus(returned);
			this.show();
			resultScan=UserInputHandler.INSTANCE.StringMenuInput();
			
		}
		
		Object[] userEliminated=Game.playerRepo.removeUser(new Human((String)resultScan[1],Game.playerRepo));
		Status result=(Status)userEliminated[0];
		if(result.equals(Status.UserEliminated)) {
			Printer.INSTANCE.userRemoved(((Player)userEliminated[1]).getName());
		}else {
			Printer.INSTANCE.printStatus(result);
		}
		
		Game.executor.submit(this.parentMenu);
				
	}

	/**
	 * @return Returns name of current menu
	 */
	@Override
	public String label() {
		return this.label;
	}

	/**
	 *  Starts thread which executes method launch()
	 */
	@Override
	public void run() {
		launch();
		
	}

}
