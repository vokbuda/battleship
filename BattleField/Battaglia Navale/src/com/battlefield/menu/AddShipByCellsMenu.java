package com.battlefield.menu;

import com.battlefield.boards.Cell;
import com.battlefield.game.Game;
import com.battlefield.menu.AddCarrierMenu.ShowRespectPrint;
import com.battlefield.printer.Printer;
import com.battlefield.settings.Status;
import com.battlefield.settings.Words;
import com.battlefield.ships.Ship;

/**
 * @author dubkov
 *
 */
public class AddShipByCellsMenu implements Menu{
	private String label=Words.ADD_SHIP_INSERTING_TWO_CELLS;
	private Menu parentMenu;
	private int indexToShow;
	/**
	 * constructor
	 * @param parentMenu is menu predecessor
	 */
	public AddShipByCellsMenu(Menu parentMenu) {
		this.parentMenu=parentMenu;
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
	 * 
	 * @param index shows output in base of index
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
	 *  Shows print for the first cell input
	 */
	public void showFirstPrint() {
		MenuHelper.showFirstInputCell();
	}
	/**
	 *  Shows print for the second Cell input
	 */
	public void showSecondPrint() {
		MenuHelper.showSecondInputCell();
		
	}

	/**
	 *  Launches Menu
	 */
	@Override
	public void launch() {
		Object[] cellsToGet=MenuHelper.generateTwoCells(this);
		Object[] resultAddition=Game.turngame.getPlayerTurnGame().addShip((Cell)cellsToGet[1], (Cell)cellsToGet[2]);
		Status returnedResult=(Status)resultAddition[0];
		Ship shipToAdd=(Ship)resultAddition[1];
		
		
		
		if(returnedResult.equals(Status.OK)) {
			Printer.INSTANCE.addedShip(shipToAdd.getTypeShip());
			int quantityShipRemainsToAdd=(int) resultAddition[2];
			if(quantityShipRemainsToAdd==0) {
				Printer.INSTANCE.printString(Words.SHIP_REPO_IS_FULL);
			}
		}else {
			Printer.INSTANCE.printStatus(returnedResult);
		}
		
		this.parentMenu.launch();
	}
	/**
	 *	@return Returns name of current Menu
	 */
	@Override
	public String label() {
		return this.label;
	}
	/**
	 *  Launches Menu Thread
	 */
	@Override
	public void run() {
		this.launch();
		
	}

}
