package com.battlefield.menu;

/**
 * @author dubkov
 * 
 */
public interface Menu extends Runnable {
	
	
	/**
	 *  Shows Menu
	 */
	void show();
	/**
	 *  Launches menu
	 */
	void launch();
	/**
	 * @return Returns menu's name
	 */
	String label();
	

}
