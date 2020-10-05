package model.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import gui.MainViewController;
import model.entities.GameInfo;
import repository.FileRepository;

public class GameService {
	public GameService() throws FileNotFoundException, IOException {
		super();
	}

	private String fileLine;
	private List<GameInfo> gamesList = new ArrayList<>();
	private List<Integer[]> fileData = new ArrayList<>();
	private int gameScore = 0;
	private int minimumRecordBreak = 0;
	private int maximumRecordBreak = 0;
	private int gameNumber;

	public void buffering(FileRepository repos) throws IOException {
		fileData.clear();
		BufferedReader br = new BufferedReader(new FileReader(repos.getSourceFile()));
		fileLine = br.readLine();
		while (fileLine != null) {
			Integer[] data = Stream.of(fileLine.split(",")).map(s -> Integer.parseInt(s)).toArray(Integer[]::new);
			fileData.add(data);
			fileLine = br.readLine();
		}
	}

	public void addScore(int Score) {
		gameScore = Score;
	}

	public void addFileGame(List<GameInfo> gamesList) throws FileNotFoundException, IOException {
		this.gamesList.clear();
		this.gamesList = gamesList;
		for (Integer[] data : fileData) {
			Integer fileGameNumber = data[0];
			Integer fileGameScore = data[1];
			Integer fileSeasonMinimum = data[2];
			Integer fileSeasonMaximum = data[3];
			Integer fileMinimumRecordBreak = data[4];
			Integer fileMaximumRecordBreak = data[5];
			gamesList.add(new GameInfo(fileGameNumber, fileGameScore, fileSeasonMinimum, fileSeasonMaximum,
					fileMinimumRecordBreak, fileMaximumRecordBreak));
			MainViewController.saveFilesGamesList.add(new GameInfo(fileGameNumber, fileGameScore, fileSeasonMinimum,
					fileSeasonMaximum, fileMinimumRecordBreak, fileMaximumRecordBreak));
		}
	}

	public GameInfo addGame(List<GameInfo> gamesList) throws FileNotFoundException, IOException {
		this.gamesList = gamesList;
		gameNumber = this.updateGameNumber();
		int seasonMinimum = this.calculateSeasonMinimum();
		int seasonMaximum = this.calculateSeasonMaximum();
		int minimumRecordBreak = this.calculateMinimumRecordBreak();
		int maximumRecordBreak = this.calculateMaximumRecordBreak();
		gamesList.add(new GameInfo(gameNumber, gameScore, seasonMinimum, seasonMaximum, minimumRecordBreak,
				maximumRecordBreak));
		MainViewController.saveFilesGamesList.add(new GameInfo(gameNumber, gameScore, seasonMinimum, seasonMaximum,
				minimumRecordBreak, maximumRecordBreak));
		return new GameInfo(gameNumber, gameScore, seasonMinimum, seasonMaximum, minimumRecordBreak,
				maximumRecordBreak);
	}

	public List<GameInfo> getList() {
		return gamesList;
	}

	private int updateGameNumber() {
		return gameNumber += 1;
	}

	private int calculateSeasonMinimum() {
		if (gamesList.size() - 1 >= 0) {
			int actualSeasonMinimum = gamesList.get(gamesList.size() - 1).getSeasonMinimum();
			if (gameScore > actualSeasonMinimum) {
				return actualSeasonMinimum;
			} else {
				return gameScore;
			}
		}
		return gameScore;
	}

	private int calculateSeasonMaximum() {
		if (gamesList.size() - 1 >= 0) {
			int actualSeasonMaximum = gamesList.get(gamesList.size() - 1).getSeasonMaximum();
			if (gameScore > actualSeasonMaximum) {
				return gameScore;
			} else {
				return actualSeasonMaximum;
			}
		}
		return gameScore;
	}

	private int calculateMinimumRecordBreak() {
		if (gamesList.size() - 1 >= 0) {
			int pastSeasonMinimum = gamesList.get(gamesList.size() - 1).getSeasonMinimum();
			int actualSeasonMinimum = this.calculateSeasonMinimum();
			if (actualSeasonMinimum != pastSeasonMinimum) {
				return minimumRecordBreak += 1;
			} else {
				return minimumRecordBreak;
			}
		}
		return 0;
	}

	private int calculateMaximumRecordBreak() {
		if (gamesList.size() - 1 >= 0) {
			int pastSeasonMaximum = gamesList.get(gamesList.size() - 1).getSeasonMaximum();
			int actualSeasonMaximum = this.calculateSeasonMaximum();
			if (actualSeasonMaximum != pastSeasonMaximum) {
				return maximumRecordBreak += 1;
			} else {
				return maximumRecordBreak;
			}
		}
		return 0;
	}
}
