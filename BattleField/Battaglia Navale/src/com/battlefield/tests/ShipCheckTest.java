package com.battlefield.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.TreeSet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.battlefield.boards.Cell;
import com.battlefield.settings.Status;
import com.battlefield.ships.ShipCheck;

class ShipCheckTest {

	/**
	 *  Checks if created ship is Patrol Boat
	 */
	@Test
	@DisplayName("Test for patrol boat")
	void testPatrolBoat() {
		TreeSet<Cell> cellsForShip=new TreeSet<>();
		cellsForShip.add(new Cell('A',1));
		cellsForShip.add(new Cell('A',2));
		Status result=ShipCheck.checkPatrolBoat(cellsForShip);
		assertEquals(result,Status.OK );
		
	}
	/**
	 *  Checks if created ship is Destroyer
	 */
	@Test
	@DisplayName("Test for destroyer")
	void testDestroyer() {
		TreeSet<Cell> cellsForShip=new TreeSet<>();
		cellsForShip.add(new Cell('A',1));
		cellsForShip.add(new Cell('A',2));
		cellsForShip.add(new Cell('A',3));
		Status result=ShipCheck.checkDestroyer(cellsForShip);
		assertEquals(result,Status.OK );
	}
	/**
	 *  Checks if created ship is Submarine
	 */
	@Test
	@DisplayName("Test for Submarine")
	void testSubmarine() {
		TreeSet<Cell> cellsForShip=new TreeSet<>();
		cellsForShip.add(new Cell('A',1));
		cellsForShip.add(new Cell('A',2));
		cellsForShip.add(new Cell('A',3));
		Status result=ShipCheck.checkSubmarine(cellsForShip);
		assertEquals(result,Status.OK );
		
	}
	/**
	 *  Checks if created ship is carrier
	 */
	@Test
	@DisplayName("Test for Battleship")
	void testBattleShip() {
		TreeSet<Cell> cellsForShip=new TreeSet<>();
		cellsForShip.add(new Cell('A',1));
		cellsForShip.add(new Cell('A',2));
		cellsForShip.add(new Cell('A',3));
		cellsForShip.add(new Cell('A',4));
		Status result=ShipCheck.checkBattleship(cellsForShip);
		assertEquals(result,Status.OK );
		
	}
	/**
	 *  Checks if created ship is carrier
	 */
	@Test
	@DisplayName("Test for Carrier")
	void testCarrier() {
		TreeSet<Cell> cellsForShip=new TreeSet<>();
		cellsForShip.add(new Cell('A',1));
		cellsForShip.add(new Cell('A',2));
		cellsForShip.add(new Cell('A',3));
		cellsForShip.add(new Cell('A',4));
		cellsForShip.add(new Cell('A',5));
		Status result=ShipCheck.checkCarrier(cellsForShip);
		assertEquals(result,Status.OK );
		
	}

}
