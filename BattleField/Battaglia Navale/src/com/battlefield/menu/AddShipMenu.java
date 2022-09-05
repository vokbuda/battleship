package com.battlefield.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.battlefield.game.Game;
import com.battlefield.userInput.UserInputHandler;

/**
 * @author dubkov
 *
 */
public class AddShipMenu implements Menu{
	private String label="Add Ship";
	private Menu parent;
	private List<Menu> menusList;
	/**
	 * constructor
	 * @param parent is menu predecessor
	 */
	public AddShipMenu(Menu parent) {
		this.parent=parent;
		menusList=new ArrayList<>(Arrays.asList(new AddShipByTypeMenu(this),new AddShipByCellsMenu(this)));
		
	}
	/**
	 *  Method created to show menu
	 */
	@Override
	public void show() {
		// TODO Auto-generated method stub
		MenuHelper.showMenusFromListWithBack(menusList);
		
	}

	@Override
	public void launch() {
		// TODO Auto-generated method stub
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
			Game.executor.submit(parent);
		}
		
		
		
		
	}
	/**
	 *	@return Returns name of current Menu
	 */
	@Override
	public String label() {
		// TODO Auto-generated method stub
		return this.label;
	}
	@Override
	public void run() {
		this.launch();
		// TODO Auto-generated method stub
		
	}

}
