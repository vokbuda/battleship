package com.battlefield.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.battlefield.game.Game;
import com.battlefield.game.ThreadStopGame;
import com.battlefield.player.Player;
import com.battlefield.printer.Printer;
import com.battlefield.settings.Status;
import com.battlefield.settings.Words;
import com.battlefield.userInput.UserInputHandler;

/**
 * @author dubkov
 *
 */
public class PrepareForBattlePlayerMenu implements Menu{
	
	List<Menu> successorMenusList=new ArrayList<Menu>(Arrays.asList());
	private String label=Words.START_GAME;
	private static Player currentHuman;
	
	
	/**
	 * constructor
	 */
	public PrepareForBattlePlayerMenu() {
		
		this.successorMenusList=new ArrayList<Menu>(Arrays.asList(new AddShipMenu(this),new RemoveShipMenu(this),new UpgradeShipMenu(this),new DowngradeShipMenu(this),new ShowAvailableShipsMenu(this),new ShowDefenceBoardMenu(this),new DuringBattleMenu()));
		
	}
	
	/**
	 *  Shows current Menu in console
	 */
	@Override
	public void show() {
		MenuHelper.showMenusFromList(successorMenusList);
		
		
	}

	

	/**
	 *  Launches current menu, from this menu 2 threads will be started(1 for menu, another one to check if game is finished)
	 */
	@Override
	public void launch() {
		this.currentHuman=Game.turngame.getPlayerTurnAllocation();	
		this.show();
		Object[] resultScan=UserInputHandler.INSTANCE.numberMenuInput(successorMenusList.size());
		resultScan=MenuHelper.checkReturnedResultForNumberInput(resultScan,this, successorMenusList.size());
		int intToHandle=(int) resultScan[1];
		
		if(intToHandle<successorMenusList.size()-1) {
			successorMenusList.get(intToHandle).launch();
		}
		
		if(intToHandle==successorMenusList.size()-1) {
			if(this.currentHuman.isReadyForSingleBattle()) {
				Object[] nextTurnAllocation=Game.turngame.executionNextTurnAllocationShip();
				if(nextTurnAllocation[0].equals(Status.NextTurn)) {
					Game.executor.submit(this);
				}
				if(nextTurnAllocation[0].equals(Status.StartGame)) {
					Runnable threadStopGame=new ThreadStopGame(Game.playerRepo);
					Game.executor.submit(threadStopGame);
					Game.executor.submit(successorMenusList.get(intToHandle));
				}
				
			}else {
				Printer.INSTANCE.printString(Words.ADD_ALL_SHIPS);
				Game.executor.submit(this);
			}
		}
		if(intToHandle==successorMenusList.size()) {
			Quit.INSTANCE.exit();
		}
	}

	/**
	 * @return Returns label of current menu
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
