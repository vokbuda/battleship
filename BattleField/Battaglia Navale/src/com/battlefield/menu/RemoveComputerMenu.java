package com.battlefield.menu;

import com.battlefield.game.Game;
import com.battlefield.player.Computer;
import com.battlefield.printer.Printer;
import com.battlefield.settings.Status;
import com.battlefield.settings.Words;

/**
 * @author dubkov
 *
 */
public class RemoveComputerMenu implements Menu {
	private Menu parentMenu;
	private String label=Words.REMOVE_COMPUTER_PLAYER;

	/**
	 * Constructor
	 * @param parentMenu is predecessor Menu
	 */
	public RemoveComputerMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}

	/**
	 * shows execution of operation "delete"
	 */
	@Override
	public void show() {
		Printer.INSTANCE.deletionStarted();	
	}

	/**
	 *	Launches current Menu
	 */
	@Override
	public void launch() {
		this.show();
		Computer comp=null;
		
		Object[] eliminatedComputer=Game.playerRepo.removeLastComputer();
		if(eliminatedComputer[0].equals(Status.ComputerEliminated)) {
			comp=(Computer)eliminatedComputer[1];
			Printer.INSTANCE.userRemoved(comp.getName());
		}else {
			Printer.INSTANCE.printStatus(((Status)eliminatedComputer[0]));
		}
		
		
		Game.executor.submit(this.parentMenu);
	}

	/**
	 * @return current menu name
	 */
	@Override
	public String label() {
		return this.label;
	}

	/**
	 *  Starts thread which launches current menu
	 */
	@Override
	public void run() {
		launch();
		
	}

}
