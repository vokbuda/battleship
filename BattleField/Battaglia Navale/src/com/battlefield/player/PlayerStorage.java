package com.battlefield.player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dubkov
 *
 */
public class PlayerStorage {
	private List<Player> playerList;
	private static PlayerStorage instance=new PlayerStorage();
	private PlayerStorage() {
		
		this.playerList = new ArrayList<>();
	}
	/**
	 * @return returns singleton of PlayerStorage
	 */
	public static PlayerStorage getInstance() {
		return instance;
	}
	/**
	 * @return Returns all players added in list
	 */
	public List<Player> getPlayerList(){
		return playerList;
	}
	/**
	 *  setter for playerStorage
	 * @param playerList sets PlayerRepository with list of Players
	 */
	public PlayerStorage(List<Player> playerList) {
		this.playerList = playerList;
	}
	

}
