package fi.metacity.tictactoe;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

/**
 * The main (and only) activity of tictactoe.
 * 
 * @author Mikko Oksa <m3tacity@gmail.com>
 * @version 1.0.0
 */
public class MainActivity extends Activity {
	public static final int BOARD_SIZE = 3;     // Size of the side of the game board (e.g. 3 = 3 x 3)

	@Override
	protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initBoard();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.main, menu);
    	return true;
	}
	
	/**
	 * Initializes the game board.
	 */
	private void initBoard() {
		TableLayout table = (TableLayout) findViewById(R.id.board);	
		LayoutParams params = new TableLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f);
		LayoutParams rowParams = new TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f);
		
		for (int i = 0; i < BOARD_SIZE; ++i) {
			TableRow row = new TableRow(this);
			row.setLayoutParams(params);
			for (int j = 0; j < BOARD_SIZE; ++j) {
				Button button = new Button(this);
				button.setLayoutParams(rowParams);
				button.setText("MO");
				row.addView(button);
			}
			table.addView(row);
		}
	}
    
}
