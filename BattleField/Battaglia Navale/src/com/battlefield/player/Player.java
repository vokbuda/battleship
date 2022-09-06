package com.battlefield.player;

import com.battlefield.boards.BoardForAttack;
import com.battlefield.boards.BoardForDefence;
import com.battlefield.boards.BoardHelper;
import com.battlefield.boards.Cell;
import com.battlefield.settings.Status;
import com.battlefield.settings.Words;
import com.battlefield.ships.Ship;
import com.battlefield.ships.ShipRepository;

/**
 * @author dubkov
 *
 */
public abstract class Player {
	private String name;
	BoardForAttack boardForAttack;
	BoardForDefence boardForDefence;
	private boolean lose=false;
	ShipRepository shipRepo;
	PlayerRepository playerRepo;
	protected PlayerRepository getPlayerRepo() {
		return this.playerRepo;
	}
	/**
	 * @return Returns ShipRepository associated with current Player
	 */
	public ShipRepository getShipRepo() {
		return shipRepo;
	}
	
	/**
	 * Removes Player from game when it's needed
	 */
	public void setLose() {
		this.lose=true;
	}
	/**
	 * @return true if this player lost
	 */
	public boolean getLoseBool() {
		return this.lose;
	}

	
	/**
	 * 
	 * @param cell with this parameter it is possible to attack all defence boards
	 * @return Object with status
	 */
	public Object[] attack(Cell cell) {
		return this.boardForAttack.attack(cell, playerRepo, this);
	}
	
	
	/**
	 * @return board for attack
	 */
	public BoardForAttack getBoardForAttack() {
		return boardForAttack;
	}
	/**
	 * @return board for defence
	 */
	public BoardForDefence getBoardForDefence() {
		return boardForDefence;
	}
	/**
	 *  Adds ship by type to concrete user
	 * @param shipToAdd adds ship to shipRepo, updates Board for defence
	 * @return Status- OK or IsNotPossibleAddShip
	 */
	public Object[] addShipByType(Ship shipToAdd) {
		if(this.shipRepo.checkQuantityIfAdd(shipToAdd)) {
			BoardHelper.INSTANCE.addShipToBoardForDefence(shipToAdd, boardForDefence);
			this.shipRepo.add(shipToAdd);
			return new Object[]{Status.OK,Words.TOTAL_QUANTITY_SHIPS-this.shipRepo.getQuantityOfAllShip()};
		}
		
		return new Object[]{Status.IsNotPossibleAddShip};
	}
	
	/**
	 * @param name username-player's identificator
	 * @param playerRepo which player belongs to
	 */
	public Player(String name,PlayerRepository playerRepo)
	{
		if(name==null||playerRepo==null) {
			throw new RuntimeException(Status.NullObject.getResult());
		}
		this.shipRepo=new ShipRepository();
		this.name = name;
		this.boardForAttack=new BoardForAttack(this);
		this.boardForDefence=new BoardForDefence(this);
		this.playerRepo=playerRepo;
	}
	/**
	 * @return quantity of ships in Ships' Repository
	 */
	public int getQuantityShips() {
		return this.shipRepo.getQuantityOfAllShip();
	}
	/**
	 * @return username of player
	 */
	public String getName() 
	{
		return this.name;
	}
	
	
	
	
	
	/**
	 * @return Returns boolean primitive which shows if player is ready to play
	 */
	public boolean isReadyForSingleBattle() {
		return this.shipRepo.checkIsReadyToPlay();
	}
	
	/**
	 * @return Status.OK when all ships are removed from ShipRepository and defence board
	 */
	public Status removeAllShipsFromDefenceTable() {
		this.boardForDefence.removeAllShips();
		this.shipRepo.removeAll();
		return Status.OK;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	/**
	 * Overrides equals method
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	/**
	 *  Adds ship to shipRepository
	 * @param toStart initial cell of ship
	 * @param toFinish last cell of ship
	 * @return Object with Status inside
	 */
	public Object[] addShip(Cell toStart,Cell toFinish) {
		Object[] result=this.boardForDefence.generateShipOnTable(toStart,toFinish,shipRepo);
		
		if(!result[0].equals(Status.OK)) 
			return new Object[] { result[0],null};
		Ship shipToAddInRepo=(Ship) result[1];
		this.shipRepo.add(shipToAddInRepo);
		
		return new Object[] {Status.OK,shipToAddInRepo,Words.TOTAL_QUANTITY_SHIPS-this.shipRepo.getQuantityOfAllShip()};
	}
}
