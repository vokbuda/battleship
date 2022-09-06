package com.battlefield.menu;

import java.util.TreeSet;

import com.battlefield.boards.Cell;
import com.battlefield.game.Game;
import com.battlefield.menu.AddCarrierMenu.ShowRespectPrint;
import com.battlefield.printer.Printer;
import com.battlefield.settings.Status;
import com.battlefield.settings.Words;
import com.battlefield.ships.Destroyer;
import com.battlefield.ships.ShipCheck;

/**
 * @author dubkov
 *
 */
public class AddDestroyerMenu implements Menu {
	private String label=Words.ADD_DESTROYER;
	private Menu parentMenu;
	private int indexToShow;
	
	/**
	 * constructor
	 * @param parentMenu predecessor Menu
	 */
	public AddDestroyerMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}
	private ShowRespectPrint[] printsToShow=new ShowRespectPrint[] {
			new ShowRespectPrint() {
				/**
				 * outputs print for the first insertion
				 */
				public void showcurrent() {
					showFirstPrint();
				}
			},new ShowRespectPrint() {
				/**
				 * outputs print for the second insertion
				 */
				public void showcurrent() {
					showSecondPrint();
				}
			}
	};
	
	
	
	/**
	 * @param index switches between cells output
	 */
	public void showcurrent(int index) {
		printsToShow[index].showcurrent();
    }
	/**
	 * shows corresponding output for cells
	 */
	@Override
	public void show() {
		showcurrent(this.indexToShow);
		this.indexToShow=this.indexToShow^1;
		
	}
	/**
	 * Shows output for the first input cell 
	 */
	public void showFirstPrint() {
		MenuHelper.showFirstInputCell();
	}
	/**
	 * Shows output for the second cell input
	 */
	public void showSecondPrint() {
		MenuHelper.showSecondInputCell();
		
	}

	

	/**
	 *  launches current Menu, controls if it is possible to add destroyer
	 */
	@Override
	public void launch() {
		TreeSet<Cell> treeSetCells=new TreeSet<>();
		treeSetCells.add(new Cell('A',1));
		treeSetCells.add(new Cell('A',2));
		treeSetCells.add(new Cell('A',3));
		
		if(Game.turngame.getPlayerTurnGame().getShipRepo().checkQuantityIfAdd(new Destroyer(treeSetCells))) {
		Object[] cellsToGenerateShip=MenuHelper.getCellsForShip(this,this.parentMenu);
		TreeSet<Cell> cellsForCreationShip=(TreeSet)cellsToGenerateShip[1];
		Status checkDestroyer=ShipCheck.checkDestroyer(cellsForCreationShip);
		if(checkDestroyer.equals(Status.OK)) {
			Object[] objectAddition=Game.turngame.getPlayerTurnGame().addShipByType(new Destroyer(cellsForCreationShip));
			Status statusAddition=(Status) objectAddition[0];
			Printer.INSTANCE.printStatus(statusAddition);
			if(statusAddition.equals(Status.OK)&&(int)objectAddition[1]==0) {
				Printer.INSTANCE.printString(Words.SHIP_REPO_IS_FULL);
			}
			
		}else {
			Printer.INSTANCE.printStatus(checkDestroyer);
		}
		
		Game.executor.submit(parentMenu);
		}
		else {
			Printer.INSTANCE.notPossibleAddShipOfThisType(Words.DESTROYER);
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
