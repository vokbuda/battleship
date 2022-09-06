package com.battlefield.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.battlefield.player.Human;
import com.battlefield.player.Player;
import com.battlefield.player.PlayerRepository;
import com.battlefield.settings.Status;

class PlayerRepositoryTest {
	PlayerRepository playerRepo;
	/**
	 *  Initial Setup
	 */
	@BeforeEach
	void startRepo() {
		playerRepo=new PlayerRepository();
		playerRepo.removeAll();
	}
	/**
	 *  Addition of one Human and 2 Computers, expected size is 2
	 */
	@Test
	@DisplayName("PlayerRepo Test->Attempt to add 1 Human and 2 computers")
	void addPlayerHuman2Comps() {
		
		playerRepo.addHuman(new Human("Andrei",playerRepo));
		playerRepo.addComputer();
		playerRepo.addComputer();
		assertEquals(playerRepo.getSize(), 2);
	}
	/**
	 *  Addition of 4 Humans and 5 Computers, expected size is 2
	 */
	@Test
	@DisplayName("PlayerRepo Test-> Here were added 9 users->4 Humans and  5 Comps->Expected Size=2 because of implementation of this game")
	
	void addPlayer4Humans5Comps() {
		
		
		playerRepo.addHuman(new Human("Andrei",playerRepo));
		playerRepo.addComputer();
		playerRepo.addHuman(new Human("Gabriele",playerRepo));
		playerRepo.addComputer();
		playerRepo.addHuman(new Human("Walter",playerRepo));
		playerRepo.addComputer();
		playerRepo.addComputer();
		playerRepo.addHuman(new Human("Massimo",playerRepo));
		playerRepo.addComputer();
		assertEquals(playerRepo.getSize(), 2);
	}
	/**
	 *  Cancel of computer which is not in PlayerRepo
	 */
	@Test
	@DisplayName("PlayerRepo Test->Cancellation of computer which is not in repo")
	void removeComputerIsNotExisting() {
		Object[] resultCancellationLastComp=playerRepo.removeLastComputer();
		assertEquals(Status.ComputerNotFound, resultCancellationLastComp[0],Status.ComputerNotFound.getResult());	
	}
	/**
	 *  Cancellation of user which is not in PlayerRepository, expected result is Status.UserIsNotFound
	 */
	@Test
	@DisplayName("PlayerRepo Test->Cancellation of human which is not in repo")
	void removePlayerIsNotExisting() {
		
		Object[] resultCancellationLastComp=playerRepo.removeUser(new Human("Andrew",playerRepo));
		assertEquals(Status.UserIsNotFound, resultCancellationLastComp[0],Status.UserIsNotFound.getResult());
	}
	/**
	 *  Addition,Cancellation,Addition of computer units, expected size of PlayerRepo is 1
	 */
	@Test
	@DisplayName("PlayerRepo Test->Addition,Cancellation,Addition of comps->expected result is 1")
	void additionAndCancellationAndAdditionSameComputer() {
		
		playerRepo.addComputer();
		playerRepo.removeLastComputer();
		playerRepo.addComputer();
		assertEquals(playerRepo.getSize(), 1,"Operation remove is not working");
		
		
	}
	/**
	 *  Addition of user with the same name is not possible, expected result is Status.UserAlreadyExists
	 */
	@Test
	@DisplayName("PlayerRepo Test->Attempt to add two users with the same name-> It's not possible")
	void addPlayerWithTheSameName() {
		playerRepo.addHuman(new Human("Andrew",playerRepo));
		Object[] repeatedPlayer=playerRepo.addHuman(new Human("Andrew",playerRepo));
		assertEquals(Status.UserAlreadyExists, repeatedPlayer[0],Status.UserAlreadyExists.getResult());
	}
	/**
	 *  Addition of two computers in PlayerRepository is not possible, expected result is Status.ComputerLimit
	 */
	@Test
	@DisplayName("PlayerRepo Test->Attempt to add two comps->In this version of game it is not possible")
	void addTwoComputers() {
		playerRepo.addComputer();
		Object[] resultPlayerRepo=playerRepo.addComputer();
		assertEquals(resultPlayerRepo[0],Status.ComputerLimit,Status.ComputerLimit.getResult() );	
	}
	/**
	 *  Addition and cancellation of the same user returns size of PlayerRepo equal to 0
	 */
	@Test
	@DisplayName("PlayerRepo test-> Addition and cancellation of the same user")
	void removePlayer() {
		playerRepo.addHuman(new Human("Andrew",playerRepo));
		playerRepo.removeUser(new Human("Andrew",playerRepo));
		assertEquals(playerRepo.getSize(), 0);
	}
	/**
	 *  Tests update player in playerRepo, returns Status.OK
	 */
	@Test
	@DisplayName("PlayerRepo test->Update player")
	void updatePlayer() {
		Player andrei=new Human("Andrei",playerRepo);
		playerRepo.addHuman((Human) andrei);
		Human h1=new Human("Dubkov",playerRepo);
		Object[] ok=playerRepo.update(andrei, h1);
		assertEquals(ok[0],Status.OK);
	}
	
	

}
