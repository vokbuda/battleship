package com.battlefield.game;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.battlefield.player.PlayerRepository;
import com.battlefield.player.Turn;

/**
 * @author dubkov
 *
 */
public enum Game {
	/**
	 * Permits manipulations with methods inside
	 */
	INSTANCE;
	/**
	 * boolean variable checks if game must go on
	 */
	public static boolean gameContinues=true;
	/**
	 * playerRepo where all players had been saved
	 */
	public static PlayerRepository playerRepo=new PlayerRepository();
	/**
	 *  object represents current Turn
	 */
	public static Turn turngame;
	/**
	 *  executor created for thread management
	 */
	public static ExecutorService executor=Executors.newFixedThreadPool(5);
	
}
