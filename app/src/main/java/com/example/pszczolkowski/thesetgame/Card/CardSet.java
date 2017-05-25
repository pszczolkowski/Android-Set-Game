package com.example.pszczolkowski.thesetgame.Card;

import android.content.Context;

import com.example.pszczolkowski.thesetgame.Card.features.Feature;
import com.example.pszczolkowski.thesetgame.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CardSet implements Serializable{

	private static final long serialVersionUID = 1;

	private final static int CARDS_PER_SET = 3;

	private Set< Card > cards = new HashSet<>();

	public CardSet(){ }
	public CardSet(Card card1 , Card card2 , Card card3 ){
		cards.add( card1 );
		cards.add( card2 );
		cards.add( card3 );
	}

	public final boolean isFull(){
		return cards.size() == CARDS_PER_SET;
	}

	public final void add( Card card ){
		if( isFull() )
			throw new CardSetFullException();

		cards.add( card );
	}
	public final boolean remove( Card card ){
		return cards.remove( card );
	}
	public final Set< Card > getCards(){
		return Collections.unmodifiableSet( cards );
	}
	public final boolean isValid(){
		if( !isFull() )
			return false;

		List< Card > cards = new ArrayList<>( this.cards );

		for( int i = 0 ; i < cards.size() - 2 ; i++ ){
			for( int j = i + 1 ; j < cards.size() - 1 ; j++ ){
				for( int k = j + 1 ; k < cards.size() ; k++ ){
					Card c1 = cards.get( i );
					Card c2 = cards.get( j );
					Card c3 = cards.get( k );

					if( !(Card.matching( Feature.COLOR , c1 , c2 , c3 ) || Card.different( Feature.COLOR , c1 , c2 , c3 )) ||
						!(Card.matching( Feature.QUANTITY , c1 , c2 , c3 ) || Card.different( Feature.QUANTITY , c1 , c2 , c3 )) ||
						!(Card.matching( Feature.SHAPE , c1 , c2 , c3 ) || Card.different( Feature.SHAPE , c1 , c2 , c3 )) ||
						!(Card.matching( Feature.FULFILLMENT , c1 , c2 , c3 ) || Card.different( Feature.FULFILLMENT , c1 , c2 , c3 ))
						){
						return false;
					}
				}
			}
		}

		return true;
	}

	public final String cardsDifferences( Context context ){
		String result = null;
		List< Card > cards = new ArrayList<>( this.cards );

		Card c1 = cards.get( 0 );
		Card c2 = cards.get( 1 );
		Card c3 = cards.get( 2 );

		if( !(Card.matching( Feature.COLOR , c1 , c2 , c3 ) || Card.different( Feature.COLOR , c1 , c2 , c3 )) )
			result = context.getResources().getString( R.string.card_differences_color );
		else if(!(Card.matching( Feature.QUANTITY , c1 , c2 , c3 ) || Card.different( Feature.QUANTITY , c1 , c2 , c3 )))
			result = context.getResources().getString( R.string.card_differences_wuantity );
		else if( !(Card.matching( Feature.SHAPE , c1 , c2 , c3 ) || Card.different( Feature.SHAPE , c1 , c2 , c3 )) )
			result = context.getResources().getString( R.string.card_differences_shape );
		else if( !(Card.matching( Feature.FULFILLMENT , c1 , c2 , c3 ) || Card.different( Feature.FULFILLMENT , c1 , c2 , c3 )) )
		result = context.getResources().getString( R.string.card_differences_fulfillment );

		return result;
	}

	@Override
	public final boolean equals(Object o){
		if(this == o) return true;
		if(!( o instanceof CardSet )) return false;

		CardSet cardSet = (CardSet) o;

		//noinspection RedundantIfStatement
		if(!cards.equals( cardSet.cards )) return false;

		return true;
	}

	@Override
	public final int hashCode(){
		return cards.hashCode();
	}

	public boolean contains(Card card){
		return cards.contains( card );
	}
}
