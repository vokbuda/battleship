package com.battlefield.tests;

import java.util.TreeSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.battlefield.boards.Cell;
import com.battlefield.ships.Battleship;
import com.battlefield.ships.Carrier;
import com.battlefield.ships.Ship;
import com.battlefield.ships.ShipRepository;

class ShipRepositoryTest {
	ShipRepository shipRepo;
	@BeforeEach
	void before() {
		shipRepo=new ShipRepository();
	}
	/**
	 *  Tests method getAvailableShips(), which must return string with available ships
	 */
	@Test
	@DisplayName("Test getAvailableShips")
	void getAvailableShips() {
		Assertions.assertEquals(shipRepo.getAvailableShips().toString(),("\n"
				+ "AVAILABLE SHIPS ARE\n"
				+ "3 Patrol boat SIZE:2\n"
				+ "2 Destroyer SIZE:3\n"
				+ "3 Submarine SIZE:3\n"
				+ "1 Carrier SIZE:5\n"
				+ "1 Battleship SIZE:4\n"
				+ "----"));
	}
	/**
	 *  
	 */
	@Test
	@DisplayName("control available ships after carrier addition")
	void controlAvailableShipsAfterAddtion() {
		TreeSet setTreeSet=new TreeSet<>();
		setTreeSet.add(new Cell('A',1));
		setTreeSet.add(new Cell('A',2));
		setTreeSet.add(new Cell('A',3));
		setTreeSet.add(new Cell('A',4));
		setTreeSet.add(new Cell('A',5));
		Ship carrier=new Carrier(setTreeSet);
		shipRepo.add(carrier);
		Assertions.assertEquals(shipRepo.getAvailableShips().toString(), ("\n"
				+ "AVAILABLE SHIPS ARE\n"
				+ "3 Patrol boat SIZE:2\n"
				+ "2 Destroyer SIZE:3\n"
				+ "3 Submarine SIZE:3\n"
				+ "0 Carrier SIZE:5\n"
				+ "1 Battleship SIZE:4\n"
				+ "----"));		
	}
	/**
	 *  Addition and remove ship and checks available ships after
	 */
	@Test
	@DisplayName("Control available ships after addition and cancellation")
	void controlAvailableShipsAfterCreationAndCancellation() {
		TreeSet setTreeSet=new TreeSet<>();
		setTreeSet.add(new Cell('A',1));
		setTreeSet.add(new Cell('A',2));
		setTreeSet.add(new Cell('A',3));
		setTreeSet.add(new Cell('A',4));
		
		Ship battleShip=new Battleship(setTreeSet);
		shipRepo.add(battleShip);
		shipRepo.removeShip(battleShip);
		Assertions.assertEquals(shipRepo.getAvailableShips().toString(),("\n"
				+ "AVAILABLE SHIPS ARE\n"
				+ "3 Patrol boat SIZE:2\n"
				+ "2 Destroyer SIZE:3\n"
				+ "3 Submarine SIZE:3\n"
				+ "1 Carrier SIZE:5\n"
				+ "1 Battleship SIZE:4\n"
				+ "----"));
		
	}
	/**
	 *  Checks if there is insufficient quantity of ships to start game
	 */
	@Test
	@DisplayName("Test if there is insufficient quantity of ships to start game")
	void controlAvailableShipsAfter() {
		Assertions.assertFalse(shipRepo.checkIsReadyToPlay()); 
	}
	/**
	 *  Test if there is insufficient quantity of ships to start game, after addition of carrier and battleship
	 */
	@Test
	@DisplayName("Test if there is insufficient quantity of ships to start game")
	void controlAvailableShips() {
		TreeSet setTreeSet=new TreeSet<>();
		setTreeSet.add(new Cell('A',1));
		setTreeSet.add(new Cell('A',2));
		setTreeSet.add(new Cell('A',3));
		setTreeSet.add(new Cell('A',4));
		setTreeSet.add(new Cell('A',5));
		Ship carrier=new Carrier(setTreeSet);
		shipRepo.add(carrier);
		setTreeSet.clear();
		setTreeSet.add(new Cell('C',1));
		setTreeSet.add(new Cell('C',2));
		setTreeSet.add(new Cell('C',3));
		setTreeSet.add(new Cell('C',4));
		shipRepo.add(new Battleship(setTreeSet));
		Assertions.assertFalse(shipRepo.checkIsReadyToPlay());
		
	}

}
