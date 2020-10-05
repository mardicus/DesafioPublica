package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.entities.GameInfo;
import model.services.GameService;

class TestCase {
	
	public TestCase() throws FileNotFoundException, IOException {
	}
	
	private GameService gameService = new GameService();

	List<GameInfo> gamesList() throws FileNotFoundException, IOException {
		List<GameInfo> gameList = new ArrayList<>();
		gameService.addScore(12);
		gameService.addGame(gameList);
		gameService.addScore(24);
		gameService.addGame(gameList);
		gameService.addScore(10);
		gameService.addGame(gameList);
		gameService.addScore(24);
		gameService.addGame(gameList);
		return gameList;
	}
	
	@Test
	void checksAndUpdatesMaximumRecordBreakCorrectly() throws FileNotFoundException, IOException {
		List<GameInfo> gamesList = this.gamesList();
		gameService.addScore(30);
		gameService.addGame(gamesList);
		boolean test = false;
		if(gamesList.size()>=2) {
			if(gamesList.get(gamesList.size()-1).getMaximumRecordBreak()>
			gamesList.get(gamesList.size()-2).getMaximumRecordBreak()) {
					test = true;
			}
		}
		System.out.print(test);
		assertTrue(test);
	}
	
	@Test
	void checksAndUpdatesMinimumRecordBreakCorrectly() throws FileNotFoundException, IOException {
		List<GameInfo> gamesList = this.gamesList();
		gameService.addScore(5);
		gameService.addGame(gamesList);
		boolean test = false;
		if(gamesList.size()>=2) {
			if(gamesList.get(gamesList.size()-1).getMinimumRecordBreak()>
			gamesList.get(gamesList.size()-2).getMinimumRecordBreak()) {
					test = true;
			}
		}
		System.out.print(test);
		assertTrue(test);
	}
	
	@Test
	void checksAndUpdatesMaximumSeasonCorrectly() throws FileNotFoundException, IOException {
		List<GameInfo> gamesList = this.gamesList();
		gameService.addScore(30);
		gameService.addGame(gamesList);
		assertEquals(30, gamesList.get(gamesList.size() - 1).getSeasonMaximum());
	}
	
	@Test
	void checksAndUpdatesMinimumSeasonCorrectly() throws FileNotFoundException, IOException {
		List<GameInfo> gamesList = this.gamesList();
		gameService.addScore(5);
		gameService.addGame(gamesList);
		assertEquals(5, gamesList.get(gamesList.size() - 1).getSeasonMinimum());
	}
}
