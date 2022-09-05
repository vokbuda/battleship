package com.battlefield.tests;

import java.util.TreeSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.battlefield.boards.Cell;
import com.battlefield.ships.PatrolBoat;

class PatrolBoatTest {

	/**
	 *  Tests if PatrolBoat have treeSet equal to null
	 */
	@Test
	@DisplayName("PatrolBoat with treeset equals to null")
	void testNullCells() {
		
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			PatrolBoat battleShip=new PatrolBoat(null);
		} );
	}
	/**
	 *  Tests if PatrolBoat can have different size of Cells
	 */
	@Test
	@DisplayName("PatrolBoat with no appropriate treeSet size")
	void testCellsSizeIsNotCorrect() {
		Assertions.assertThrows(IllegalArgumentException.class,()->{
			TreeSet treeSet=new TreeSet<>();
			treeSet.add(new Cell('A',5));			
			PatrolBoat battleShip=new PatrolBoat(treeSet);
		} );
	}

}
