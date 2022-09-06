package com.battlefield.menu;

import java.util.TreeSet;

import com.battlefield.boards.Cell;
import com.battlefield.game.Game;
import com.battlefield.menu.AddCarrierMenu.ShowRespectPrint;
import com.battlefield.printer.Printer;
import com.battlefield.settings.Status;
import com.battlefield.settings.Words;
import com.battlefield.ships.PatrolBoat;
import com.battlefield.ships.ShipCheck;

/**
 * @author dubkov
 *
 */
public class AddPatrolBoatMenu implements Menu {
	private String label=Words.ADD_PATROL_BOAT;
	private Menu parentMenu;
	private int indexToShow;
	
	/**
	 * @param parentMenu presents predecessor of current Menu
	 */
	public AddPatrolBoatMenu(Menu parentMenu) {
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
	 * @param index shows corresponding output related to array
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
	 *  Prints output for the first cell
	 */
	public void showFirstPrint() {
		MenuHelper.showFirstInputCell();
	}
	/**
	 *  Prints output for the second cell
	 */
	public void showSecondPrint() {
		MenuHelper.showSecondInputCell();
		
	}

	

	/**
	 *	 Launches current menu
	 */
	@Override
	public void launch() {
		TreeSet<Cell> treeSetCells=new TreeSet<>();
		treeSetCells.add(new Cell('A',1));
		treeSetCells.add(new Cell('A',2));
		
		
		if(Game.turngame.getPlayerTurnGame().getShipRepo().checkQuantityIfAdd(new PatrolBoat(treeSetCells))) {
		Object[] cellsToGenerateShip=MenuHelper.getCellsForShip(this,this.parentMenu);
		
		TreeSet<Cell> cellsForCreationShip=(TreeSet)cellsToGenerateShip[1];
		Status checkCarrier=ShipCheck.checkPatrolBoat(cellsForCreationShip);
		if(checkCarrier.equals(Status.OK)) {
			Object[] objectAddition=Game.turngame.getPlayerTurnGame().addShipByType(new PatrolBoat(cellsForCreationShip));
			Status statusAddition=(Status)objectAddition[0];
			Printer.INSTANCE.printStatus(statusAddition);
			if(statusAddition.equals(Status.OK)&&(int)objectAddition[1]==0) {
				Printer.INSTANCE.printString(Words.SHIP_REPO_IS_FULL);
			}
			
		}else {
			Printer.INSTANCE.printStatus(checkCarrier);
			
		}
		
		Game.executor.submit(parentMenu);
		
		}else {
		
		Printer.INSTANCE.notPossibleAddShipOfThisType(Words.PATROL_BOAT);
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
	 *  Launches current thread menu
	 */
	@Override
	public void run() {
		launch();
		
	}

}
