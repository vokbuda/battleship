package com.battlefield.printer;

import java.util.List;

import com.battlefield.player.Player;
import com.battlefield.settings.Status;
import com.battlefield.settings.Words;

/**
 * @author dubkov
 *
 */
public enum Printer {
	/**
	 * Gives access to methods inside
	 */
	INSTANCE;
	
	/**
	 *  prints String
	 * @param toPrint String
	 */
	public void printString(String toPrint) {
		System.out.println(toPrint);
	}
	
	/**
	 *  Prints status
	 * @param statusToPrint prints string which represents status
	 */
	public void printStatus(Status statusToPrint) {
		System.out.println(statusToPrint.getResult());
	}
	
	/**
	 * Clears console, ansi characters are not working in windows
	 */
	public void clearConsole() {
		System.out.print("\033[H\033[2J");  
    	System.out.flush(); 
	}
	/**
	 *  Used for print stringbuilder
	 * @param toPrint StringBuilder
	 */
	public void printStringBuilder(StringBuilder toPrint) {
		System.out.println(toPrint);
	}
	/**
	 *  Prints menu in certain format
	 * @param i menu int representation
	 * @param label menu label
	 */
	public void labelFormatMenu(int i,String label) {
		System.out.println(i+1+"."+label);
	}
	/**
	 *  Shows added ship
	 * @param nameship is name of added ship to print
	 */
	public void addedShip(String nameship) {
		System.out.println(nameship+" "+Words.WAS_ADDED_TO_DEFENCE_BOARD);
	}
	/**
	 *   shows when it is not possible add ship to Repo
	 * @param typeShip String
	 */
	public void notPossibleAddShipOfThisType(String typeShip) {
		System.out.println(Words.IsNotPossibleAddShipOfThisType+"-----"+typeShip);
	}
	/**
	 *  Prints winner
	 * @param namePlayer String
	 */
	public void winner(String namePlayer) {
		System.out.println(namePlayer+" "+Words.WON);
	}
	/**
	 *  Shows eliminated players
	 * @param listPlayers checks on current list
	 */
	public void eliminatedUsers(List<Player> listPlayers) {
		for (Player player : listPlayers) {
			System.out.println(player.getName()+" "+Words.ELIMINATED);
		}
	}
	/**
	 *  Prints player's turn
	 * @param name String
	 */
	public void turnPlayer(String name) {
		System.out.println(name+" "+Words.TURN_LABEL);
	}
	/**
	 *  Shows added user to Repository
	 * @param name username is used for user's identification
	 */
	public void userAdded(String name) {
		System.out.println(name+" "+Words.ADDED_TO_PLAYER_REPO);
	}
	/**
	 *  
	 * @param name String
	 */
	public void userRemoved(String name) {
		System.out.println(name+" "+Words.REMOVED_FROM_PLAYER_REPO);
	}
	/**
	 *  Shows static String when computer is deleted
	 * 
	 */
	public void deletionStarted() {
		System.out.println(Words.START_DELETION_COMPUTER);
	}
	/**
	 *  Prints ship before update/downgrade
	 * @param shipBefore String
	 */
	public void ShipBefore(String shipBefore) {
		System.out.println(Words.SHIP_BEFORE+shipBefore);
	}
	/**
	 *  Prints ship after update/downgrade
	 * @param shipAfter String
	 */
	public void ShipAfter(String shipAfter) {
		System.out.println(Words.SHIP_AFTER+shipAfter);
	}
	

}
