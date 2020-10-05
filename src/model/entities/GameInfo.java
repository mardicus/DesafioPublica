package model.entities;

public class GameInfo {
	private Integer gameNumber;
	private Integer gameScore;
	private Integer seasonMinimum;
	private Integer seasonMaximum;
	private Integer minimumRecordBreak;
	private Integer maximumRecordBreak;
	
	public GameInfo(Integer gameNumber, Integer gameScore, Integer seasonMinimum, Integer seasonMaximum,
			Integer minimumRecordBreak, Integer maximumRecordBreak) {
		this.gameNumber = gameNumber;
		this.gameScore = gameScore;
		this.seasonMinimum = seasonMinimum;
		this.seasonMaximum = seasonMaximum;
		this.minimumRecordBreak = minimumRecordBreak;
		this.maximumRecordBreak = maximumRecordBreak;
	}

	public Integer getGameNumber() {
		return gameNumber;
	}

	public void setGameNumber(Integer gameNumber) {
		this.gameNumber = gameNumber;
	}

	public Integer getGameScore() {
		return gameScore;
	}

	public void setGameScore(Integer gameScore) {
		this.gameScore = gameScore;
	}

	public Integer getSeasonMinimum() {
		return seasonMinimum;
	}

	public void setSeasonMinimum(Integer seasonMinimum) {
		this.seasonMinimum = seasonMinimum;
	}

	public Integer getSeasonMaximum() {
		return seasonMaximum;
	}

	public void setSeasonMaximum(Integer seasonMaximum) {
		this.seasonMaximum = seasonMaximum;
	}

	public Integer getMinimumRecordBreak() {
		return minimumRecordBreak;
	}

	public void setMinimumRecordBreak(Integer minimumRecordBreak) {
		this.minimumRecordBreak = minimumRecordBreak;
	}

	public Integer getMaximumRecordBreak() {
		return maximumRecordBreak;
	}

	public void setMaximumRecordBreak(Integer maximumRecordBreak) {
		this.maximumRecordBreak = maximumRecordBreak;
	}
}
