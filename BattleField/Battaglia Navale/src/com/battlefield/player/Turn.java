package com.battlefield.player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.battlefield.printer.Printer;
import com.battlefield.settings.Status;
import com.battlefield.settings.Words;

/**
 * @author dubkov
 *
 */
public class Turn {
	private PlayerRepository playerRepo;
	private int indexPlayer;
	
	private List<Boolean> checkTurn;
	/**
	 * @param playerRepo is needed to switch turns
	 */
	public Turn(PlayerRepository playerRepo) {
		this.playerRepo=playerRepo;
		List<Player> playersList=playerRepo.getAllActivePlayers();
		checkTurn=generateListBooleans(playersList.size());
	}
	/**
	 *  all booleans in checkTurn become true
	 */
	public void regenerateTurns() {
		this.checkTurn=(List<Boolean>) this.checkTurn.stream().map(bool->true).collect(Collectors.toList());
	}
	/**
	 * @param size
	 * @return boolean's list which corresponds to the size(as parameter)
	 */
	private List generateListBooleans(int size) {
		List<Boolean>bools=new ArrayList<>();
		for (int i=0;i<size;i++) {
			bools.add(true);
		}
		return bools;
	}
	/**
	 * @return player whose turn is starting of(ship allocation)
	 */
	public Player getPlayerTurnAllocation() {
		Player player=this.playerRepo.getAll().get(indexPlayer);
		while(player.getClass().equals(Computer.class)) {
			if(indexPlayer!=this.playerRepo.getSize()-1) {
				player=this.playerRepo.getAll().get(++indexPlayer);
			}
			else {
				
				indexPlayer=0;
			}
			
		}
		if(checkTurn.get(indexPlayer).equals(true)) {
		Printer.INSTANCE.turnPlayer(player.getName());
		checkTurn.set(indexPlayer,false);
		}
		return player;
	}
	/**
	 * @return player whose turn is starting of(during a game)
	 */
	public Player getPlayerTurnGame() {
		
		Player player=this.playerRepo.getAllActivePlayers().get(indexPlayer);
		while(player.getClass().equals(Computer.class)) {
			turnComputer((Computer)player);
			Printer.INSTANCE.printString(player.getName()+" "+Words.COMMITED_AN_ATTACK);
			if(indexPlayer!=this.playerRepo.getAllActivePlayers().size()-1) {
				
				player=this.playerRepo.getAllActivePlayers().get(++indexPlayer);
			}
			else {
				
				indexPlayer=0;
				player=this.playerRepo.getAll().get(indexPlayer);
			}
		}
		if(checkTurn.get(indexPlayer).equals(true)) {
			Printer.INSTANCE.clearConsole();
			Printer.INSTANCE.turnPlayer(player.getName());
			checkTurn.set(indexPlayer, false);
		}
		
		return player;
	}
	
	/**
	 *  Created for switch among user their turn
	 * @return Status of next turn
	 */
	public Object[] executionNextTurnAllocationShip() {		
		if(++indexPlayer==this.playerRepo.getAllActivePlayers().size()) {
			regenerateTurns();
			indexPlayer=0;
			return new Object[] {Status.StartGame};
		}
		
		Player currentPlayer=this.playerRepo.getAllActivePlayers().get(indexPlayer);
		
		while(currentPlayer.getClass().equals(Computer.class)) {
			
			if(indexPlayer==this.playerRepo.getAllActivePlayers().size()-1) {
				regenerateTurns();
				indexPlayer=0;
				return new Object[] {Status.StartGame};
			}
			currentPlayer=this.playerRepo.getAllActivePlayers().get(indexPlayer++);
		}
		Printer.INSTANCE.clearConsole();
		return new Object[] {Status.NextTurn};
		
		
	}
	/**
	 *  Method had been created for the execution of the next attack in case of Computer player
	 */
	public void executionNextTurnAttack() {
		if(++indexPlayer==this.playerRepo.getAll().size()) {
			regenerateTurns();
			indexPlayer=0;
			return;
		}
		Player currentPlayer=this.playerRepo.getAll().get(indexPlayer);
		
		while(currentPlayer.getClass().equals(Computer.class)) {
			turnComputer((Computer)currentPlayer);
			Printer.INSTANCE.printString(currentPlayer.getName()+" "+Words.COMMITED_AN_ATTACK);
			if(indexPlayer==this.playerRepo.getAll().size()-1) {
				regenerateTurns();
				indexPlayer=0;
				break;
			}
			currentPlayer=this.playerRepo.getAll().get(++indexPlayer);
		}
	}
	/**
	 *  computer's attack happens when turn arrives
	 * @param comp
	 */
	private void turnComputer(Computer comp) {
		comp.randomAttackCell();
	}
}
