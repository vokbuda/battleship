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
public class CreateComputerMenu implements Menu{
	private String label=Words.CREATE_COMPUTER_PLAYER;
	private Computer computerCreated;
	private Menu parentMenu;
	
	/**
	 * Constructor
	 * @param parentMenu is predecessor Menu
	 */
	public CreateComputerMenu(Menu parentMenu) {
		
		this.parentMenu = parentMenu;
	}

	/**
	 *  Shows name of user added to repository
	 */
	@Override
	public void show() {
		Printer.INSTANCE.userAdded(computerCreated.getName());
		
	}

	/**
	 *	 Launches current menu
	 */
	@Override
	public void launch() {
		//We need to remove last computer lol
		
		Object[] resultCreationComp=Game.playerRepo.addComputer();
		Status statusResult=(Status)resultCreationComp[0];
		if(statusResult.equals(Status.OK)) {
			
			this.computerCreated=(Computer)resultCreationComp[1];
			this.show();
		}else {
			Printer.INSTANCE.printStatus(statusResult);
		}
		
		Game.executor.submit(this.parentMenu);
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
