package com.battlefield.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.battlefield.userInput.UserInputCheck;

class UserInputCheckTest {
	
	/**
	 *  Test controls method which verifies if cell is in range
	 */
	@Test
	@DisplayName("Control if we have appropriate value of cell")
	void checkInputCell() {
		Assertions.assertEquals(UserInputCheck.INSTANCE.checkInputCell("A 10"), true); 
	}
	/**
	 *   Test controls method which verifies if cell is not in range and not in correct format
	 */
	@Test
	@DisplayName("Control if we have not appropriate value of cell")
	void checkNotValidCell() {
		Assertions.assertEquals(UserInputCheck.INSTANCE.checkInputCell("100 A"),false);
		Assertions.assertEquals(UserInputCheck.INSTANCE.checkInputCell("A-100"),false);
	}
	/**
	 *  The first assertion checks invalid cell, the second-valid
	 */
	@Test
	@DisplayName("Cell is not out of range control")
	void checkRangeCell() {
		Assertions.assertEquals(UserInputCheck.INSTANCE.checkCellInputRange('A',100), false);
		Assertions.assertEquals(UserInputCheck.INSTANCE.checkCellInputRange('G',5), true);
	}
	/**
	 *  The first assertion checks if input number less than total quantity of submenus
	 */
	@Test
	@DisplayName("Control input less than size of menu")
	void controlMenuSize() {
		Assertions.assertEquals(UserInputCheck.INSTANCE.checkIntInputRange(2,3), true);
		Assertions.assertEquals(UserInputCheck.INSTANCE.checkIntInputRange(4,3), true);
	}
	/**
	 *  The first assertion checks if input number less than total quantity of submenus(with submenu quit)
	 */
	@Test
	@DisplayName("Control input(With Quit) less than size of menu")
	void controlMenuSizeQuit() {
		Assertions.assertEquals(UserInputCheck.INSTANCE.checkIntInputRangeQuit(5,3),true);
		Assertions.assertEquals(UserInputCheck.INSTANCE.checkIntInputRangeQuit(6,3),false);
	}
	
	
	

}
