package com.battlefield.tests;

import java.util.TreeSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.battlefield.ships.Battleship;

class BattleshipTest {
	
	/**
	 * 
	 * Checks if it possible to initialize BattleShip with treeSet equal to null
	 */
	@Test
	@DisplayName("Battleship with treeset equals to null")
	void testNullCells() {
		
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			Battleship battleShip=new Battleship(null);
		} );
	}
	/**
	 * 
	 * 
	 * Checks if it is possible to initialize BattleShip with 0 Cells
	 */
	@Test
	@DisplayName("BattleShip with treeset size 0")
	void testCellsSizeIsNotCorrect() {
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			Battleship battleShip=new Battleship(new TreeSet<>());
		} );
	}
	

}
