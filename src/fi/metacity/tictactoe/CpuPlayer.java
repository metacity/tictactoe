package fi.metacity.tictactoe;

public abstract class CpuPlayer {
	protected final char[][] mBoard;
	protected final char mMark;
	
	protected CpuPlayer(char[][] board, char mark) {
		mBoard = board;
		mMark = mark;
	}
	
	public abstract void makeMove();
}
