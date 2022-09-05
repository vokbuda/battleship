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
public class RemoveShipMenu implements Menu {
	private String label=Words.REMOVE_SHIP;
	List<Menu> menusList;
	private Menu parentMenu;
	/**
	 * Constructor
	 * @param menu is predecessor Menu
	 */
	public RemoveShipMenu(Menu menu) {
		parentMenu=menu;
		this.menusList=new ArrayList<>(Arrays.asList(new RemoveShipByCellMenu(this),new RemoveAllShipsFromDefenceBoardMenu(this)));
		
	}
	/**
	 *  Shows menu in console
	 */
	@Override
	public void show() {
		MenuHelper.showMenusFromListWithBack(menusList);		
	}

	/**
	 *  Launches current menu
	 */
	@Override
	public void launch() {
		this.show();
		Object[] resultScan=UserInputHandler.INSTANCE.numberMenuInputWithBackTurnOff(menusList.size());
		resultScan=MenuHelper.checkReturnedResultForNumberInput(resultScan,this, menusList.size());
		int intToHandle=(int) resultScan[1];
		if(intToHandle==menusList.size()) {
			Game.executor.submit(parentMenu);
		}
		if(intToHandle==menusList.size()+1) {
			Quit.INSTANCE.exit();
		}if(intToHandle<menusList.size()) {
			Game.executor.submit(menusList.get(intToHandle));
		}
	}

	/**
	 * @return returns name of current Menu
	 */
	@Override
	public String label() {
		return this.label;
	}
	/**
	 *  inisitializes thread, which starts launch method
	 */
	@Override
	public void run() {
		launch();
		
	}

}
