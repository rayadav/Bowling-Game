/*
 * Displays the Score Board.
 * Rahul Yadav
 * rahulyadav@cmu.edu
 */

import java.util.Collections;
import java.util.List;

public class ScoreBoard {
	public void printScores(List<PlayerDetails> playerList) {

		// Sorts the list according to players' scores,
		// the top scorer being the first
		Collections.sort(playerList, PlayerDetails.compareScore);

		// Displays the final score board
		System.out.println("\n***** Final Scores! *****\n");
		for (PlayerDetails player : playerList) {
			System.out.println(player.getName() + "'s total score is: "
					+ player.getScore());
			System.out.println("|_1_|_2_|_3_|_4_|_5_|_6_|_7_|_8_|_9_|__F__|");
			System.out.print("|");

			// Prints the first 9 frames
			for (int i = 0; i < 9; i++) {
				if (player.getFirstRoll(i) == 10) {
					System.out.print("X|");
				} else
					System.out.print(player.getFirstRoll(i) + "|");
				if (player.getSecondRoll(i) == 0) {
					System.out.print(" |");
				} else if ((player.getFirstRoll(i) + player.getSecondRoll(i)) == 10) {
					System.out.print("/|");
				} else {
					System.out.print(player.getSecondRoll(i) + "|");
				}
			}

			// Prints the tenth frame
			if (player.getFirstRoll(9) == 10) {
				System.out.print("X|");
			} else
				System.out.print(player.getFirstRoll(9) + "|");
			if (player.getSecondRoll(9) == 10) {
				System.out.print("X|");
			} else if ((player.getFirstRoll(9) + player.getSecondRoll(9)) == 10) {
				System.out.print("/|");
			} else {
				System.out.print(player.getSecondRoll(9) + "|");
			}

			if ((player.getFirstRoll(9) == 10)
					| (player.getFirstRoll(9) + player.getSecondRoll(9) == 10)) {
				if (player.getFirstRoll(9) == 10) {
					System.out.println("X|");
				} else {
					System.out.println(player.getBonusRoll() + "|");
				}
			} else {
				System.out.println(" |");
			}
			System.out.println("|___|___|___|___|___|___|___|___|___|_____|");
			System.out.println("\n");
		}

		// Print out the winner's name
		System.out.println(playerList.get(0).getName() + " wins!");
	}
}