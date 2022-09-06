package com.battlefield.player;

import java.util.List;

/**
 * @author dubkov
 *
 * @param <T> player's
 */
public interface RepositoryPlayers<T> {
	/**
	 * @param t t is Human to add in PlayerRepo
	 * @return array of objects with Status inside
	 */
	Object[] addHuman(Human t);
	/**
	 * @return returns all users
	 */
	List<T> getAll();
	/**
	 * @return all active players in game
	 */
	List<T> getAllActivePlayers();
	/**
	 * @param oldT oldPlayer which needs to be updated
	 * @param newT newPlayer which needs to be added
	 * @return status and players in class where this method will be overriden
	 */
	Object[] update(T oldT,T newT);
	/**
	 * Removes all players from repository
	 */
	void removeAll();
	/**
	 * @return size of PlayerRepository(quantity of players)
	 */
	int getSize();
	/**
	 * @return Winner of game
	 */
	Player getWinner();
	/**
	 * @return Object with status depending on fact if somebody had been deleted
	 */
	Object[] eliminationUsers();
	/**
	 * @return Object with deleted computer and Status
	 */
	Object[] removeLastComputer();
	/**
	 * @return array of Objects with Status
	 */
	Object[] addComputer();
	/**
	 * @param user parameter for user deletion
	 * @return Object with status if operation had been executed with success
	 */
	Object[] removeUser(T user);
	/**
	 * @return object of StringBuilder which shows how many players are in PlayerRepository and who they are
	 */
	StringBuilder showSituation();
}
