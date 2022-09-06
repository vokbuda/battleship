package com.battlefield.settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dubkov
 *
 */
public class Words {
	/**
	 * default strings Setup
	 */
	public final static int limitShipsToLose=9;
	public final static String CellIsOutOfRange="Cell is out of range";
	public final static String NullObject="Object is null";
	public final static String SHIP_REPO_IS_FULL="Ships' repository is full";
	public final static String OK="Operation had been executed with success";
	public final static String IsNotPossibleAddShipOfThisType="It is not possible to add ship of this type";
	public final static String IsNotPossibleAddShip="It's not possible to add ship";
	public final static String IsNotPossibleModifyShip="It's not possible to update this ship";
	public final static String isNoAppropriateShip="There is no appropriate ship";
	public final static String AttemptIsNotSuccessful="Attempt is not successful";
	public final static String ProblemCell="Cell/-s is/-are not valid";
	public final static String NumberIsNotValid="Number is not valid";
	public final static String IsNoShip="There is no ship";
	public final static String UserEliminated="user had been eliminated";
	public final static String NobodyEliminated="nobody had been eliminated";
	public final static String ComputerEliminated="computer had been eliminated";
	public final static String ComputerNotFound="computer is not found";
	public final static String UserAlreadyExists="user already exists";
	public final static String StartGame="Start Game";
	public final static String NextTurn="Next Turn";
	public final static String UserLimit="It is not possible to add more than 2 players";
	public final static String ComputerLimit="It is not possible to have more than one computer in current version";
	public final static String UserIsNotFound="User is not found";
	public final static String COMPUTER="COMPUTER";
	public final static String SHIP="Ship";
	public final static String DESTROYED="Destroyed";
	public final static String SHOT="Shot";
	public final static String REMOVE_SHIP_CHOOSING_ONE_CELL="Remove ship choosing cell";
	public final static String POST_BATTLE_MENU="Post Battle Menu";
	public final static String START_GAME="Start Game";
	public final static String SHIP_BEFORE="Ship before ";
	public final static String SHIP_AFTER="Ship after ";
	public final static String START_DELETION_COMPUTER="If there is any computer it will be cancelled";
	public final static String ADD_SHIP_INSERTING_TWO_CELLS="Add ship inserting two cells";
	public final static String ADD_SHIP_SELECTING_TYPE="Add ship selecting type";
	public final static String INSERT_USERNAME="Insert username";
	public static final String SHOW_BOARD_FOR_ATTACK="Show board for attack";
	public static final String REMOVE_SHIP="Remove Ship";
	public static final String REMOVE_SHIP_BY_CELL="Remove ship by cell";
	public static final String REMOVE_ALL_SHIPS_FROM_DEFENCE_BOARD="Remove all ships from defence board";
	public static final String SHOW_DEFENCE_BOARD="Show defence board";
	public static final String ADD_BATTLESHIP="Add battleship";
	public static final String ADD_DESTROYER ="Add destroyer";
	public static final String ADD_PATROL_BOAT="Add patrol boat";
	public static final String ADD_SUBMARINE="Add submarine";
	public static final String ADD_CARRIER="Add carrier";
	public static final String DOWNGRADE_SHIP="Downgrade Ship";
	public static final String CHOOSE_CELL_TO_ATTACK="Choose cell to attack";
	public static final String CREATE_USER="Create user";
	public static final String MAIN_MENU="Main Menu";
	public static final String NEXT_TURN_OR_START_BATTLE="Next turn/Start Battle";
	public static final String UPGRADE_SHIP="Upgrade Ship";
	public static final String SHOW_AVAILABLE_SHIP="Show available ships";
	public final static int MAX_QUANTITY_CARRIER=1;
	public final static int MAX_QUANTITY_SUBMARINE=3;
	public final static int MAX_QUANTITY_DESTROYER=2;
	public final static int MAX_QUANTITY_BATTLESHIP=1;
	public final static int MAX_QUANTITY_PATROL_BOAT=3;
	public final static int TOTAL_QUANTITY_SHIPS=MAX_QUANTITY_CARRIER+MAX_QUANTITY_SUBMARINE+MAX_QUANTITY_DESTROYER+MAX_QUANTITY_BATTLESHIP+MAX_QUANTITY_PATROL_BOAT;
	public final static int MAX_SPACE_CARRIER=5;
	public final static int MAX_SPACE_BATTLESHIP=4;
	public final static int MAX_SPACE_DESTROYER_AND_SUBMARINE=3;
	public final static int MAX_SPACE_SUBMARINE=3;
	public final static int MAX_SPACE_PATROL_BOAT=2;
	public final static String PATROL_BOAT="Patrol boat";//Nave Assalto=Patrol boat
	public final static String CARRIER="Carrier";//Porta Aereo=Carrier
	public final static String DESTROYER="Destroyer";//Crociera=Destroyer
	public final static String BATTLESHIP="Battleship";//Corazzata=Battleship
	public final static String SUBMARINE="Submarine";//Sottomarino=Submarine
	public final static String NEED_TO_CREATE_USER="YOU NEED TO CREATE USER";
	public final static String WAS_ADDED_TO_DEFENCE_BOARD="was added to defence board";
	public final static String ADD_ALL_SHIPS="Add all available ships to a board";
	public final static String AVAILABLE_SHIPS_ARE="\nAVAILABLE SHIPS ARE\n";
	public final static int MIN_QUANTITY_SHIPS=0;
	public final static String COMMITED_AN_ATTACK="commited an attack";
	public final static String DEFENCE_BOARD="Defence board";
	public final static String ATTACK_BOARD="Attack board";
	public final static String WON="won";
	public final static String ELIMINATED="lost";
	public final static String TURN_LABEL="'s turn";
	public final static String ADDED_TO_PLAYER_REPO="had been created";
	public final static String REMOVED_FROM_PLAYER_REPO="had been removed";
	public final static String REMOVE_USER="Remove user";
	public final static String CREATE_HUMAN_PLAYER="Add user";
	public final static String REMOVE_COMPUTER_PLAYER="Remove computer";
	public final static String CREATE_COMPUTER_PLAYER="Create computer";
	public final static String MANAGE_USERS="Manage users";
	public final static String SHOW_ALL_PARTICIPANTS="Show all participants";
	public final static String TOTAL_QUANTITY_PARTICIPANTS="Total quantity: ";
	public static final String InputCell="Input cell in following format:'Letter Number'";
	public static final String InputNumber="Input number";
	public static final String InputFirstCell="Input the first cell in following format:'Letter Number'";
	public static final String InputSecondCell="Input the second cell in following format:'Letter Number'";
	public final static List<Character> listOfLetters = Collections.unmodifiableList(Arrays.asList('A','B','C','D','E','F','G','H','I','J'));
	
