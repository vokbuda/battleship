package com.battlefield.menu;

import java.util.TreeSet;

import com.battlefield.boards.Cell;
import com.battlefield.game.Game;
import com.battlefield.printer.Printer;
import com.battlefield.settings.Status;
import com.battlefield.settings.Words;
import com.battlefield.ships.Carrier;
import com.battlefield.ships.ShipCheck;

/**
 * @author dubkov
 *
 */
public class AddCarrierMenu implements Menu {
	
	private String label=Words.ADD_CARRIER;
	private int indexToShow=0;
	private Menu menuParent;
	interface ShowRespectPrint{
		void showcurrent();
	}
	/**
	 * Constructs object of current class
	 * @param menuParent predecessor Menu
	 */
	public AddCarrierMenu(Menu menuParent) {
		this.menuParent=menuParent;
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
	 * @param index with this one program switches between outputs
	 */
	public void showcurrent(int index) {
		printsToShow[index].showcurrent();
    }
	/**
	 *  shows Current menu
	 */
	@Override
	public void show() {
		showcurrent(this.indexToShow);
		this.indexToShow=this.indexToShow^1;
	}
	/**
	 *  Shows text for the first input
	 */
	public void showFirstPrint() {
		MenuHelper.showFirstInputCell();
	}
	/**
	 *  Shows text for the second input
	 */
	public void showSecondPrint() {
		MenuHelper.showSecondInputCell();
		
	}
	
	/**	 method launches current Menu
	 *
	 */
	@Override
	public void launch() {
		TreeSet<Cell> treeSetCells=new TreeSet<>();
		treeSetCells.add(new Cell('A',1));
		treeSetCells.add(new Cell('A',2));
		treeSetCells.add(new Cell('A',3));
		treeSetCells.add(new Cell('A',4));
		treeSetCells.add(new Cell('A',5));
		if(Game.turngame.getPlayerTurnGame().getShipRepo().checkQuantityIfAdd(new Carrier(treeSetCells))){
		Object[] cellsToGenerateShip=MenuHelper.getCellsForShip(this,this.menuParent);
		TreeSet<Cell> cellsForCreationShip=(TreeSet)cellsToGenerateShip[1];
		Status checkCarrier=ShipCheck.checkCarrier(cellsForCreationShip);
		if(checkCarrier.equals(Status.OK)) {
			Object[] objectAddition=Game.turngame.getPlayerTurnGame().addShipByType(new Carrier(cellsForCreationShip));
			Status statusAddition=(Status)objectAddition[0];
			Printer.INSTANCE.printStatus(statusAddition);
			if(statusAddition.equals(Status.OK)&&(int)objectAddition[1]==0) {
				Printer.INSTANCE.printString(Words.SHIP_REPO_IS_FULL);
			}
		}else {
			Printer.INSTANCE.printStatus(checkCarrier);
		}
		
		Game.executor.submit(this.menuParent);
		}
		else {
		Printer.INSTANCE.notPossibleAddShipOfThisType(Words.CARRIER);
		Game.executor.submit(this.menuParent);
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
	 *  Launches thread
	 */
	@Override
	public void run() {
		launch();
		
	}

}
