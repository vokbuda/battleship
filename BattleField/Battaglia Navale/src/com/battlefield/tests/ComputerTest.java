package com.battlefield.tests;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.battlefield.boards.Cell;
import com.battlefield.player.Computer;
import com.battlefield.player.Human;
import com.battlefield.player.PlayerRepository;
import com.battlefield.settings.Words;

class ComputerTest {
	PlayerRepository playerRepo;
	/**
	 *  initial setup
	 */
	@BeforeEach
	void creationRepo() {
		playerRepo=new PlayerRepository();
		playerRepo.removeAll();
	}
	/**
	 *  Tests name of computer instance
	 */
	@Test
	void testNameComputer() {
		Object[] result1= playerRepo.addComputer();
		Computer comp1=(Computer) result1[1];
		Assertions.assertEquals(comp1.getName(), "COMPUTER1"); 		
	}
	/**
	 *  Tests quantity of ships created by computer, must be equal 10
	 */
	@Test
	void generationShipsComp() {
		Object[] result1= playerRepo.addComputer();
		Computer comp1=(Computer) result1[1];
		Assertions.assertEquals(comp1.getQuantityShips(),10);
	}
	/**
	 *  Tests Creation of last cells starting from the first one
	 */
	@Test
	void testFourDirectionsCell() {
		Object[] result1= playerRepo.addComputer();
		Computer comp1=(Computer) result1[1];
		Object[] result4Cells=comp1.cellsFourDirections(new Cell('E',5), 3);
		List result=(List) result4Cells[1];
		Set<Cell> setToVerify  = new HashSet<Cell>(result);
		Set<Cell> setDestination=new HashSet<Cell>();
		setDestination.add(new Cell('E',3));
		setDestination.add(new Cell('E',7));
		setDestination.add(new Cell('G',5));
		setDestination.add(new Cell('C',5));
		Assertions.assertEquals(setToVerify, setDestination);
	}
	/**
	 *  Tests if Computer's Random Attack is working correctly
	 */
	@Test
	void attackRandomCell() {
		Human player=new Human("Andrei",playerRepo);
		playerRepo.addHuman(player);
		player.addShip(new Cell('A',1), new Cell('A',5));
		Object[] result1= playerRepo.addComputer();
		Computer comp1=(Computer) result1[1];
		comp1.randomAttackCell();
		boolean result=player.getBoardForDefence().getBoardMap().containsValue(Words.SHOT)||player.getBoardForDefence().getBoardMap().containsValue(Words.DESTROYED);
		Assertions.assertEquals(result, true); 
		Assertions.assertEquals(comp1.getCellsAttacksRemains().size(), 99);
	}
	

}
