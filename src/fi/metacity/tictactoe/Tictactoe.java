package fi.metacity.tictactoe;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a tic-tac-toe game.
 */
public class Tictactoe {
	private final char[][] mBoard;
	private final int mWinningLineLength;
	private final int mHumanPlayers;
	private final List<CpuPlayer> mCpuPlayers;
	
	private int freeSquares;
	
	public Tictactoe(int boardSize, int winningLineLength, int humanPlayers, int cpuPlayers) {
		
		// Initialize game properties
		mBoard = new char[boardSize][boardSize];
		mWinningLineLength = winningLineLength;
		mHumanPlayers = humanPlayers;
		
		// Initialize CPU players
		mCpuPlayers = new ArrayList<CpuPlayer>(cpuPlayers);
		for (int i = 0; i < cpuPlayers; ++i) {
			char mark = (char)(Math.random()*26 + 'A');  // Random A-Z
			mCpuPlayers.add(CpuPlayer.getRandomInstance(mBoard, mark));
		}
	}
	
	public void addCpuPlayer() {
		
	}

}
