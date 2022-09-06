package com.battlefield.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.battlefield.boards.Cell;
import com.battlefield.player.Human;
import com.battlefield.player.PlayerRepository;
import com.battlefield.settings.Status;
import com.battlefield.ships.Carrier;
import com.battlefield.ships.PatrolBoat;

class HumanTest {
	Human basicHuman;
	/**
	 *  Initial Setup
	 */
	@BeforeEach
	void creationHuman() {
		
		basicHuman=new Human("basic human",new PlayerRepository());
		
		
	}
	/**
	 *  Tests creation of human with null parameters
	 */
	@Test
	@DisplayName("Human Test->Attempt to create human with null parameters")
	void nullHumans() {
		
		Assertions.assertThrows(RuntimeException.class, ()->{
			Human h1=new Human(null,null);
		});
		
	}
	@Test
	@DisplayName("Human Test->Attempt to downgrade ship with null cell")
	void downgradeNullCell() {
		Assertions.assertThrows(RuntimeException.class,()->{
			basicHuman.downgradeShip(null);	
		});
	}
	/**
	 *  Tests downgrade ship with cell which is not on board
	 */
	@Test
	@DisplayName("Human Test->Attempt to downgrade with outbound cell")
	void downgradeOutBoundCell() {
		Assertions.assertThrows(RuntimeException.class,()->{
			basicHuman.downgradeShip(new Cell('A',100));	
		});
	}
	/**
	 *  Tests Carrier addition to defence Board
	 */
	@Test
	@DisplayName("Human Test->Attempt to add ship with start cell and finishCell Carrier")
	void addCarrier2Cells() {
		Object[] resultAddition=basicHuman.addShip(new Cell('A',1), new Cell('A',5));
		assertEquals(resultAddition[1].getClass(),Carrier.class);
	}
	/**
	 *  Tests addition of Patrol Boat
	 */
	@Test
	@DisplayName("Human Test->Attempt to add Patrol Boat")
	void addPatrolBoat() {
		Object[] resultAddition=basicHuman.addShip(new Cell('A',1), new Cell('A',2));
		assertEquals(resultAddition[1].getClass(),PatrolBoat.class);
	}
	/**
	 *  Tests if addition and cancellation from player returns the same quantity of available ships as it was before
	 */
	@Test
	@DisplayName("Human Test->Attempt to add and remove Patrol Boat and we check result with defauld representaion")
	void addPatrolBoatRemovePatrolBoat() {
		Object[] resultAddition=basicHuman.addShip(new Cell('A',1), new Cell('A',2));
		basicHuman.removeShipByCell(new Cell('A',1));
		Assertions.assertEquals(basicHuman.getAvailableShips().toString().equals("\n"
				+ "AVAILABLE SHIPS ARE\n"
				+ "3 Patrol boat SIZE:2\n"
				+ "2 Destroyer SIZE:3\n"
				+ "3 Submarine SIZE:3\n"
				+ "1 Carrier SIZE:5\n"
				+ "1 Battleship SIZE:4\n"
				+ "----"
				), true);
	}
	
	/**
	 *  Tests if it is possible to create ships with cells from method finalCellsForDifferentShips and initial Cell
	 * @param finishCell
	 */
	@DisplayName("Human Test->Add Ship(Start letter is A10, finish letter is taken from list)")
	@ParameterizedTest
	@MethodSource("finalCellsForDifferentShips")
	void addShipTest(Cell finishCell) {
		Cell startCell=new Cell('A',10);
		Object[] resultAddition=basicHuman.addShip(startCell, finishCell);
		Assertions.assertEquals(resultAddition[0], Status.OK);
		
	}
	private static List<Cell> finalCellsForDifferentShips(){
		return Arrays.asList(new Cell('A',7),new Cell('A',8),new Cell('A',9));
	}
	/**
	 *  Tests if Upgraded Battleship is Carrier
	 */
	@Test
	@DisplayName("Human Test-> Upgrade Ship from Battleship to Carrier")
	void updateShipFromBattleShipToCarrier() {
		Cell cellAddToShip=new Cell('A',6);
		basicHuman.addShip(new Cell('A',7), new Cell('A',10));
		basicHuman.upgradeShip(cellAddToShip);
		Assertions.assertEquals(basicHuman.getBoardForDefence().getBoardMap().get(cellAddToShip).getClass(), Carrier.class);	
	}
	/**
	 *  Tests downgraded Destroyer, must be PatrolBoat
	 */
	@Test
	@DisplayName("Human Test-> Downgrade Ship from Destroyer to PatrolBoat")
	void downgradeShipFromDestroyerToPatrolBoat() {
		basicHuman.addShip(new Cell('A',7), new Cell('A',9));
		basicHuman.downgradeShip(new Cell('A',9));
		Assertions.assertEquals(basicHuman.getBoardForDefence().getBoardMap().get(new Cell('A',7)).getClass(), PatrolBoat.class);
	}
	
	
	

}
