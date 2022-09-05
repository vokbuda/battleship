package com.battlefield.ships;

import java.util.Map;

import com.battlefield.settings.Status;

/**
 * @author dubkov
 *
 * @param <T> is for ships
 */
public interface RepositoryShips<T> {
	/**
	 * @return true if player is ready to play
	 */
	boolean checkIsReadyToPlay();
	/**
	 * @param t is ship to add in Ship Repository
	 * @return Object with Status inside
	 */
	Object[] add(T t);
	/**
	 * @return returns all ships with their types
	 */
	Map getAll();
	/**
	 * @param oldT for old ship
	 * @param newT for new ship
	 * @return Status
	 */
	Status update(T oldT,T newT);
	/**
	 * @param ship ship to remove
	 * @return Status successful if operation had been executed
	 */
	Status removeShip(Ship ship);
	/**
	 * @param ship ship which will be checked
	 * @return true if it is possible to add ship
	 */
	boolean checkQuantityIfAdd(Ship ship);
	/**
	 * @param ship ship to check
	 * @return true if it is possible to remove ship
	 */
	boolean checkQuantityIfRemove(Ship ship);
	/**
	 * @return quantity of ships in Ship Repository
	 */
	int getQuantityOfAllShip();
	/**
	 * Removes all ships from repository
	 */
	void removeAll();
	/**
	 * @return StringBuilder's object
	 */
	StringBuilder getAvailableShips();
}
