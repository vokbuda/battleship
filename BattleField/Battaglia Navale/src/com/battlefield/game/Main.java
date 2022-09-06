package com.battlefield.game;

import com.battlefield.menu.MainMenu;
import com.battlefield.security.ExtendedSecurityManager;

/**
 * @author dubkov
 *
 */
public class Main {

	/**
	 * @param args Launches Main Thread
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		System.setSecurityManager(new ExtendedSecurityManager());
		
		MainMenu mainMenu=new MainMenu();
		
		Game.executor.submit(mainMenu);
			
		
		
		
		
	}
	

}
