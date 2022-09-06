package com.battlefield.boards;

import java.util.List;

import com.battlefield.player.Player;
import com.battlefield.player.PlayerRepository;
import com.battlefield.settings.Status;
import com.battlefield.settings.Words;
import com.battlefield.ships.Ship;

/**
 * @author dubkov
 *
 */
public class BoardForAttack extends Board {
	
	/**
	 * @param player creates Board For Attack which belongs to certain Player
	 */
	public BoardForAttack(Player player) {
		super(player);	
	}
	
	/**
	 *  Method gives a possibility to attack players
	 * @param attackCell Cell to attack
	 * @param repoPlayers(repository with players to attack)
	 * @param currentPlayer(player who attacks)
	 * @return Object(with Status)
	 */
	public Object[] attack(Cell attackCell,PlayerRepository repoPlayers,Player currentPlayer) {
		if(!BoardValidator.isCellNotOutOfRange(attackCell)) {
			return new Object[] {Status.ProblemCell};
		}
		List<Player> allPlayers=repoPlayers.getAll();
		for (Player player : allPlayers) {
			if(!player.equals(currentPlayer))
				if(player.getBoardForDefence().startTable.get(attackCell)!=null&&player.getBoardForDefence().startTable.get(attackCell).getClass().getSuperclass().equals(Ship.class)) {
					currentPlayer.getBoardForAttack().startTable.put(attackCell,Words.DESTROYED);
					player.getBoardForDefence().removeCellByShot(attackCell);
					
					return new Object[] {Status.OK};
				}else {
					player.getBoardForDefence().startTable.put(attackCell,Words.SHOT);
				}
		}
		currentPlayer.getBoardForAttack().startTable.put(attackCell, Words.SHOT);
		return new Object[] {Status.AttemptIsNotSuccessful};
	}
	@Override
	public String toString() {
		return "BoardForAttack [BoardTable=" + startTable + ", playerOwner=" + playerOwner + ", getClass()="
				+ getClass() + "]";
	}
	
	
}
