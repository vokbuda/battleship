package com.battlefield.tests;

import java.util.TreeSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.battlefield.boards.BoardForDefence;
import com.battlefield.boards.BoardHelper;
import com.battlefield.boards.Cell;
import com.battlefield.player.Human;
import com.battlefield.player.Player;
import com.battlefield.player.PlayerRepository;
import com.battlefield.ships.PatrolBoat;
import com.battlefield.ships.Ship;

class BoardHelperTest {
	Player human;
	BoardForDefence boardForDefence;
	/**
	 *  Initial setup before each method
	 */
	@BeforeEach
	void before() {
		human=new Human("Andrei", new PlayerRepository());
		new PlayerRepository().addHuman((Human)human);
		boardForDefence=human.getBoardForDefence();
	}
	/**
	 *  Tests if patrol boat had been added to defence board
	 */
	@Test
	@DisplayName("Addition patrolBoat to defence board")
	void testAddToDefenceBoard() {
		TreeSet<Cell> setOfCells=new TreeSet();
		setOfCells.add(new Cell('A',1));
		setOfCells.add(new Cell('A',2));
		Ship ship=new PatrolBoat(setOfCells);
		BoardHelper.INSTANCE.addShipToBoardForDefence(ship, boardForDefence);
		boardForDefence.getBoardMap().get(new Cell('A',1)).toString().equals("PatrolBoat [TypeShip()=Patrol boat, Space=[Cell(A, 1), Cell(A, 2)], Class=class com.battagliaNavale.ships.PatrolBoat]");
		
	}
	/**
	 *  Tests if generation of horizontal ships is working
	 */
	@Test
	@DisplayName("Generation horizontal ship")
	void generationHorizontalShip() {
		Object[] cellsShip=BoardHelper.INSTANCE.generateHorizontalShip(new Cell('A',1), new Cell('D',1));
		TreeSet mainSet=new TreeSet();
		mainSet.add(new Cell('A',1));
		mainSet.add(new Cell('B',1));
		mainSet.add(new Cell('C',1));
		mainSet.add(new Cell('D',1));
		Assertions.assertTrue(cellsShip[1].equals(mainSet)); 
	}
	/**
	 *  Tests if vertical ship is generated
	 */
	@Test
	@DisplayName("Generation vertical ship")
	void generationVerticalShip() {
		Object[] cellsShip=BoardHelper.INSTANCE.generateVerticalShip(new Cell('A',1), new Cell('A',4));
		TreeSet mainSet=new TreeSet();
		mainSet.add(new Cell('A',1));
		mainSet.add(new Cell('A',2));
		mainSet.add(new Cell('A',3));
		mainSet.add(new Cell('A',4));
		Assertions.assertTrue(cellsShip[1].equals(mainSet)); 
		
	}
	
	

}
