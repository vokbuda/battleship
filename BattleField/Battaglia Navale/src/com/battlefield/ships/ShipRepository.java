package com.battlefield.ships;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.battlefield.settings.Status;
import com.battlefield.settings.Words;

/**
 * @author dubkov
 *
 */
public class ShipRepository implements RepositoryShips<Ship>{
	private Map<String,ArrayList<Ship>> storageShips;
	
	
	/**
	 * @return returns StorageShips
	 */
	public Map returnStorageShips() {
		return this.storageShips;
	}
	/**
	 * Constructor
	 */
	public ShipRepository() {
		this.storageShips = new ShipStorage().getShipMap();
	}

	
	

	/**
	 *  Adds to shipRepo
	 * @return Returns Object with Status with shipToAdd
	 */
	@Override
	public Object[] add(Ship shipToAdd) {
			this.storageShips.get(shipToAdd.getTypeShip()).add(shipToAdd);
			return new Object[]{Status.OK,shipToAdd};
	}

	/**
	 *  Updates ship in ShipRepo
	 * @return Returns Status
	 */
	@Override
	public Status update(Ship oldT, Ship newT) {
		this.storageShips.get(oldT.getTypeShip()).remove(oldT);
		this.storageShips.get(newT.getTypeShip()).add(newT);
		return Status.OK;
	}

	/**
	 * @return returns HashMap with all ships inside
	 */
	@Override
	public Map getAll() {
		return this.storageShips;
	}
	/**
	 *  Removes ship from storage
	 * @return Status
	 */
	@Override
	public Status removeShip(Ship ship) {
		this.storageShips.get(ship.getTypeShip()).remove(ship);
		return Status.OK;
	}
	/**
	 * @return returns true when it is possible add ship to ShipRepository
	 */
	@Override
	public boolean checkQuantityIfAdd(Ship ship) {
		String typeShip=ship.getTypeShip();
		if(this.storageShips.get(typeShip).size()+1<=Words.detectLimit(typeShip))
			return true;
		return false;
	}

	/**
	 * @param ship ship to check in case of deletion from ShipRepository
	 * @return returns true when it is possible to remove ship from ShipRepository
	 */
	@Override
	public boolean checkQuantityIfRemove(Ship ship) {
		String typeShip=ship.getTypeShip();
		if(this.storageShips.get(typeShip).size()-1<Words.MIN_QUANTITY_SHIPS) {
			return false;
		}
		return true;
	}
	/**
	 *  returns quantity of ships belonging to player
	 */
	@Override
	public int getQuantityOfAllShip() {
		int quantityResult=0;
		for (String key : storageShips.keySet()) {
			quantityResult+=storageShips.get(key).size();
		}
		return quantityResult;
	}
	/**
	 *  Removes all ships from ShipRepository
	 */
	@Override
	public void removeAll() {
		this.storageShips.replaceAll((key, oldValue)
                -> new ArrayList<>());
		
	}
	/**
	 * @return StringBuilder with available ships
	 */
	@Override
	public StringBuilder getAvailableShips() {
		StringBuilder returnAvailableShips=new StringBuilder();
		List<String> storeList=this.storageShips.keySet().stream().collect(Collectors.toList());
		Map<String,ArrayList<Integer>> mapShips=Words.shipsWithSizes;
		returnAvailableShips.append(Words.AVAILABLE_SHIPS_ARE);
		for (String keyMapTo : storeList) {
			returnAvailableShips.append(mapShips.get(keyMapTo).get(1)-this.storageShips.get(keyMapTo).size());
			returnAvailableShips.append(" "+keyMapTo);
			returnAvailableShips.append(" SIZE:"+mapShips.get(keyMapTo).get(0));
			returnAvailableShips.append("\n");
		}
		returnAvailableShips.append("----");
		return returnAvailableShips;
	}
	/**
	 * @return true if player is ready to play(allocates all ships)
	 */
	@Override
	public boolean checkIsReadyToPlay() {
		Map<String,ArrayList<Integer>> mapShips=Words.shipsWithSizes;
		List<String> storeList=this.storageShips.keySet().stream().collect(Collectors.toList());
		for (String typeShip : storeList) {
			if(mapShips.get(typeShip).get(1)!=this.storageShips.get(typeShip).size()) {
				return false;
			}
		}
		return true;
	}
}
