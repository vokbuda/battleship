package com.battlefield.menu;

import java.util.TreeSet;

import com.battlefield.boards.Cell;
import com.battlefield.game.Game;
import com.battlefield.menu.AddCarrierMenu.ShowRespectPrint;
import com.battlefield.printer.Printer;
import com.battlefield.settings.Status;
import com.battlefield.settings.Words;
import com.battlefield.ships.ShipCheck;
import com.battlefield.ships.Submarine;

/**
 * @author dubkov
 *
 */
public class AddSubmarineMenu implements Menu{
	private String label=Words.ADD_SUBMARINE;
	private Menu parentMenu;
	private int indexToShow;
	
	/**
	 * constructor
	 * @param parentMenu predecessor Menu
	 */
	public AddSubmarineMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}

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
	 * @param index int-can be 1 or 0
	 */
	public void showcurrent(int index) {
		printsToShow[index].showcurrent();
    }
	@Override
	public void show() {
		showcurrent(this.indexToShow);
		this.indexToShow=this.indexToShow^1;
		// TODO Auto-generated method stub
		
	}
	/**
	 *  Prints in case when we need take in input the first cell
	 */
	public void showFirstPrint() {
		MenuHelper.showFirstInputCell();
	}
	/**
	 *  Prints in case when we need take in input the second cell
	 */
	public void showSecondPrint() {
		MenuHelper.showSecondInputCell();
		
	}

	

	/**
	 *  Launches current Menu
	 */
	@Override
	public void launch() {
		TreeSet<Cell> treeSetCells=new TreeSet<>();
		treeSetCells.add(new Cell('A',1));
		treeSetCells.add(new Cell('A',2));
		treeSetCells.add(new Cell('A',3));
		if(Game.turngame.getPlayerTurnGame().getShipRepo().checkQuantityIfAdd(new Submarine(treeSetCells))) {
		Object[] cellsToGenerateShip=MenuHelper.getCellsForShip(this,this.parentMenu);
		TreeSet<Cell> cellsForCreationShip=(TreeSet)cellsToGenerateShip[1];
		Status checkSubmarine=ShipCheck.checkSubmarine(cellsForCreationShip);
		if(checkSubmarine.equals(Status.OK)) {
			Object[] objectAddition=Game.turngame.getPlayerTurnGame().addShipByType(new Submarine(cellsForCreationShip));
			Status statusAddition=(Status)objectAddition[0];
			Printer.INSTANCE.printStatus(statusAddition);
			if(statusAddition.equals(Status.OK)&&(int)objectAddition[1]==0) {
				Printer.INSTANCE.printString(Words.SHIP_REPO_IS_FULL);
			}
			
		}else {
			Printer.INSTANCE.printStatus(checkSubmarine);
		}
		Game.executor.submit(this.parentMenu);
		}
		else {
			Printer.INSTANCE.notPossibleAddShipOfThisType(Words.SUBMARINE);
			Game.executor.submit(this.parentMenu);
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
	 *  Launches thread with current Menu
	 */
	@Override
	public void run() {
		launch();
		
		
	}

}
