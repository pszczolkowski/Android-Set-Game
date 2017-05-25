package com.example.pszczolkowski.thesetgame;

import com.example.pszczolkowski.thesetgame.Card.Card;
import com.example.pszczolkowski.thesetgame.Card.CardSet;
import com.example.pszczolkowski.thesetgame.Card.Deck;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game implements Serializable{

	private static final long serialVersionUID = 1;

	public static final int CARDS_PER_GAME = 12;
	private final List< CardSet > foundSets = new ArrayList<>(  );
	private List< CardSet > possibleSets = new ArrayList<>( );
	private List<Card> cards;

	public Game(){
		Deck deck = new Deck();
		cards = deck.draw( CARDS_PER_GAME , possibleSets );
	}

	public final boolean foundSet( CardSet set ){
		if( foundSets.contains( set ) )
			return false;

		foundSets.add( set );
		return true;
	}

	public final List< CardSet > getFoundSets(){
		return Collections.unmodifiableList( foundSets );
	}

	public final List< CardSet > getPossibleSets(){
		return Collections.unmodifiableList( possibleSets );
	}

	public final boolean isFinished(){
		return foundSets.size() == possibleSets.size();
	}

	public final List< Card > getCards(){
		return Collections.unmodifiableList( cards );
	}

}
