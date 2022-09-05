package com.battlefield.tests;

import java.util.TreeSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.battlefield.boards.Cell;
import com.battlefield.ships.Carrier;
import com.battlefield.ships.Destroyer;

class DestroyerTest {

	/**
	 *  Tests if Destroyer can have treeSet equal to null
	 */
	@Test
	@DisplayName("Destroyer with treeset equals to null")
	void testNullCells() {
		
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			Carrier battleShip=new Carrier(null);
		} );
	}
	/**
	 *  Tests if destroyer has appropriate size
	 */
	@Test
	@DisplayName("Destroyer with no appropriate treeSet size")
	void testCellsSizeIsNotCorrect() {
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			TreeSet treeSet=new TreeSet<>();
			treeSet.add(new Cell('A',5));
			treeSet.add(new Cell('B',5));			
			Destroyer battleShip=new Destroyer(treeSet);
		} );
	}

}
