package com.example.pszczolkowski.thesetgame.Card.drawings;

import com.example.pszczolkowski.thesetgame.Card.features.ShapeFeature;

public class DrawersFactory{

	private final ShapeFeature shape;

	public DrawersFactory(ShapeFeature shape){
		this.shape = shape;
	}

	public final Drawer createDrawer(){
		if( shape == ShapeFeature.CIRCLE )
			return new CircleDrawer();
		else if( shape == ShapeFeature.RECTANGLE )
			return new RectangleDrawer();
		else if( shape == ShapeFeature.TRIANGLE )
			return new TriangleDrawer();
		else
			throw new IllegalArgumentException();
	}

}
