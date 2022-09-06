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
public class AddShipByTypeMenu implements Menu {
	private String label=Words.ADD_SHIP_SELECTING_TYPE;
	List<Menu> menusList;	
	private Menu parentMenu;
	
	
	/**
	 * Constructor
	 * @param parentMenu is predecessor menu
	 */
	public AddShipByTypeMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
		menusList=new ArrayList<>(Arrays.asList(new AddBattleshipMenu(this),new AddCarrierMenu(this),new AddDestroyerMenu(this),new AddPatrolBoatMenu(this),new AddSubmarineMenu(this)));
	}

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
		Object[] resultScan=UserInputHandler.INSTANCE.numberMenuInputWithBackTurnOff(menusList.size()+1);
		resultScan=MenuHelper.checkReturnedResultForNumberInput(resultScan,this, menusList.size()+1);
		int intToHandle=(int) resultScan[1];		
		if(intToHandle==menusList.size()+1) {
			
			Quit.INSTANCE.exit();
		}
		if(intToHandle<menusList.size()) {
			Game.executor.submit(menusList.get(intToHandle));
			
		}
		if(intToHandle==menusList.size()) {
			Game.executor.submit(parentMenu);
		}
		
		// TODO Auto-generated method stub
		
	}
	/**
	 *	@return Returns name of current Menu
	 */
	@Override
	public String label() {
		// TODO Auto-generated method stub
		return this.label;
	}
	
	/**
	 *  Launches thread
	 */
	@Override
	public void run() {
		launch();
	}
	
	

}
