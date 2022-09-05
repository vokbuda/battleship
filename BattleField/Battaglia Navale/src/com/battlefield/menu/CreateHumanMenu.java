package com.battlefield.menu;

import com.battlefield.game.Game;
import com.battlefield.player.Human;
import com.battlefield.player.Player;
import com.battlefield.player.Turn;
import com.battlefield.printer.Printer;
import com.battlefield.settings.Status;
import com.battlefield.settings.Words;
import com.battlefield.userInput.UserInputHandler;

/**
 * @author dubkov
 *
 */
public class CreateHumanMenu implements Menu {
	private String label=Words.CREATE_USER;
	
	/**
	 * Instance, responsible for switching user's turns
	 */
	public static Turn turnGame;
	private Menu parent;
	/**
	 * Constructor
	 * @param parent is predecessor Menu
	 */
	public CreateHumanMenu(Menu parent) {
		this.parent=parent;
		
	}
	/**
	 *  Shows current Menu
	 */
	@Override
	public void show() {
		Printer.INSTANCE.printString(Words.INSERT_USERNAME);
		
		
	}

	/**
	 *  Launches current menu
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
		
		Human playerCreated=new Human((String)resultScan[1],Game.playerRepo);
		Object[] playerResult=Game.playerRepo.addHuman(playerCreated);
		Status result=(Status)playerResult[0];
		if(result.equals(Status.OK)) {
			Printer.INSTANCE.userAdded(((Player)playerResult[1]).getName());
		}
		else {
			Printer.INSTANCE.printStatus(result);
		}
		
		Game.executor.submit(parent);
		
	}
	
	/**
	 *	@return Returns name of current Menu
	 */
	@Override
	public String label() {
	
		return this.label;
	}
	/**
	 *  Launches thread with current menu
	 */
	@Override
	public void run() {
		launch();
		
		
	}

}
