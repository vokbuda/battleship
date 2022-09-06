package com.battlefield.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.battlefield.game.Game;
import com.battlefield.game.ThreadStopGame;
import com.battlefield.player.Player;
import com.battlefield.printer.Printer;
import com.battlefield.settings.Words;
import com.battlefield.userInput.UserInputHandler;

/**
 * @author dubkov
 *
 */
public class DuringBattleMenu implements Menu{
	private String label=Words.NEXT_TURN_OR_START_BATTLE;
	List<Menu> menusList;
	/**
	 * checks if game whould continue
	 */
	public static boolean gameContinues;
	
	/**
	 * Constructor
	 */
	public DuringBattleMenu() {
		gameContinues=true;
		this.menusList=new ArrayList<>(Arrays.asList(new AttackCellMenu(this)));
		
		
	}
	@Override
	public void show() {
		MenuHelper.showMenusFromList(menusList);
		
		
		
		
	}
	
	@Override
	public void launch() {
		
		if(ThreadStopGame.continues) {
			Player currentPlayer=Game.turngame.getPlayerTurnGame();
			Printer.INSTANCE.printStringBuilder(currentPlayer.getBoardForAttack().showBoard());
			Printer.INSTANCE.printStringBuilder(currentPlayer.getBoardForDefence().showBoard()); 
			this.show();
			Object[] resultScan=UserInputHandler.INSTANCE.numberMenuInput(menusList.size());
			resultScan=MenuHelper.checkReturnedResultForNumberInput(resultScan,this, menusList.size());
			int intToHandle=(int) resultScan[1];
			if(intToHandle<menusList.size()) {
				
				Game.executor.submit(this.menusList.get(intToHandle));
			}
			if(intToHandle==menusList.size()) {
				Quit.INSTANCE.exitDuringBattle();
				
			}
		}else {
			Game.executor.submit(new PostBattleMenu());
		}
	}
	
	


	@Override
	public String label() {
		return this.label;
	}
	@Override
	public void run() {
		launch();
	}

}
