package com.example.pszczolkowski.thesetgame;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.pszczolkowski.thesetgame.Card.Card;
import com.example.pszczolkowski.thesetgame.Card.CardButton;
import com.example.pszczolkowski.thesetgame.Card.CardSet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CardFragment extends Fragment{

	private static final String SELECTED_SET_NAME = "SELECTED_SET";

	private Game game;
	private List<CardButton > cardButtons = new ArrayList<>(  );
	private CardSet selectedSet = new CardSet(  );
	private Set< OnSetFoundListener > onSetFoundListeners = new HashSet<>(  );
	private Set<OnGameFinishedListener> onGameFinishedListeners = new HashSet<>(  );
	private Toast m_toast;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		View view = inflater.inflate( R.layout.fragment_card , container , false );

		if( savedInstanceState != null ){
			CardSet savedSet = (CardSet) savedInstanceState.getSerializable( SELECTED_SET_NAME );
			if(savedSet != null)
				selectedSet = savedSet;
		}

		return view;
	}

	@Override
	public void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState( outState );
		outState.putSerializable( SELECTED_SET_NAME , selectedSet );
	}

	public void repaint(){
		for( CardButton button : cardButtons ){
			button.draw();
		}
	}

	public void addOnSetFoundListener( OnSetFoundListener listener ){
		onSetFoundListeners.add( listener );
	}

	public void addOnGameFinishedListener( OnGameFinishedListener listener ){
		onGameFinishedListeners.add( listener );
	}

	public void setGame(Game game){
		// SPRAWDZENIE CZY WYBRANO ZACZECIE NOWEJ GRY
		if( this.game != null )
			selectedSet = new CardSet(  );

		this.game = game;

		int[] cardButtonIDs = {
				R.id.card_1,
				R.id.card_2,
				R.id.card_3,
				R.id.card_4,
				R.id.card_5,
				R.id.card_6,
				R.id.card_7,
				R.id.card_8,
				R.id.card_9,
				R.id.card_10,
				R.id.card_11,
				R.id.card_12,
		};

		cardButtons.clear();

		for(int i = 0; i < cardButtonIDs.length; i++){
			Button button = (Button) getActivity().findViewById( cardButtonIDs[ i ] );
			Card card = game.getCards().get( i );
			CardButton cardButton = new CardButton( button, card );
			cardButtons.add( cardButton );
			if( selectedSet.contains( card ) )
				cardButton.setSelected( true );

			button.setOnClickListener( new cardButtonListener( cardButton ) );
		}
	}


	private void emitSetFoundEvent( CardSet set ){
		for( OnSetFoundListener listener : onSetFoundListeners )
			listener.setFound( set );
	}

	private void gameFinished(){
		for( OnGameFinishedListener listener: onGameFinishedListeners )
			listener.gameFinished();
	}

	private class cardButtonListener implements View.OnClickListener {
		private CardButton cardButton;

		public cardButtonListener( CardButton cardButton ){
			this.cardButton = cardButton;
		}

		@Override
		public void onClick(View v){
			if( cardButton.isSelected() ){
				cardButton.setSelected( false );
				selectedSet.remove( cardButton.getCard() );
			}else if( game.isFinished() ){
				toast( R.string.game_already_finished );
			}else{
				cardButton.setSelected( true );

				selectedSet.add( cardButton.getCard() );
				if(selectedSet.isFull()){
					if( selectedSet.isValid() ){
						if( game.foundSet( selectedSet ) ){
							toast( R.string.msg_set_found );
							emitSetFoundEvent( selectedSet );

							if( game.isFinished() ){
								gameFinished();
							}
						}else{
							toast( R.string.msg_set_already_found );
						}
					}else{
						String msg = getResources().getString( R.string.not_a_set ) + "\n" + selectedSet.cardsDifferences( getActivity() );
						toast( msg, Toast.LENGTH_LONG );
					}

					for( CardButton button : cardButtons )
						button.setSelected( false );
					selectedSet = new CardSet(  );
				}
			}
		}
	}

	private void toast( String text , int duration ){
		if( m_toast == null )
			m_toast = Toast.makeText( getActivity() , text , duration );
		else
			m_toast.setText( text );

		m_toast.show();
	}
	private void toast( int text ){
		toast( getResources().getString( text ) , Toast.LENGTH_SHORT );
	}

	public interface OnSetFoundListener{
		public void setFound( CardSet set );
	}
	public interface OnGameFinishedListener{
		public void gameFinished();
	}
}
