package com.battlefield.menu;

import java.util.TreeSet;

import com.battlefield.boards.Cell;
import com.battlefield.game.Game;
import com.battlefield.menu.AddCarrierMenu.ShowRespectPrint;
import com.battlefield.printer.Printer;
import com.battlefield.settings.Status;
import com.battlefield.settings.Words;
import com.battlefield.ships.Battleship;
import com.battlefield.ships.ShipCheck;


/**
 * @author 150224
 *
 */
public class AddBattleshipMenu implements Menu {
	private String label=Words.ADD_BATTLESHIP;
	private Menu menuParent;
	private int indexToShow;
	
	/**
	 * constructor
	 * @param menuParent predecessor
	 */
	public AddBattleshipMenu(Menu menuParent) {
		this.menuParent = menuParent;
	}
	/**
	 * 
	 * This array helps us to switch between two prints and print different cells
	 */
	private ShowRespectPrint[] printsToShow=new ShowRespectPrint[] {
			new ShowRespectPrint() {
				public void showcurrent() {
					showFirstPrint();
				}
			},new ShowRespectPrint() {
				public void showcurrent() {
					showSecondPrint();
				}
			}
	};
	
	/**
	 *  helps to switch between different outputs in console
	 * @param index can be 0 or 1 to select the first or the second element in array to show
	 */
	public void showcurrent(int index) {
		printsToShow[index].showcurrent();
    }
	@Override
	public void show() {
		showcurrent(this.indexToShow);
		this.indexToShow=this.indexToShow^1;
		
	}
	/**
	 * Shows output to insert the first cell
	 */
	public void showFirstPrint() {
		MenuHelper.showFirstInputCell();
	}
	/**
	 * Shows output to insert the second cell
	 */
	public void showSecondPrint() {
		MenuHelper.showSecondInputCell();
		
	}
	
	/**
	 *  Launches menu
	 *
	 */
	@Override
	public void launch() {
		TreeSet<Cell> treeSetCells=new TreeSet<>();
		treeSetCells.add(new Cell('A',1));
		treeSetCells.add(new Cell('A',2));
		treeSetCells.add(new Cell('A',3));
		treeSetCells.add(new Cell('A',4));
		
		if(Game.turngame.getPlayerTurnGame().getShipRepo().checkQuantityIfAdd(new Battleship(treeSetCells))) {
		Object[] cellsToGenerateShip=MenuHelper.getCellsForShip(this,this.menuParent);
		TreeSet<Cell> cellsForCreationShip=(TreeSet)cellsToGenerateShip[1];
		Status checkBattleship=ShipCheck.checkBattleship(cellsForCreationShip);
		if(checkBattleship.equals(Status.OK)) {
			Object[] objectAddition=Game.turngame.getPlayerTurnGame().addShipByType(new Battleship(cellsForCreationShip));
			Status statusAddition=(Status)objectAddition[0];
			Printer.INSTANCE.printStatus(statusAddition);
			if (statusAddition.equals(Status.OK)&&(int)objectAddition[1]==0){
				Printer.INSTANCE.printString(Words.SHIP_REPO_IS_FULL);
			}
			
		}else {
			Printer.INSTANCE.printStatus(checkBattleship);
		}
		Game.executor.submit(this.menuParent);
		}
		else {
			Printer.INSTANCE.notPossibleAddShipOfThisType(Words.BATTLESHIP);
			Game.executor.submit(this.menuParent);
		}
		
	}
	
	
	
	/**
	 * @return Label
	 */
	@Override
	public String label() {
		// TODO Auto-generated method stub
		return this.label;
	}
	/**
	 *  starts thread
	 */
	@Override
	public void run() {
		launch();	
	}

}
