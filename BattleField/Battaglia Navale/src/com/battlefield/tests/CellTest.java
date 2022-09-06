package com.battlefield.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.battlefield.boards.Cell;

class CellTest {
	
	/**
	 *  Tests if there are two different object which represent the same cell are equal
	 */
	@Test
	@DisplayName("Cell Test-> equals Testing-> two different memory allocations but with the same values inside")
	void equalsCell() {
		Cell cell=new Cell('A',1);
		Assertions.assertEquals(cell.equals(new Cell('A',1)), true);
	}
	/**
	 *  Tests toString method of Cell.class
	 */
	@Test
	@DisplayName("Cell Test-> toString method-> check implementation of  redefined toString() ")
	void cellToString() {
		Cell someCell=new Cell('A',10);
		Assertions.assertEquals(someCell.toString(), new Cell('A',10).toString()); 
	}
	/**
	 *  Tests compareToMethod:check if one cell is higher than other on the same line
	 */
	@Test
	@DisplayName("Cell Test-> compareTo method-> in current program check if one cell is higher than other on the same line")
	void cellCompareTo() {
		Cell littleCell=new Cell('A',1);
		Cell bigCell=new Cell('A',10);
		Assertions.assertEquals(bigCell.compareTo(littleCell), 1);
		
	}
	/**
	 *  Tests compareToMethod:check if one cell is on the left of other on the same line
	 */
	@Test
	@DisplayName("Cell Test-> compareTo method-> in current program check if one cell is on the left of other on the same line")
	void leftCell() {
		Cell leftCell=new Cell('A',1);
		Cell rightCell=new Cell('H',1);
		Assertions.assertEquals(rightCell.compareTo(leftCell), 1);
	}
	/**
	 *  Null CompareTo Method
	 */
	@Test
	@DisplayName("Cell Test-> compareTo(null object)")
	void nullCompareTo() {
		Cell cell=new Cell('A',1);
		Assertions.assertThrows(RuntimeException.class,()->{
			cell.compareTo(null);
		});
	}

}
