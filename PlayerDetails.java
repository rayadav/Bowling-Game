/*
 * Keeps details of the player like name, score and bonus counter.
 * Rahul Yadav
 * rahulyadav@cmu.edu
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PlayerDetails {
	private String name;
	private List<Integer> firstRoll = new ArrayList<Integer>();
	private List<Integer> secondRoll = new ArrayList<Integer>();
	private int bonusRoll;
	private int score;
	private int bonusCounter;

	// Sets player name
	public PlayerDetails(String name) {
		this.name = name;
	}

	// Gets player name
	public String getName() {
		return name;
	}

	// Sets the score from first roll of a frame
	public void setFirstRoll(int frame, int score) throws IOException {
		firstRoll.add(score);
	}

	// Gets the first ball of a frame to roll
	public int getFirstRoll(int frame) {
		return firstRoll.get(frame);
	}

	// Sets the score from the second roll of a frame
	public void setSecondRoll(int frame, int score) throws IOException {
		secondRoll.add(score);
	}

	// Gets the second ball of a frame to roll
	public int getSecondRoll(int frame) {
		return secondRoll.get(frame);
	}

	// Sets the score from bonus roll for the last frame
	public void setBonusRoll(int score) {
		bonusRoll = score;
	}

	// Gets the ball for a bonus roll in the last frame
	public int getBonusRoll() {
		return bonusRoll;
	}

	// Updates the player's total score
	public void setScore(int score) {
		this.score = this.score + score;
	}

	// Gets the player's total score
	public int getScore() {
		return score;
	}

	// Sets the player's bonus counter
	public void setBonusCounter(int bonusCtr) {
		bonusCounter = bonusCtr;
	}

	// Gets the player's bonus counter
	public int getBonusCounter() {
		return bonusCounter;
	}

	// Comparator to compare players' scores
	public static Comparator<PlayerDetails> compareScore = new Comparator<PlayerDetails>() {

		@Override
		public int compare(PlayerDetails p1, PlayerDetails p2) {
			return p2.score - p1.score;
		}
	};
}