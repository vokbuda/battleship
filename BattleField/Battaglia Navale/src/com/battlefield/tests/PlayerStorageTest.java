package com.battlefield.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.battlefield.player.Human;
import com.battlefield.player.PlayerRepository;
import com.battlefield.player.PlayerStorage;

class PlayerStorageTest {
	//We can control size of PlayerStorage
	PlayerStorage playerStorage;
	
	
	
	
	/**
	 * Tests if size of playerStorage equals to "0"
	 */
	@Test
	@DisplayName("Check if arraylist size equals to 0")
	void test() {
		this.playerStorage=new PlayerStorage(new ArrayList<>());
		assertEquals(this.playerStorage.getPlayerList().size(), 0);
				
	}
	/**
	 *  Tests size of playerStorage after addition of one player
	 */
	@Test
	@DisplayName("Check if arraylist size equals to 1")
	void secondTest() {
		this.playerStorage=new PlayerStorage(Arrays.asList(new Human("Some",new PlayerRepository())));
		assertEquals(this.playerStorage.getPlayerList().size(), 1);
	}
	
	
	/**
	 *  Tests size of playerStorage after addition of 2 players
	 */
	@Test
	@DisplayName("Check if arraylist size equals to 2")
	void thirdTest() {
		this.playerStorage=new PlayerStorage(Arrays.asList(new Human("Some",new PlayerRepository()),new Human("One",new PlayerRepository())));
		assertEquals(this.playerStorage.getPlayerList().size(), 2);
	}
	
	
	

}
