package com.battlefield.player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.battlefield.settings.Status;
import com.battlefield.settings.Words;

/**
 * @author dub
 *
 */
public class PlayerRepository implements RepositoryPlayers<Player>{
	private List<Player> storagePlayers;
	/**
	 * Setups PlayerRepository
	 */
	public PlayerRepository() {
		this.storagePlayers=PlayerStorage.getInstance().getPlayerList();
	}
	/**
	 *  Adds human to repository
	 * @return Returns object with status
	 */
	@Override
	public Object[] addHuman(Human humanUser) {
		if(this.getSize()==2) {
			return new Object[] {Status.UserLimit};
		}
		Object[] statusAddition=addPlayer(humanUser);
		if(statusAddition[0].equals(Status.OK)) {
			return new Object[] {Status.OK,humanUser};
		}
		
		return new Object[] {statusAddition[0]};
		
		
	}
	

	/**
	 *@return Returns all players from storage
	 */
	@Override
	public List<Player> getAll() {
		List<Player> playersList = new ArrayList<>(this.storagePlayers);
		return playersList;
	}

	
	
	/**
	 *  newP will substitute oldP
	 * @param oldP, newP
	 *  Updates player with another one
	 */
	@Override
	public Object[] update(Player oldP, Player newP) {
		if(this.storagePlayers.contains(oldP)) {
			int oldPlayerIndex=storagePlayers.indexOf(oldP);
			this.storagePlayers.set(oldPlayerIndex, newP);
			return new Object[] {Status.OK};
		}else {
			return new Object[] {Status.AttemptIsNotSuccessful};
		}
		
	}
	
	
	
	/**
	 * Removes all players from storage
	 */
	@Override
	public void removeAll() {
		Computer.counterCreatedInstances=0;
		this.storagePlayers.clear();
		
	}
	/**
	 * @return Returns size player's storage
	 */
	@Override
	public int getSize() {
		return this.storagePlayers.size();
	}
	/**
	 * @return Returns winner of game
	 */
	@Override
	public Player getWinner() {
		return this.getAllActivePlayers().get(0);
	}
	
	/**
	 * Controls if some player has quantity of ships equal to limit
	 *@return Returns players who lost a game
	 */
	@Override
	public Object[] eliminationUsers() {
		Iterator<Player> itr = this.storagePlayers.iterator();
		List<Player> listOfPlayers=new ArrayList<>();
		
		while(itr.hasNext()) {
			Player player=itr.next();
			
			if(player.getQuantityShips()==Words.limitShipsToLose&&player.getLoseBool()==false) {
				
				player.setLose();
				if(player.getClass().equals(Computer.class)) {
					Computer.counterCreatedInstances--;
				}
				listOfPlayers.add(player);
			}
		}
		if(listOfPlayers.size()==0) {
			return new Object[] {Status.NobodyEliminated};
		}
		
		return new Object[] {Status.UserEliminated,listOfPlayers};	
	}
	
	/**
	 * Removes last computer from repository
	 *@return Object with Status.ComputerEliminated and computer found
	 *
	 */
	@Override
	public Object[] removeLastComputer() {
		ListIterator iteratorReverse=this.storagePlayers.listIterator(this.storagePlayers.size());
		while(iteratorReverse.hasPrevious()) {
			Player player=(Player)iteratorReverse.previous();
			if(player.getClass().equals(Computer.class)) {
				iteratorReverse.remove();
				Computer.counterCreatedInstances--;
				return new Object[] {Status.ComputerEliminated,player};
			}
		}
		return new Object[] {Status.ComputerNotFound};
	}
	/**
	 * Adds computer to repository
	 *@return Returns object with status.ok if this operation was successful
	 */
	@Override
	public Object[] addComputer() {
		if(this.getSize()==1) {
			if(this.getAll().get(0).getClass().equals(Computer.class)) {
				return new Object[] {Status.ComputerLimit};
			}
		}
		if(this.getSize()==2) {
			return new Object[] {Status.UserLimit};
		}
		Computer comp=new Computer(this);
		Object[] statusAddition= this.addPlayer(comp);
		if(statusAddition[0].equals(Status.OK)) {
			return new Object[] {Status.OK,comp};
		}else {
			return new Object[] {statusAddition[0]};
		}
		
	}
	/**
	 *	@return Object with Status inside
	 */
	@Override
	public Object[] removeUser(Player userToRemove) {
		Iterator<Player> itr = this.storagePlayers.iterator();
		while(itr.hasNext()) {
			Player player=itr.next();
			if(player.getName().equals(userToRemove.getName())) {
				itr.remove();
				return new Object[] {Status.UserEliminated,userToRemove};
			}
		}
		return new Object[] {Status.UserIsNotFound};
	}
	/**
	 *  shows current situation of playerRepository(quantity of Players, who are they etc);
	 * @return object of class StringBuilder
	 */
	@Override
	public StringBuilder showSituation() {
		StringBuilder resultStringBuilder=new StringBuilder();
		resultStringBuilder.append(Words.TOTAL_QUANTITY_PARTICIPANTS+":"+this.getSize()+"\n");
		for (Player player : storagePlayers) {
			resultStringBuilder.append(player.getName()+":"+player.getClass().getSimpleName()+"\n");
		}
		return resultStringBuilder;
	}
	/**
	 * @return List with all active players in a game
	 */
	@Override
	public List<Player> getAllActivePlayers() {
		List<Player> playersList = new ArrayList<>(this.storagePlayers);
		List<Player> resultList=new ArrayList<>();
		for (Player player : playersList) {
			if(!player.getLoseBool()) {
				resultList.add(player);
			}
		}
		return resultList;
	}
	
	/** Method adds player to repository
	 * @param p of type Player
	 * @return Object with Status success in case if player had been added in repository
	 */
	private Object[] addPlayer(Player p) {
		if(p==null) {
			return new Object[] {Status.NullObject};
		}
		for (Player player : storagePlayers) {
			if(player.equals(p)) {
				return new Object[] {Status.UserAlreadyExists};
			}
		}
		this.storagePlayers.add(p);
		return new Object[] {Status.OK};
	}
	
}
