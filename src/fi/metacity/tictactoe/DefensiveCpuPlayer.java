package fi.metacity.tictactoe;

public class DefensiveCpuPlayer extends CpuPlayer {
	
	public DefensiveCpuPlayer(char[][] board, char mark) {
		super(board, mark);
	}

	@Override
	public void makeMove() {
		while (true) {
			int row = (int)(Math.random() * mBoard.length);
			int col = (int)(Math.random() * mBoard[0].length);
			
			if (mBoard[row][col] == ' ') {
				mBoard[row][col] = mMark;
				break;
			}
		}
	}
}
