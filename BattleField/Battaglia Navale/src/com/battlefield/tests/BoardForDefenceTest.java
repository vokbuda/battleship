package com.battlefield.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.battlefield.boards.BoardForDefence;
import com.battlefield.boards.Cell;
import com.battlefield.player.Human;
import com.battlefield.player.Player;
import com.battlefield.player.PlayerRepository;
import com.battlefield.ships.Battleship;
import com.battlefield.ships.Carrier;
import com.battlefield.ships.Destroyer;
import com.battlefield.ships.PatrolBoat;
import com.battlefield.ships.Ship;
import com.battlefield.ships.ShipRepository;
import com.battlefield.ships.Submarine;

class BoardForDefenceTest {
	Player human;
	BoardForDefence boardForDefence;
	/**
	 *  Initial Setup
	 */
	@BeforeEach
	void before() {
		human=new Human("Andrei", new PlayerRepository());
		new PlayerRepository().addHuman((Human)human);
		boardForDefence=human.getBoardForDefence();
	}
	/**
	 *  Tests patrol boat addition to board for defence
	 */
	@Test
	@DisplayName("Test addition Patrol Boat")
	void testAdditionPatrolBoat() {
		ShipRepository shipRepo=human.getShipRepo();
		Object[]result=boardForDefence.generateShipOnTable(new Cell('A',1), new Cell('A',2), shipRepo);
		assertEquals(boardForDefence.getBoardMap().get(new Cell('A',1)).getClass(),PatrolBoat.class);
	}
	/**
	 *  Tests carrier addition to board for defence
	 */
	@Test
	@DisplayName("Test addition Carrier")
	void testAdditionCarrier() {
		ShipRepository shipRepo=human.getShipRepo();
		Object[]result=boardForDefence.generateShipOnTable(new Cell('G',1), new Cell('G',5), shipRepo);
		assertEquals(boardForDefence.getBoardMap().get(new Cell('G',1)).getClass(),Carrier.class);
	}
	/**
	 *  Tests BattleShip addition to board for defence
	 */
	@Test
	@DisplayName("Test addition BattleShip")
	void testAdditionBattleShip() {
		ShipRepository shipRepo=human.getShipRepo();
		Object[]result=boardForDefence.generateShipOnTable(new Cell('A',4), new Cell('D',4), shipRepo);
		assertEquals(boardForDefence.getBoardMap().get(new Cell('A',4)).getClass(),Battleship.class);
	}
	/**
	 *  Tests if all ships would be removed from defence board
	 */
	@Test
	@DisplayName("Test remove all ships")
	void testRemoveAllShips() {
		ShipRepository shipRepo=human.getShipRepo();
		Object[]result=boardForDefence.generateShipOnTable(new Cell('A',4), new Cell('D',4), shipRepo);
		boardForDefence.removeAllShips();
		Assertions.assertEquals(boardForDefence.getBoardMap().get(new Cell('A',4)), null);
	}
	@Test
	@DisplayName("Test downgrade ship with cell")
	void downgradeShipWithCell() {
		ShipRepository shipRepo=human.getShipRepo();
		Object[] resultGenerationShip= boardForDefence.generateShipOnTable(new Cell('A',4), new Cell('D',4), shipRepo);
		shipRepo.add((Ship)resultGenerationShip[1]);
		Object[]result=boardForDefence.downgradeShipWithCell(new Cell('A',4), shipRepo);
		Ship resultShip=(Ship) result[2];
		Assert.assertTrue(resultShip.getClass().equals(Destroyer.class)||resultShip.getClass().equals(Submarine.class));
		
	}
	/**
	 *  Tests upgrade of ship from battleship to carrier
	 */
	@Test
	@DisplayName("Test upgrade ship with cell BattleShip->Carrier")
	void upgradeShipWithCell() {
		ShipRepository shipRepo=human.getShipRepo();
		Object[] resultGenerationShip= boardForDefence.generateShipOnTable(new Cell('A',4), new Cell('D',4), shipRepo);
		shipRepo.add((Ship)resultGenerationShip[1]);		
		Object[]result=boardForDefence.upgradeShipWithCell(new Cell('E',4), shipRepo);
		Ship resultShip=(Ship) result[2];
		Assert.assertTrue(resultShip.getClass().equals(Carrier.class));
	}
	/**
	 *  Tests remove ship by choosing cell
	 */
	@Test
	@DisplayName("Test remove ship with cell")
	void testRemoveShipWithCell(){
		ShipRepository shipRepo=human.getShipRepo();
		Object[] resultGenerationShip= boardForDefence.generateShipOnTable(new Cell('A',4), new Cell('D',4), shipRepo);
		shipRepo.add((Ship)resultGenerationShip[1]);
		boardForDefence.removeShipByCell(new Cell('B',4));
		Assertions.assertEquals(null, boardForDefence.getBoardMap().get(new Cell('A', 4)));
	}
	
	

}
