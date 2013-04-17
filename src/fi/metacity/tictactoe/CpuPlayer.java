package fi.metacity.tictactoe;

public abstract class CpuPlayer {	
	protected final char[][] mBoard;
	protected final char mMark;
	
	protected CpuPlayer(char[][] board, char mark) {
		mBoard = board;
		mMark = mark;
	}
	
	public abstract void makeMove();
	
	/**
	 * Returns a CPU player with randomly selected strategy.
	 *  
	 * @param board  game board that the CPU player will be playing on
	 * @param mark   the mark the CPU player will use
	 * @return       the CPU player
	 */
	public static CpuPlayer getRandomInstance(char[][] board, char mark) {
		int selection = (int)(Math.random() * 2);
		switch (selection) {
			case 0: return new OffensiveCpuPlayer(board, mark);
			case 1: return new DefensiveCpuPlayer(board, mark);
			default: return null;
		}
	}
}
