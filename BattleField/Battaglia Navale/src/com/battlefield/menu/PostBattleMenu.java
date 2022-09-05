package com.battlefield.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.battlefield.game.Game;
import com.battlefield.printer.Printer;
import com.battlefield.settings.Words;
import com.battlefield.userInput.UserInputHandler;

/**
 * @author dubkov
 *
 */
public class PostBattleMenu implements Menu{
	private String label=Words.POST_BATTLE_MENU;
	List<Menu> menusList=new ArrayList<Menu>(Arrays.asList(new MainMenu()));
	/**
	 *	 Shows current Menu
	 */
	@Override
	public void show() {
		Printer.INSTANCE.printString(label);
		MenuHelper.showMenusFromList(menusList);
		
		
		
		
	}
	

	
	/**
	 *	 Launches Menu
	 */
	@Override
	public void launch() {
		this.show();
		Object[] resultScan=UserInputHandler.INSTANCE.numberMenuInput(menusList.size());
		resultScan=MenuHelper.checkReturnedResultForNumberInput(resultScan,this, menusList.size());
		int intToHandle=(int) resultScan[1];
		if(intToHandle==this.menusList.size()) {
			Quit.INSTANCE.exit();
		}else {
			if(menusList.get(intToHandle).equals(MainMenu.class)) {
				
			}
			Game.executor.submit(menusList.get(intToHandle));
		}
		
	}
	/**
	 *  Returns menu's label
	 */
	@Override
	public String label() {
		return this.label;
	}
	/**
	 *  Launches thread from which menu starts
	 */
	@Override
	public void run() {
		launch();
		
	}

}
