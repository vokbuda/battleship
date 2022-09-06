package com.battlefield.game;

import java.util.List;

import com.battlefield.menu.DuringBattleMenu;
import com.battlefield.player.Player;
import com.battlefield.player.PlayerRepository;
import com.battlefield.printer.Printer;
import com.battlefield.settings.Status;

/**
 * @author dubkov
 * Stops game when game is finished
 *
 */
public class ThreadStopGame implements Runnable {
	/**
	 * needs for control if game goes on
	 */
	public static volatile boolean continues;
	private PlayerRepository playerRepo;
	
	/**
	 * @param playerRepo here there will be happened different controls if game must go on
	 */
	public ThreadStopGame( PlayerRepository playerRepo) {
		this.playerRepo=playerRepo;
		continues=true;
	}
	
	/**
	 * eliminates users when ships limit is reached
	 */
	@Override
	public synchronized void run() {
		while(continues) {

			Object[] statusEliminationUsers=playerRepo.eliminationUsers();
			if(statusEliminationUsers[0]==Status.UserEliminated) {
				Printer.INSTANCE.eliminatedUsers((List<Player>)statusEliminationUsers[1]);
			}
			if(playerRepo.getAllActivePlayers().size()==1) {
				
				DuringBattleMenu.gameContinues=false;
				this.continues=false;
				Printer.INSTANCE.winner(playerRepo.getWinner().getName());
				this.playerRepo.removeAll();
			}
			
		}
		
	}
	
	

}