	public final static List<Integer> listOfInts=Collections.unmodifiableList(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
	
	public static Map<String, ArrayList<Integer>> shipsWithSizes=new LinkedHashMap<>() {{
		put(CARRIER, new ArrayList<>() {{
			add(MAX_SPACE_CARRIER);
			add(MAX_QUANTITY_CARRIER);
		}});
		put(BATTLESHIP, new ArrayList<>() {{
			add(MAX_SPACE_BATTLESHIP);
			add(MAX_QUANTITY_BATTLESHIP);
		}});
		put(DESTROYER, new ArrayList<>() {{
			add(MAX_SPACE_DESTROYER_AND_SUBMARINE);
			add(MAX_QUANTITY_DESTROYER);
		}});
		
		put(SUBMARINE, new ArrayList<>() {{
			add(MAX_SPACE_SUBMARINE);
			add(MAX_QUANTITY_SUBMARINE);
		}});
		put(PATROL_BOAT, new ArrayList<>() {{
			add(MAX_SPACE_PATROL_BOAT);
			add(MAX_QUANTITY_PATROL_BOAT);
		}});
		
	}};
	public final static int[] orderedShips= {};
	public final static List<String> typesShip=new ArrayList<>() {{
		add(BATTLESHIP);
		add(DESTROYER);
		add(PATROL_BOAT);
		add(SUBMARINE);
		add(CARRIER);
	}};
	public static int detectLimit(String typeShip) {
		
		if(typeShip.equals(PATROL_BOAT)) {
			return MAX_QUANTITY_PATROL_BOAT;
		}
		if(typeShip.equals(SUBMARINE)) {
			
			return MAX_QUANTITY_SUBMARINE;
		}
		if(typeShip.equals(BATTLESHIP)) {
			
			return MAX_QUANTITY_BATTLESHIP;
		}
		if(typeShip.equals(DESTROYER)) {
			
			return MAX_QUANTITY_DESTROYER;
		}
		if(typeShip.equals(CARRIER)) {
			return MAX_QUANTITY_CARRIER;
		}
		return -1;
	}
	public static List getIntsTableList() {
		return listOfInts;
	}
	public final static Map<String,Character> ownerToLiteral=new HashMap<String,Character>(){{
		
		put(null,' ');
		put(DESTROYED,'-');
		put(SHOT,'*');
		put(SHIP,'+');
	}};
	public final static Map<Integer,ArrayList<String>> mapSizeToString=new HashMap<Integer,ArrayList<String>>(){{
		put(MAX_SPACE_DESTROYER_AND_SUBMARINE,new ArrayList<String>() {{
			add(SUBMARINE);
			add(DESTROYER);
		}});
		put(MAX_SPACE_PATROL_BOAT,new ArrayList<String>() {{
			add(PATROL_BOAT);
		}});
		put(MAX_SPACE_CARRIER,new ArrayList<String>() {{
			add(CARRIER);
		}});
		put(MAX_SPACE_BATTLESHIP,new ArrayList<String>() {{
			add(BATTLESHIP);
		}});
	}};
	
}
