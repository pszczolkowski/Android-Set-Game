package com.example.pszczolkowski.thesetgame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.pszczolkowski.thesetgame.Card.CardSet;


public class MainActivity extends ActionBarActivity implements CardFragment.OnSetFoundListener, CardFragment.OnGameFinishedListener{

	private static String SAVED_GAME_NAME = "game";

	private CardFragment cardFragment;
	private FoundSetsFragment foundSetsFragment;
	private Game game;

	@Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		// WCZYTANIE GRY
		if( savedInstanceState != null )
			loadGame( (Game) savedInstanceState.getSerializable( SAVED_GAME_NAME ) );
		// ROZPOCZECIE NOWEJ GRY
		else
			startNewGame();
	}

    @Override
    public final boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
    }

    @Override
    public final boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

		// START NEW GAME ON REQUEST
        if (id == R.id.action_new_game) {
			game = new Game();
			initializeFragments();

			cardFragment.repaint();
			foundSetsFragment.repaint();
            return true;
		// DISPLAY GAME RULES
        }else if( id == R.id.action_rules ){
			displayRules();
			return true;
		}

        return super.onOptionsItemSelected(item);
    }

	@Override
	public final void onWindowFocusChanged(boolean hasFocus){
		if( hasFocus ){
			cardFragment.repaint();
			foundSetsFragment.repaint();
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState( outState );

		outState.putSerializable( SAVED_GAME_NAME , game );
	}

	@Override
	public final void setFound(CardSet set){
		foundSetsFragment.foundSet( set );
	}

	private void initializeFragments(){
		cardFragment = (CardFragment)getFragmentManager().findFragmentById( R.id.cardsFragment );
		foundSetsFragment = (FoundSetsFragment)getFragmentManager().findFragmentById( R.id.foundSetsFragment );

		cardFragment.setGame( game );
		foundSetsFragment.setGame( game );

		cardFragment.addOnSetFoundListener( this );
		cardFragment.addOnGameFinishedListener( this );
	}

	private void displayRules(){
		AlertDialog.Builder builder = new AlertDialog.Builder( this );
		builder.setTitle( R.string.game_rules )
				.setMessage( R.string.rules_content )
				.setPositiveButton( "OK" , null );

		builder.create().show();
	}

	private void startNewGame(){
		game = new Game();
		initializeFragments();
	}

	private void loadGame( Game game ){
		this.game = game;
		initializeFragments();
	}

	@Override
	public void gameFinished(){
		new AlertDialog.Builder( this )
			.setMessage( R.string.game_finished )
			.setCancelable( false )
			.setNegativeButton( R.string.no , null )
			.setPositiveButton( R.string.yes , new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which){
					startNewGame();
				}
			} )
			.create()
			.show();
	}
}
