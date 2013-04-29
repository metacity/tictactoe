package fi.metacity.tictactoe;

import java.util.Arrays;

/**
 * Class representing a tic-tac-toe game.
 */
public class Tictactoe {
	private final char[][] mBoard;
	private final char mHumanMark;
	private final char mCpuMark;
	
	// Super-duper-simple selfish CPU AI (won't try to block player moves)
	private Move mLastMove;
	private Move mSecondToLastMove;
	private int mIDirection;
	private int mJDirection;
	
	/**
	 * Construct a tictactoe game with the specified player marks.
	 * 
	 * @param humanMark  human player's mark on the board
	 * @param cpuMark    computer's mark on the board
	 */
	public Tictactoe(char humanMark, char cpuMark) {
		// Initialize game properties
		mBoard = new char[3][3];
		for (char[] row : mBoard) Arrays.fill(row, ' ');
		mHumanMark = humanMark;
		mCpuMark = cpuMark;
	}

	/**
	 * Insets human player's mark in the specified coordinates on the board.
	 *  
	 * @param i  row of the move
	 * @param j  column of the move
	 */
	public void makeHumanMove(int i, int j) {
		if (i >= mBoard.length || j >= mBoard[i].length) {
			throw new IllegalArgumentException("Movement parameters outside the board!");			
		}
		if (!isFull() && mBoard[i][j] == ' ') {
			mBoard[i][j] = mHumanMark;
		}
	}
	
	/**
	 * Makes the computer player's move on the board. 
	 * The internal AI will attempt to evaluate the best
	 * move for the computer.
	 * 
	 * @return the move that was made
	 */
	public Move makeCpuMove() {
		if (isFull()) return mLastMove;
		
		// On the 1st move, make a random move
		if (mLastMove == null) {
			mLastMove = randomCpuMove();
		} 
		// On the 2nd move, randomly pick the direction to continue to
		else if (mSecondToLastMove == null) {
			Move newMove = chooseCpuDirectionAndMove();
			mSecondToLastMove = mLastMove;
			mLastMove = newMove;
		}
		// On successive moves, attempt to continue in the defined direction.
		// Failing that, attempt to continue in reverse direction.
		// Failing THAT, randomly select a new square.
		else {
			int newI = mLastMove.i + mIDirection;
			int newJ = mLastMove.j + mJDirection;
			if (newI >= 0 && newI <= 2
			 && newJ >= 0 && newJ <= 2
			 && mBoard[newI][newJ] == ' ') {
				
				mBoard[newI][newJ] = mCpuMark;
				mSecondToLastMove = mLastMove;
				mLastMove = new Move(newI, newJ); 
			} else {
				newI = mLastMove.i - mIDirection;
				newJ = mLastMove.j - mJDirection;
				if (newI >= 0 && newI <= 2
				 && newJ >= 0 && newJ <= 2
				 && mBoard[newI][newJ] == ' ') {
					
					mBoard[newI][newJ] = mCpuMark;
					mSecondToLastMove = mLastMove;
					mLastMove = new Move(newI, newJ); 
				} else {
					mLastMove = randomCpuMove();
					mSecondToLastMove = null;
				}				
			}
		}
		
		return mLastMove;
	}
	
	/**
	 * Marks the computer player's mark on a random 
	 * (but legal) location on the game board.
	 * 
	 * @return the move that was made
	 */
	private Move randomCpuMove() {
		while (true) {
			int i = (int)(Math.random() * 3);
			int j = (int)(Math.random() * 3);
			if (mBoard[i][j] == ' ') {
				mBoard[i][j] = mCpuMark;
				return new Move(i, j);
			}
		}
	}
	
	/**
	 * Chooses a direction for computer player's future moves.
	 * Also makes one move to that direction. If no legal direction 
	 * is found within reasonable amount of tries, 
	 * a random move is made ({@link #randomCpuMove()}.
	 * 
	 * @return the move that was made
	 */
	private Move chooseCpuDirectionAndMove() {
		for (int i = 0; i < 20; ++i) { // attempt 20 times
			int iDirection = (int)(Math.random()*3 - 1);
			int jDirection = (int)(Math.random()*3 - 1);
			
			// Check if the move square would be empty and within the board limits
			int newI = mLastMove.i + iDirection;
			int newJ = mLastMove.j + jDirection;
			if (newI >= 0 && newI <= 2
			 && newJ >= 0 && newJ <= 2
			 && mBoard[newI][newJ] == ' ') {
				
				mBoard[newI][newJ] = mCpuMark;
				mIDirection = iDirection;
				mJDirection = jDirection;
				return new Move(newI, newJ);
			}
		}
		
		// Failed to find a suitable direction (maybe blocked in a corner?),
		// make a random move instead..
		return randomCpuMove();
	}
	
	/**
	 * Returns whether the game board is full.
	 * 
	 * @return true if the board is full
	 */
	public boolean isFull() {
		for (char[] row : mBoard) {
			for (char col : row) {
				if (col == ' ') return false;
			}
		}
		return true;
	}
	
	/**
	 * Attempts to find a winning line on the board.
	 * This method can be checked against null to check whether the 
	 * game is still in progress (more moves can still be made).
	 * 
	 * @return the winner if possible to determine, null if game is still in progress
	 * @see Winner
	 */
	public Winner findWinner() {
		// Check horizontal lines
		for (char[] row : mBoard) {
			if (row[0] != ' ' && row[0] == row[1] && row[0] == row[2]) {
				return (row[0] == mHumanMark) ? Winner.HUMAN : Winner.CPU;
			}
		}
		
		// Check for vertical lines
		for (int j = 0; j < mBoard[0].length; ++j) {
			if (mBoard[0][j] != ' ' && mBoard[0][j] == mBoard[1][j] && mBoard[0][j] == mBoard[2][j]) {
				return (mBoard[0][j] == mHumanMark) ? Winner.HUMAN : Winner.CPU;
			}
		}
		
		// Check diagonal lines
		if (mBoard[1][1] != ' ' && ((mBoard[0][0] == mBoard[1][1] && mBoard[0][0] == mBoard[2][2])      // NW <-> SE 
		                         || (mBoard[2][0] == mBoard[1][1] && mBoard[2][0] == mBoard[0][2])) ) { // SW <-> NE
			
			// mBoard[1][1] has the winner's mark in both cases
			return (mBoard[1][1] == mHumanMark) ? Winner.HUMAN : Winner.CPU;
		}
		
		// If board is full, must be a draw..
		if (isFull()) return Winner.DRAW;
		
		// Otherwise, winner can't be determined yet..
		return null;
	}

}
