package com.battlefield.menu;

import com.battlefield.game.Game;
import com.battlefield.game.ThreadStopGame;
import com.battlefield.printer.Printer;

/**
 * @author dubkov
 *
 */
public enum Quit {
	/**
	 * Represents instance of this enum
	 */
	INSTANCE;
	/**
	 * Exits from game
	 */
	public static void exit() {
		Printer.INSTANCE.printString(turnedOff);
		Game.INSTANCE.gameContinues=false;
		Game.INSTANCE.executor.shutdown();
		
	}
	/**
	 *  method which stops game
	 */
	public static void exitDuringBattle() {
		exit();
		ThreadStopGame.continues=false;
	}
	private static String turnedOff="Game is turned off";
	private static String QUIT_GAME="Quit game";
	private final static String GO_BACK="Go back";
	/**
	 * @return String QUIT_GAME defined in Words.java
	 */
	public String quitString() {
		return QUIT_GAME;
	}
	/**
	 * @return String GO_BACK defined in Words.java
	 */
	public String goBackString() {
		return GO_BACK;
	}

}
