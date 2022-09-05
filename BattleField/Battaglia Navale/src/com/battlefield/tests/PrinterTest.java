package com.battlefield.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.battlefield.printer.Printer;
import com.battlefield.settings.Words;

/**
 * @author dubkov
 *
 */
class PrinterTest {
	ByteArrayOutputStream outContent;
	/**
	 *  Initial setup
	 */
	@BeforeEach
	public void before() {
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	}
	/**
	 *  Tests output of System.out.println()
	 */
	@Test
	@DisplayName("Test simple System.out.println()")
	public void testSimpleOutput() {
	    Printer.INSTANCE.printString("something");
	    assertEquals(outContent.toString().trim(), "something");
	}
	/**
	 *  Tests output of added ship
	 */
	@Test
	@DisplayName("Test added ship")
	public void testAddedShip() {
		Printer.INSTANCE.addedShip(Words.BATTLESHIP);
		assertEquals(outContent.toString().trim(), Words.BATTLESHIP+" "+Words.WAS_ADDED_TO_DEFENCE_BOARD);
	}
	/**
	 *  Tests output of winner
	 */
	@Test
	@DisplayName("Test winner")
	public void testWinner() {
		Printer.INSTANCE.winner(Words.BATTLESHIP);
		assertEquals(outContent.toString().trim(), Words.BATTLESHIP+" "+Words.WON);
	}
	
	/**
	 *  Tests output of user's addition
	 */
	@Test
	@DisplayName("Test user addition")
	public void testAdditionUser() {
		Printer.INSTANCE.userAdded("Dubkov");
		assertEquals(outContent.toString().trim(),"Dubkov"+" "+Words.ADDED_TO_PLAYER_REPO );
	}
	/**
	 *  Tests output of labels in menu
	 */
	@Test
	@DisplayName("Test labelFormatMenu")
	public void testSomething() {
		Printer.INSTANCE.labelFormatMenu(2, "Main menu");
		assertEquals(outContent.toString().trim(), 3+"."+"Main menu");
	}
	/**
	 *  Tests output of ship name
	 */
	@Test
	@DisplayName("Test shipafter ")
	public void testShipAfter() {
		String shipAfter="Jolly";
		Printer.INSTANCE.ShipAfter(shipAfter);
		assertEquals(outContent.toString().trim(),Words.SHIP_AFTER+shipAfter);
	}
	
	/**
	 *  Tests output of ship name
	 */
	@Test
	@DisplayName("Test shipBefore ")
	public void testShipBefore() {
		String shipBefore="Jolly";
		Printer.INSTANCE.ShipBefore(shipBefore);
		assertEquals(outContent.toString().trim(),Words.SHIP_BEFORE+shipBefore);
	}
	/**
	 *  Tests output of removed user's name
	 */
	@Test
	@DisplayName("Printer userRemoved ")
	public void testUserRemoved() {
		String name="dubkov";
		Printer.INSTANCE.userRemoved(name);
		assertEquals(outContent.toString().trim(), name+" "+Words.REMOVED_FROM_PLAYER_REPO);
	}
	

}
