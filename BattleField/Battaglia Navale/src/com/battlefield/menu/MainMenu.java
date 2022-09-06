package com.battlefield.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.battlefield.game.Game;
import com.battlefield.player.Turn;
import com.battlefield.printer.Printer;
import com.battlefield.settings.Words;
import com.battlefield.userInput.UserInputHandler;

/**
 * @author dubkov
 * Entry Menu
 *
 */
public class MainMenu implements Menu {
	
	private String label=Words.MAIN_MENU;

	List<Menu> menusList;
	
	/**
	 * Constructs MainMenu with successors
	 */
	public MainMenu() {
		menusList=new ArrayList<Menu>(Arrays.asList(new ManageUsersMenu(this),new PrepareForBattlePlayerMenu()));
		
	}
	/**
	 *  Shows current menu interface
	 */
	@Override
	public void show() {
		MenuHelper.showMenusFromList(menusList);
	}
	/**
	 *  Launches current Menu
	 */
	@Override
	public void launch() {
		this.show();
		Object[] resultScan=UserInputHandler.INSTANCE.numberMenuInput(menusList.size());
		resultScan=MenuHelper.checkReturnedResultForNumberInput(resultScan,this, menusList.size());
		int intToHandle=(int) resultScan[1];
		
		if(intToHandle==menusList.size()) {
			Quit.INSTANCE.exit();
		}else {
			if (menusList.get(intToHandle).getClass().equals(PrepareForBattlePlayerMenu.class)) {
				if(Game.playerRepo.getSize()>1) {
					Game.turngame=new Turn(Game.playerRepo);
					Game.executor.submit(menusList.get(intToHandle));
				}else {
					Printer.INSTANCE.printString(Words.NEED_TO_CREATE_USER);
					Game.executor.submit(this);
				}
			}else {
				Game.executor.submit(menusList.get(intToHandle));
			}
		}		
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
