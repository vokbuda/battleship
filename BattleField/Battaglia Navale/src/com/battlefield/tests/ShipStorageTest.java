package com.battlefield.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.battlefield.settings.Words;
import com.battlefield.ships.ShipStorage;

class ShipStorageTest {
	ShipStorage shipStorage;
	/**
	 *  initial setup
	 */
	@BeforeEach
	void before() {
		this.shipStorage=new ShipStorage();
	}
	/**
	 *  Checks if quantity of types equal to 5
	 */
	@Test
	@DisplayName("Quantity types should equal to 5")
	void testQuantityTypes() {
		assertEquals(shipStorage.getShipMap().keySet().size(), 5); 
	}
	/**
	 *  Checks if types are in map
	 */
	@Test
	@DisplayName("Map contains certain types")
	void initialTypes() {
		Assertions.assertTrue(shipStorage.getShipMap().containsKey(Words.SUBMARINE));
		Assertions.assertTrue(shipStorage.getShipMap().containsKey(Words.CARRIER));
		Assertions.assertTrue(shipStorage.getShipMap().containsKey(Words.PATROL_BOAT));
		Assertions.assertTrue(shipStorage.getShipMap().containsKey(Words.DESTROYER));
		Assertions.assertTrue(shipStorage.getShipMap().containsKey(Words.BATTLESHIP));
	}
	

}
