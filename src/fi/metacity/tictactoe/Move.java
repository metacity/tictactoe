package fi.metacity.tictactoe;

/**
 * A simple POD class representing a 
 * simple move on the tictactoe board.
 */
public class Move {
	public final int i;
	public final int j;
	
	public Move(int i, int j) {
		this.i = i;
		this.j = j;
	}
}
