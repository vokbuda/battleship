package com.battlefield.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.battlefield.game.Game;
import com.battlefield.settings.Words;
import com.battlefield.userInput.UserInputHandler;

/**
 * @author dubkov
 *
 */
public class ManageUsersMenu implements Menu {
	private List<Menu> menusList;
	private Menu parentMenu;
	private String label=Words.MANAGE_USERS;
	/**
	 * Constructor
	 * @param parentMenu Menu predecessor
	 */
	public ManageUsersMenu(Menu parentMenu) {
		this.parentMenu=parentMenu;
		this.menusList=new ArrayList<>(Arrays.asList(new CreateHumanMenu(this),new RemoveHumanMenu(this),new CreateComputerMenu(this),new RemoveComputerMenu(this), new QuantityUsersMenu(this)));
	}
	
	/**
	 *  Shows menu with possibility to go back
	 */
	@Override
	public void show() {
		MenuHelper.showMenusFromListWithBack(menusList);
				
	}

	/**
	 *  Launches current Menu
	 */
	@Override
	public void launch() {
		this.show();
		Object[] resultScan=UserInputHandler.INSTANCE.numberMenuInputWithBackTurnOff(menusList.size());
		resultScan=MenuHelper.checkReturnedResultForNumberInput(resultScan,this, menusList.size());
		int intToHandle=(int) resultScan[1];
		if(intToHandle<menusList.size()) {
			Game.executor.submit(this.menusList.get(intToHandle));
		}
		if(intToHandle==menusList.size()) {
			Game.executor.submit(this.parentMenu);
		}
		if(intToHandle==menusList.size()+1) {
			Quit.INSTANCE.exit();
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
	 *  Launches thread with current menu
	 */
	@Override
	public void run() {
		launch();		
	}

}
