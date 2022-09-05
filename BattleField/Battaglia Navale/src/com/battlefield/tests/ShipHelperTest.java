package com.battlefield.tests;

import java.util.TreeSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.battlefield.boards.Cell;
import com.battlefield.ships.Battleship;
import com.battlefield.ships.Carrier;
import com.battlefield.ships.PatrolBoat;
import com.battlefield.ships.ShipHelper;
import com.battlefield.ships.ShipRepository;

class ShipHelperTest {
	ShipRepository shipRepo;
	
	/**
	 *  Initial setup before each method
	 */
	@BeforeEach
	void before() {
		shipRepo=new ShipRepository();
		
	}
	@Test
	@DisplayName("Return appropriate ship->PatrolBoat")
	void testPatrolBoat() {
		TreeSet<Cell> treeSetCells=new TreeSet<>();
		treeSetCells.add(new Cell('G',1));
		treeSetCells.add(new Cell('G',2));
		Assertions.assertTrue(ShipHelper.returnAppropriateShip(treeSetCells, shipRepo)[1] instanceof PatrolBoat);
	}
	/**
	 *  Method checks if ship with 5 cells is Carrier
	 */
	@Test
	@DisplayName("Return appropriate ship->Carrier")
	void testCarrier() {
		TreeSet<Cell> treeSetCells=new TreeSet<>();
		treeSetCells.add(new Cell('A',1));
		treeSetCells.add(new Cell('A',2));
		treeSetCells.add(new Cell('A',3));
		treeSetCells.add(new Cell('A',4));
		treeSetCells.add(new Cell('A',5));
		Assertions.assertTrue(ShipHelper.returnAppropriateShip(treeSetCells, shipRepo)[1] instanceof Carrier);
	}
	/**
	 *  Method checks if ship with 4 cells is Battleship
	 */
	@Test
	@DisplayName("Return appropriate Ship->BattleShip")
	void testBattleship() {
		TreeSet<Cell> treeSetCells=new TreeSet<>();
		treeSetCells.add(new Cell('A',1));
		treeSetCells.add(new Cell('A',2));
		treeSetCells.add(new Cell('A',3));
		treeSetCells.add(new Cell('A',4));
		Assertions.assertTrue(ShipHelper.returnAppropriateShip(treeSetCells, shipRepo)[1] instanceof Battleship);
	}

}
