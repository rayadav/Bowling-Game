/*
 * Handles frames and scoring according to the rules.
 * Rahul Yadav
 * rahulyadav@cmu.edu
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class BowlingRules {

	// Prints the welcome message
	public void welcomeMessage(String player) {
		System.out.println(String.format(
				"Hi! Please choose one of the following options:", player));
	}

	// Prints the menu
	public void mainMenu() {
		System.out.println("1. Add Another Player\n2. Start Game\n3. Quit");
		System.out.println("Please select an option (1, 2 or 3):");
	}

	// Prints the number of players and their names
	public void printPlayerInfo(List<PlayerDetails> playerList) {
		System.out.println("There are " + playerList.size()
				+ " players in the game.");
		System.out.println("The players are:");
		for (PlayerDetails player : playerList) {
			System.out.println(player.getName());
		}
	}

	// Scoring for bowling frames 1-9
	public void bowlFrame(PlayerDetails player, int frame) throws IOException {
		int totalScore = 0;
		int bonusCounter = player.getBonusCounter();
		System.out.println("\n" + player.getName() + "'s up!\n");

		// Bowl first round
		System.out.println("Enter your score for ball 1:");
		int score = getScore(totalScore);
		totalScore = totalScore + score;
		player.setFirstRoll(frame, score);

		// Update player score
		if (bonusCounter == 1 | bonusCounter == 2) {
			player.setScore(score + score);
			bonusCounter -= 1;
		} else if (bonusCounter == 3) {
			player.setScore(score + score + score);
			bonusCounter -= 2;
		} else {
			player.setScore(score);
		}

		// Checks for strike
		if (totalScore == 10) {
			player.setSecondRoll(frame, 0);
			System.out.println("You have bowled a strike!!");
		} else {

			// Gets score for ball 2 if there was no strike
			System.out.println("Enter your score for ball 2:");
			score = getScore(totalScore);
			player.setSecondRoll(frame, score);
			if (bonusCounter == 1) {
				player.setScore(score + score);
				bonusCounter -= 1;
			} else {
				player.setScore(score);
			}
			totalScore = totalScore + score;

			// Checks for spare
			if (totalScore == 10) {
				System.out.println("You have bowled a spare!!");
			}
		}

		// Updates bonus counter if the player gets a strike
		if ((player.getFirstRoll(frame)) == 10) {
			bonusCounter += 2;
		} else if (totalScore == 10) {
			// Updates bonus counter if the player gets a spare
			bonusCounter += 1;
		}
		player.setBonusCounter(bonusCounter);
		System.out.println(player.getName() + "'s total score is "
				+ player.getScore());
	}

	/*
	 * Scoring for the last frame. This includes a bonus ball if the player got
	 * a strike or spare. Bonus points are calculated using bonusCounter.
	 * bonusCounter is incremented by 2 for every strike and 1 for every spare.
	 * It is decremented by 1 for each bonus score added. If there are two bonus
	 * scores added (e.g. the two previous balls were strikes) then the bonus
	 * score is decremented by 2.
	 */
	public void bowlLastFrame(PlayerDetails player, int frame)
			throws IOException {
		int totalScore = 0;
		int bonusCounter = player.getBonusCounter();
		System.out.println("\n" + player.getName() + "'s up!\n");

		// Bowl first ball
		System.out.println("Enter your score for ball 1:");
		int score = getScore(totalScore);
		totalScore = totalScore + score;
		player.setFirstRoll(frame, score);

		// Update player score
		if (bonusCounter == 1 | bonusCounter == 2) {
			player.setScore(score + score);
			bonusCounter -= 1;
		}
		if (bonusCounter == 3) {
			player.setScore(score + score + score);
			bonusCounter -= 2;
		} else {
			player.setScore(score);
		}

		// Bowl second ball, when player bowled a strike on the first ball
		if (totalScore == 10) {
			System.out.println("You have bowled a strike!!");
			bonusCounter += 2;
			System.out.println("Enter your score for ball 2:");
			score = getScore();
			player.setSecondRoll(frame, score);
			if (bonusCounter == 1 | bonusCounter == 2) {
				player.setScore(score);
				bonusCounter -= 1;
			}
			if (bonusCounter == 3) {
				player.setScore(score + score);
				bonusCounter -= 2;
			}
		}

		// Bowl second ball, when player did not bowl a strike on the first ball
		else {
			System.out.println("Enter your score for ball 2:");
			score = getScore(totalScore);
			player.setSecondRoll(frame, score);
			totalScore = totalScore + score;
			if (bonusCounter == 1 | bonusCounter == 2) {
				player.setScore(score + score);
				bonusCounter -= 1;
			} else {
				player.setScore(score);
			}
		}

		// Bowl bonus ball if player bowled any strikes or a spare this frame
		if (totalScore == 10) {
			System.out.println("Strike");
			System.out
					.println("You have earned a bonus ball! Enter your score for the bonus ball:");
			score = getScore();
			player.setBonusRoll(score);
			if (score == 10) {
				System.out.println("Strike!!!");
			}
			player.setScore(score);
		}

		System.out.println(player.getName() + "'s total score is "
				+ player.getScore());

	}

	// Gets the score from the player
	public int getScore(int totalScore) throws IOException {
		boolean validScore = false;
		int tempScore = 0;
		while (validScore == false) {
			String temp = new BufferedReader(new InputStreamReader(System.in))
					.readLine();
			while (temp.equals("")) {
				System.out.println("Please enter your score:");
				temp = new BufferedReader(new InputStreamReader(System.in))
						.readLine();
			}
			tempScore = Integer.parseInt(temp);
			if ((tempScore < 0) | (tempScore + totalScore > 10)) {
				System.out
						.println("You have entered an invalid score. Please try again.");
			} else {
				validScore = true;
			}
		}
		return tempScore;
	}

	// Gets the bowling scores for the bonus round
	public int getScore() throws IOException {
		boolean validScore = false;
		int tempScore = 0;
		while (validScore == false) {
			String temp = new BufferedReader(new InputStreamReader(System.in))
					.readLine();
			if (temp == null) {
				System.out.println("Please enter your score:");
			}
			tempScore = Integer.parseInt(temp);
			if (tempScore < 0 | tempScore > 10) {
				System.out
						.println("You have entered an invalid score. Please try again.");
			} else {
				validScore = true;
			}
		}
		return tempScore;
	}
}