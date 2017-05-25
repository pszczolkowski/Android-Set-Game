package com.example.pszczolkowski.thesetgame.Card;

import com.example.pszczolkowski.thesetgame.Card.features.ColorFeature;
import com.example.pszczolkowski.thesetgame.Card.features.FulfillmentFeature;
import com.example.pszczolkowski.thesetgame.Card.features.QuantityFeature;
import com.example.pszczolkowski.thesetgame.Card.features.ShapeFeature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck{

	private static List< Card > fullDeck;

	public final List< Card > draw( int quantity , List< CardSet > possibleSets ){
		if( fullDeck == null )
			fullDeck = generateFullDeck();

		List< Card > cards;

		do{
			possibleSets.clear();

			Collections.shuffle( fullDeck );
			cards = new ArrayList<>( fullDeck.subList( 0 , quantity ) );

			possibleSets.addAll( findSets( cards ) );
		}while( possibleSets.size() < 3 || possibleSets.size() > 6 );

		return cards;
	}

	private List< Card > generateFullDeck(){
		List< Card > result = new ArrayList<>(  );

		for( int i = 0 ; i < ColorFeature.values().length ; i++ ){
			for( int j = 0 ; j < QuantityFeature.values().length ; j++ ){
				for( int k = 0 ; k < ShapeFeature.values().length ; k++ ){
					for( int l = 0 ; l < FulfillmentFeature.values().length ; l++){
						result.add( new Card(
								ColorFeature.values()[ i ] ,
								ShapeFeature.values()[ k ] ,
								FulfillmentFeature.values()[ l ] ,
								QuantityFeature.values()[ j ]
						) );
					}
				}
			}
		}

		return  result;
	}

	private List< CardSet > findSets(List<Card> cards){
		List< CardSet > foundSets = new ArrayList<>(  );

		for( int i = 0 ; i < cards.size() - 2 ; i++ ){
			for( int j = i + 1 ; j < cards.size() - 1 ; j++ ){
				for( int k = j + 1 ; k < cards.size() ; k++ ){
					CardSet cardSet = new CardSet( cards.get( i ) , cards.get( j ) , cards.get( k ) );
					if( cardSet.isValid() ){
						foundSets.add( cardSet );
					}
				}
			}
		}

		return foundSets;
	}

}
