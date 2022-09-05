package com.battlefield.tests;

import java.util.TreeSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.battlefield.boards.Cell;
import com.battlefield.ships.Submarine;

class SubmarineTest {

	@Test
	@DisplayName("Submarine with treeset equals to null")
	void testNullCells() {
		
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			Submarine battleShip=new Submarine(null);
		} );
	}
	/**
	 *  Tests if battleship has correct number of cells inside
	 */
	@Test
	@DisplayName("BattleShip with no appropriate treeSet size")
	void testCellsSizeIsNotCorrect() {
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			TreeSet treeSet=new TreeSet<>();
			treeSet.add(new Cell('A',5));
			treeSet.add(new Cell('B',5));			
			Submarine battleShip=new Submarine(treeSet);
		} );
	}
}
