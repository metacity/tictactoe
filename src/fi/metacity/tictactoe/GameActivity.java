package fi.metacity.tictactoe;

import java.security.spec.MGF1ParameterSpec;

import android.R.color;
import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

/**
 * The main/game activity of tictactoe.
 * 
 * @author Mikko Oksa <m3tacity@gmail.com>
 * @version 1.0.0
 */
public class GameActivity extends Activity {
	private Button[][] mButtons;
	private Tictactoe mTictacToe;
	private char mHumanMark = 'X';
	private char mCpuMark = 'O';

	@Override
	protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initGame();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.main, menu);
    	return true;
	}
	
	
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_new_game:
				initGame();
				return true;
				
			default:
				return super.onMenuItemSelected(featureId, item);
		}
		
	}

	/**
	 * Initializes the board and starts the game.
	 */
	private void initGame() {
		mButtons = new Button[3][3];
		TableLayout table = (TableLayout) findViewById(R.id.board);
		table.removeAllViews();

		LayoutParams params = new TableLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f);
		LayoutParams rowParams = new TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f);
		
		for (int i = 0; i < 3; ++i) {

			TableRow row = new TableRow(this);
			row.setLayoutParams(params);
			
			// Populate the row with buttons
			for (int j = 0; j < 3; ++j) {
				Button button = new Button(this);
				button.setLayoutParams(rowParams);
				button.setTextSize(40f);
				Spannable number = new SpannableString(" " + (i*3 + j + 1) + " ");
				number.setSpan(new AbsoluteSizeSpan(16, true), 1, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				button.setText(number);
				button.setBackgroundColor(color.background_light);
				button.setOnClickListener(new SquareClickListener(i, j));

				row.addView(button);
				mButtons[i][j] = button;
			}
			table.addView(row);
		}
		
		mTictacToe = new Tictactoe(mHumanMark, mCpuMark);
	}
	
	/**
	 * Draws button texts according to the char array content.
	 */
	private void refreshButtons() {
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				
			}
		}
	}

	private void announceWinnerIfPossible() {
		Winner winner = mTictacToe.findWinner();
		if (winner != null) {
			switch (winner) {
				case HUMAN:
					Toast.makeText(getApplicationContext(), "Voitit! :)", Toast.LENGTH_LONG).show();
					break;
				case CPU:
					Toast.makeText(getApplicationContext(), "Hävisit! :(", Toast.LENGTH_LONG).show();
					break;
				case DRAW:
					Toast.makeText(getApplicationContext(), "Tasapeli!", Toast.LENGTH_LONG).show();
					break;
				default:
					break;
			}
		}
	}
	
	private class SquareClickListener implements View.OnClickListener {
		private final int mRow;
		private final int mColumn;
		
		public SquareClickListener(int row, int column) {
			mRow = row;
			mColumn = column;
		}

		@Override
		public void onClick(View v) {
			if (mTictacToe.findWinner() == null) {
				mTictacToe.makeHumanMove(mRow, mColumn);
				mButtons[mRow][mColumn].setText(String.valueOf(mHumanMark));
				announceWinnerIfPossible();
				if (mTictacToe.findWinner() == null) {
					Move cpuMove = mTictacToe.makeCpuMove();
					mButtons[cpuMove.i][cpuMove.j].setText(String.valueOf(mCpuMark));
					announceWinnerIfPossible();
				}
			}
		}
		
	}
	
}
