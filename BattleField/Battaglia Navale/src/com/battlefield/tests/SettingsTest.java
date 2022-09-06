package com.battlefield.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.battlefield.settings.Words;

class SettingsTest {

	/**
	 *  checks limit of Patrol Boats
	 */
	@Test
	@DisplayName("detectLimitTest for quantity of Patrol Boat")
	void testDetectLimitPatrolBoat() {
		Assertions.assertEquals(Words.detectLimit("Patrol boat"), 3);
	}
	/**
	 *  checks limit of Carrier
	 */
	@Test
	@DisplayName("detectLimitTest for quantity of Carrier")
	void testDetectLimitCarrier() {
		Assertions.assertEquals(Words.detectLimit("Carrier"), 1);
	}
	/**
	 *  checks limit of Battleship
	 */
	@Test
	@DisplayName("detectLimitTest for quantity of BattleShip")
	void testDetectLimitBattleShip() {
		Assertions.assertEquals(Words.detectLimit("Battleship"), 1);
	}

}
