package com.battlefield.ships;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.battlefield.settings.Words;

/**
 * @author dubkov
 *
 */
public class ShipStorage {
	private Map<String,ArrayList<Ship>> shipMap;
	
	/**
	 * constructor of storage
	 */
	public ShipStorage() {
		
		this.shipMap = new HashMap<>();
		
		this.shipMap.put(Words.BATTLESHIP, new ArrayList<>());
		this.shipMap.put(Words.DESTROYER, new ArrayList<>());
		this.shipMap.put(Words.PATROL_BOAT, new ArrayList<>());
		this.shipMap.put(Words.CARRIER, new ArrayList<>());
		this.shipMap.put(Words.SUBMARINE, new ArrayList<>());
	}
	
	/**
	 * @return HashMap with all ships relative to Player
	 */
	public Map<String,ArrayList<Ship>> getShipMap(){
		return this.shipMap;
	}
	
}
