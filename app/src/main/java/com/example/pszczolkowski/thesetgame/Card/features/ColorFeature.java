package com.example.pszczolkowski.thesetgame.Card.features;

import android.graphics.Color;

public enum ColorFeature {
    RED( Color.RED ) , GREEN( Color.parseColor( "#00AA00" ) ) , BLUE( Color.BLUE );

	private int color;

	private ColorFeature( int color ){
		this.color = color;
	}

	public final int getColor(){
		return color;
	}

}
