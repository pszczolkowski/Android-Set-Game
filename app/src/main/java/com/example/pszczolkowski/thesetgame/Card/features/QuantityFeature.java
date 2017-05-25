package com.example.pszczolkowski.thesetgame.Card.features;

public enum QuantityFeature {
    ONE( 1 ) , TWO( 2 ) , THREE( 3 );

	private final int quantity;

	QuantityFeature( int quantity ){
		this.quantity = quantity;
	}

	public final int getQuantity(){
		return quantity;
	}
}
