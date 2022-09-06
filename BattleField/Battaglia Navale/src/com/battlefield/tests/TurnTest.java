package com.battlefield.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.battlefield.player.Human;
import com.battlefield.player.PlayerRepository;
import com.battlefield.player.Turn;
import com.battlefield.settings.Status;

class TurnTest {
	PlayerRepository playerRepo;
	@BeforeEach
	void removeAllFromRepo() {
		playerRepo=new PlayerRepository();
		playerRepo.removeAll();
		
	}
	/**
	 *  test2HumansTurnAllocation method checks if after turn of one player there will be turn of the next player
	 */
	@Test
	@DisplayName("Turn test->1)Human and 2)Human, after allocation Turn we check if we turn to the next Human")
	void test2HumansTurnAllocation() {
		playerRepo.addHuman(new Human("andrei",playerRepo));
		playerRepo.addHuman(new Human("dubkov",playerRepo));
		Turn someTurn=new Turn(playerRepo);
		someTurn.executionNextTurnAllocationShip();
		assertEquals(someTurn.getPlayerTurnGame().getName(), "dubkov");
		
	}
	/**
	 *  
	 * Sequence of turns:
	 * 1)Human
	 * 2)Computer
	 * After execution of turn test method controls if that turn is of the same human
	 */
	@Test
	@DisplayName("Turn test->1)Human and 2)Computer, after human turn we should have a turn of the same human")
	void testHumanCompTurnAllocation() {
		playerRepo.addHuman(new Human("dubkov",playerRepo));
		playerRepo.addComputer();
		Turn someTurn=new Turn(playerRepo);
		someTurn.executionNextTurnAllocationShip();
		assertEquals(someTurn.getPlayerTurnGame().getName(), "dubkov");
	}
	/**
	 * 
	 * Sequence of turns:
	 * 1)Computer
	 * 2)Human
	 * After human turn test method checks if there is the same turn after
	 */
	@Test
	@DisplayName("Turn test->1)Computer and 2)Human, after human turn we should have a turn of the same human")
	void testComputerHumanTurnAllocation() {
		
		playerRepo.addComputer();
		Human andrei=new Human("andrei", playerRepo);
		playerRepo.addHuman(andrei);
		Turn someTurn=new Turn(playerRepo);
		someTurn.executionNextTurnAllocationShip();
		assertEquals(someTurn.getPlayerTurnGame(), andrei);
	}
	/**
	 *  Checks if player is ready to play
	 */
	@Test
	@DisplayName("Turn test-> after execution of all allocation we will start our game")
	void testStartGameComputerHumanTurnAllocation() {
		playerRepo.addComputer();
		playerRepo.addHuman(new Human("andrei",playerRepo));
		Turn someTurn=new Turn(playerRepo);
		someTurn.getPlayerTurnAllocation();
		assertEquals(Status.StartGame, someTurn.executionNextTurnAllocationShip()[0]);
		
		
	}
	
	

}
