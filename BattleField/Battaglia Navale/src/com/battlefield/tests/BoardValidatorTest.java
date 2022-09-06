package com.battlefield.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.battlefield.boards.BoardValidator;
import com.battlefield.boards.Cell;
import com.battlefield.player.Human;
import com.battlefield.player.PlayerRepository;
import com.battlefield.settings.Status;

/**
 * @author dubkov
 *
 */
class BoardValidatorTest {
	PlayerRepository playerRepo;
	/**
	 *  Initial setup
	 */
	@BeforeEach
	void playerRepoInit() {
		playerRepo=new PlayerRepository();
		playerRepo.removeAll();
	}
	/**
	 *  checks if cells are on the same line
	 */
	@Test
	@DisplayName("BoardValidatorTest-> cells are on the same line")
	void checkFirstLastCells() {
		Cell firstCell=new Cell('A',1);
		Cell lastCell=new Cell('A',5);
		boolean result=BoardValidator.areProblemCells(firstCell.getIdNumber(), lastCell.getIdNumber(), firstCell.getLetter(), lastCell.getLetter());
		assertEquals(result, true);
	}
	/**
	 *  Tests if invalid cells are out of Range
	 * @param cellToControl
	 */
	@ParameterizedTest
	@DisplayName("BoardValidatorTest-> invalid cell control")
	@MethodSource("cellsToCheck")
	void checkInvalidCell(Cell cellToControl) {
		boolean result=BoardValidator.isCellNotOutOfRange(cellToControl);
		Assertions.assertEquals(result, false);
		
	}
	/**
	 * @return List with generated cells(not valid)
	 */
	private static List cellsToCheck() {
		return Arrays.asList(new Cell('A',100),new Cell('K',2),new Cell('T',200));
	}
	/**
	 * @param there is no any parameter
	 *  test method checks if it is some available cell between two ships 
	 */
	@Test
	@DisplayName("BoardValidator->test cell touches->cell between two vertical ships(cell between)")
	void testVerticalCellTouches() {
		Human human=new Human("Dubkov",playerRepo);
		playerRepo.addHuman(human);
		human.addShip(new Cell('A',10), new Cell('A',6));
		human.addShip(new Cell('A',4), new Cell('A',2));
		Status result=(Status) BoardValidator.checkShipNear(new Cell('A',5),human.getBoardForDefence().getBoardMap())[0];
		Assertions.assertEquals(result, Status.ProblemCell);
	}
	/**
	 *  Tests if two vertical ships have diagonal touch between them
	 * @param there is no parameters
	 * 
	 */
	@Test
	@DisplayName("BoardValidator->test cell touches->cell between two vertical ships(corner touches two ships)")
	void testVerticalCornerTouchesCell() {
		Human human=new Human("Dubkov",playerRepo);
		playerRepo.addHuman(human);
		human.addShip(new Cell('A',10), new Cell('A',6));
		human.addShip(new Cell('C',4), new Cell('C',2));
		Status result=(Status) BoardValidator.checkShipNear(new Cell('B',5),human.getBoardForDefence().getBoardMap())[0];
		Assertions.assertEquals(result, Status.ProblemCell);
	}
	/**
	 *  Tests if 2 ships located horizontally touches each other
	 * @param there is no parameters
	 * 
	 */
	@Test
	@DisplayName("BoardValidator->test cell touches->cell between two horizontal ships")
	void testHorizontalCornerTouchesCell() {
		Human human=new Human("Dubkov",playerRepo);
		playerRepo.addHuman(human);
		human.addShip(new Cell('A',10), new Cell('C',10));
		human.addShip(new Cell('E',8), new Cell('G',8));
		Status result=(Status) BoardValidator.checkShipNear(new Cell('B',8),human.getBoardForDefence().getBoardMap())[0];
		Assertions.assertEquals(result, Status.ProblemCell);
	}
	
	
	
	

}
