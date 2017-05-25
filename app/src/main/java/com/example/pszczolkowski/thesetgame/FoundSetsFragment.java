package com.example.pszczolkowski.thesetgame;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.pszczolkowski.thesetgame.Card.Card;
import com.example.pszczolkowski.thesetgame.Card.CardSet;

import java.util.ArrayList;
import java.util.List;

public class FoundSetsFragment extends Fragment{

	private List< CardSet > foundSets = new ArrayList<>(  );

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate( R.layout.fragment_found_sets , container , false );
	}

	public void setPossibleSetsQuantity( int quantity ){
		LayoutInflater inflater = LayoutInflater.from( getActivity() );

		TableLayout tableLayout = (TableLayout) getActivity().findViewById( R.id.foundSetsTable );

		tableLayout.removeAllViews();

		for( int i = 0 ; i < quantity ; i++ ){
			inflater.inflate( R.layout.found_set_row, tableLayout );
		}

		TextView possibleSetsQuantity = (TextView) getActivity().findViewById( R.id.possibleSetsQuantity );
		possibleSetsQuantity.setText( quantity + "" );
	}

	public void foundSet( CardSet set){
		TableLayout table = (TableLayout) getActivity().findViewById( R.id.foundSetsTable );

		TableRow row = (TableRow) table.getChildAt( foundSets.size() );

		List<Card> cards = new ArrayList<>( set.getCards() );

		for( int i = 0 ; i < cards.size() ; i++ ){
			View view = row.getVirtualChildAt( i );
			view.setBackground( cards.get( i ).draw( view.getMeasuredWidth() , view.getMeasuredHeight() ) );
		}

		foundSets.add( set );

		TextView foundSetsQuantity = (TextView) getActivity().findViewById( R.id.foundSetsQuantity );
		foundSetsQuantity.setText( foundSets.size() + "" );
	}

	public final void repaint(){
		TableLayout table = (TableLayout) getActivity().findViewById( R.id.foundSetsTable );

		for( int i = 0 ; i < table.getChildCount() ; i++ ){
			TableRow row = (TableRow) table.getChildAt( i );
			View view1 = row.getChildAt( 0 );
			View view2 = row.getChildAt( 1 );
			View view3 = row.getChildAt( 2 );

			if( i < foundSets.size() ){
				List< Card > cards = new ArrayList<>( foundSets.get( i ).getCards() );

				view1.setBackground( cards.get( 0 ).draw( view1.getWidth() , view1.getHeight() ) );
				view2.setBackground( cards.get( 1 ).draw( view2.getWidth() , view2.getHeight() ) );
				view3.setBackground( cards.get( 2 ).draw( view3.getWidth() , view3.getHeight() ) );
			}else {
				view1.setBackgroundColor( Color.WHITE );
				view2.setBackgroundColor( Color.WHITE );
				view3.setBackgroundColor( Color.WHITE );
			}
		}

		TextView foundSetsQuantity = (TextView) getActivity().findViewById( R.id.foundSetsQuantity );
		foundSetsQuantity.setText( foundSets.size() + "" );
	}

	public final void setGame( Game game ){
		foundSets = new ArrayList<>( game.getFoundSets() );
		setPossibleSetsQuantity( game.getPossibleSets().size() );
	}
}
