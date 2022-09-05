package com.battlefield.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.battlefield.boards.BoardForAttack;
import com.battlefield.boards.Cell;
import com.battlefield.player.Human;
import com.battlefield.player.Player;
import com.battlefield.player.PlayerRepository;
import com.battlefield.settings.Words;

class BoardForAttackTest {
	BoardForAttack boardForAttack;
	Player andrei;
	
	/**
	 *  Method for initial setup of tests
	 */
	@BeforeEach
	void before() {
		PlayerRepository playerRepo=new PlayerRepository();
		andrei=new Human("Andrei",playerRepo);
		playerRepo.addHuman((Human)andrei);
		boardForAttack=new BoardForAttack(andrei);
	}
	/**
	 *  Checks if board for attack contains Cell(A, 1)
	 */
	@Test
	@DisplayName("Board for attack contains Cell(A 1)")
	void testContainsCellA1() {
		boolean containsCell= boardForAttack.getBoardMap().containsKey(new Cell('A',1));
		assertEquals(containsCell, true);
	}
	/**
	 *  Tests if board for attack doesn't contain Cell(A,20)
	 */
	
	@Test
	@DisplayName("Board for attack doesn't contain Cell(A 20)")
	void testDoesntContainCell() {
		boolean doesnContainCell=boardForAttack.getBoardMap().containsKey(new Cell('A',20));
		assertEquals(doesnContainCell, false);
	}
	/**
	 *  Tests attack from one player to another, compares cell where player had been shoot
	 */
	@Test
	@DisplayName("Attack with one board to another")
	void testAttack() {
		PlayerRepository playerRepo=new PlayerRepository();
		Player anotherPlayer=new Human("Dubkov",playerRepo);
		playerRepo.addHuman((Human)anotherPlayer);
		boardForAttack.attack(new Cell('A',1) , playerRepo,andrei );
		String result=(String) anotherPlayer.getBoardForDefence().getBoardMap().get(new Cell('A',1));
		Assertions.assertEquals(result, Words.SHOT);
	}

}
