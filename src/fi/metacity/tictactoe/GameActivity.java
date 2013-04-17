//package fi.metacity.tictactoe;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import android.R.color;
//import android.os.Bundle;
//import android.app.Activity;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup.LayoutParams;
//import android.widget.Button;
//import android.widget.TableLayout;
//import android.widget.TableRow;
//import android.widget.Toast;
//
///**
// * The main/game activity of tictactoe.
// * 
// * @author Mikko Oksa <m3tacity@gmail.com>
// * @version 1.0.0
// */
//public class GameActivity extends Activity {
//	private Button[][] mButtons;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//    	super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        
//        initBoard(mSquaresPerSide);
//        mCpuPlayers.add(new DefensiveCpuPlayer(mBoard, 'X'));
//        mCpuPlayers.add(new DefensiveCpuPlayer(mBoard, 'Z'));
//	}
//
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//    	getMenuInflater().inflate(R.menu.main, menu);
//    	return true;
//	}
//	
//	
//	
//	@Override
//	public boolean onMenuItemSelected(int featureId, MenuItem item) {
//		switch (item.getItemId()) {
//			case R.id.action_new_game:
//				initBoard(mSquaresPerSide);
//				return true;
//				
//			default:
//				return super.onMenuItemSelected(featureId, item);
//		}
//		
//	}
//
//	/**
//	 * Initializes and draws the game board.
//	 * 
//	 * @param squaresPerSide  the number of squares on a side (total squares = squaresPerSide^2)
//	 */
//	private void initBoard(int squaresPerSide) {
//		mButtons = new Button[squaresPerSide][squaresPerSide];
//		mBoard = new char[squaresPerSide][squaresPerSide];
//		for (char[] row : mBoard) Arrays.fill(row, ' ');
//				
//		TableLayout table = (TableLayout) findViewById(R.id.board);
//		table.removeAllViews();
//
//		LayoutParams params = new TableLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f);
//		LayoutParams rowParams = new TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f);
//		
//		for (int i = 0; i < mSquaresPerSide; ++i) {
//
//			TableRow row = new TableRow(this);
//			row.setLayoutParams(params);
//			
//			// Populate the row with buttons
//			for (int j = 0; j < mSquaresPerSide; ++j) {
//				Button button = new Button(this);
//				button.setLayoutParams(rowParams);
//				button.setTextSize(60f);
//				button.setBackgroundColor(color.background_light);
//				button.setOnClickListener(new SquareClickListener(i, j));
//
//				row.addView(button);
//				mButtons[i][j] = button;
//			}
//			table.addView(row);
//		}
//	}
//	
//	/**
//	 * Draws button texts according to the char array content.
//	 */
//	private void refreshButtons() {
//		for (int i = 0; i < mSquaresPerSide; ++i) {
//			for (int j = 0; j < mSquaresPerSide; ++j) {
//				mButtons[i][j].setText(String.valueOf(mBoard[i][j]));
//			}
//		}
//	}
//	
//	/**
//	 * Attempts to find a winning stroke/line on the game board.
//	 * 
//	 * @return winner's char if found, ' ' (space) if not found
//	 */
//	private char findWinner() {
//		for (int i = 0; i < mSquaresPerSide; ++i) {
//			for (int j = 0; j < mSquaresPerSide; ++j) {
//				char mark = mBoard[i][j];
//				for (int k = 0; k < mSquaresPerSide; ++k) {
//					// I knowm, try-catch boundary check is a sin,
//					// but easiest way to avoid mindfucking manual boundary checks
//					try {
//						
//					} catch (IndexOutOfBoundsException ioobex) {
//						// Ok, went out of the game board, no winning stroke found..
//					}
//				}
//			}
//		}
//		return ' ';
//	}
//	
//	/**
//	 * Checks if the board is fully populated.
//	 * 
//	 * @return true if board is full, false otherwise
//	 */
//	private boolean isBoardFull() {
//		for (char[] row : mBoard) {
//			for (char square : row) {
//				if (square == ' ') return false;
//			}
//		}
//		
//		return true;
//	}
//	
//	
//	private class SquareClickListener implements View.OnClickListener {
//		private final int mRow;
//		private final int mColumn;
//		
//		public SquareClickListener(int row, int column) {
//			mRow = row;
//			mColumn = column;
//		}
//
//		@Override
//		public void onClick(View v) {
//			if (mBoard[mRow][mColumn] == ' ') {
//				mBoard[mRow][mColumn] = mPlayerMark;
//				
//				for (CpuPlayer cpu : mCpuPlayers) {
//					if (findWinner() == ' ' && !isBoardFull()) {
//						cpu.makeMove();
//					}
//				}
//				refreshButtons();
//			}			
//		}
//		
//	}
//	
//}
