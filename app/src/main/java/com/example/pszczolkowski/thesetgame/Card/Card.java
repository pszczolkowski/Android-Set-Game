package com.example.pszczolkowski.thesetgame.Card;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.example.pszczolkowski.thesetgame.Card.drawings.Drawer;
import com.example.pszczolkowski.thesetgame.Card.drawings.DrawersFactory;
import com.example.pszczolkowski.thesetgame.Card.features.ColorFeature;
import com.example.pszczolkowski.thesetgame.Card.features.Feature;
import com.example.pszczolkowski.thesetgame.Card.features.FulfillmentFeature;
import com.example.pszczolkowski.thesetgame.Card.features.QuantityFeature;
import com.example.pszczolkowski.thesetgame.Card.features.ShapeFeature;

import java.io.Serializable;

public class Card implements Serializable{
	private static final long serialVersionUID = 1;

    private ColorFeature colorFeature;
    private ShapeFeature shapeFeature;
    private FulfillmentFeature fulfillmentFeature;
    private QuantityFeature quantityFeature;

    public Card(ColorFeature colorFeature, ShapeFeature shapeFeature, FulfillmentFeature fulfillmentFeature, QuantityFeature quantityFeature) {
        this.colorFeature = colorFeature;
        this.shapeFeature = shapeFeature;
        this.fulfillmentFeature = fulfillmentFeature;
        this.quantityFeature = quantityFeature;
    }

	@SuppressWarnings( "deprecation" )
	public Drawable draw( int width , int height ){
		Bitmap bitmap = Bitmap.createBitmap( width, height, Bitmap.Config.ARGB_8888 );
		Canvas canvas = new Canvas( bitmap );

		DrawersFactory factory = new DrawersFactory( shapeFeature );
		Drawer drawer = factory.createDrawer();
		drawer.draw( canvas , quantityFeature , colorFeature , fulfillmentFeature );

		return new BitmapDrawable(bitmap);
	}

	public static boolean matching( Feature feature , Card c1 , Card c2 , Card c3 ){
		switch( feature ){
			case QUANTITY:
				return c1.quantityFeature == c2.quantityFeature && c1.quantityFeature == c3.quantityFeature;
			case COLOR:
				return c1.colorFeature == c2.colorFeature && c1.colorFeature == c3.colorFeature;
			case SHAPE:
				return c1.shapeFeature == c2.shapeFeature && c1.shapeFeature == c3.shapeFeature;
			case FULFILLMENT:
				return c1.fulfillmentFeature == c2.fulfillmentFeature && c1.fulfillmentFeature == c3.fulfillmentFeature;
			default:
				throw new IllegalArgumentException( );
		}
	}

	public static boolean different(Feature feature , Card c1 , Card c2 , Card c3){
		switch( feature ){
			case QUANTITY:
				return c1.quantityFeature != c2.quantityFeature && c1.quantityFeature != c3.quantityFeature && c2.quantityFeature != c3.quantityFeature;
			case COLOR:
				return c1.colorFeature != c2.colorFeature && c1.colorFeature != c3.colorFeature && c2.colorFeature != c3.colorFeature;
			case SHAPE:
				return c1.shapeFeature != c2.shapeFeature && c1.shapeFeature != c3.shapeFeature && c2.shapeFeature != c3.shapeFeature;
			case FULFILLMENT:
				return c1.fulfillmentFeature != c2.fulfillmentFeature && c1.fulfillmentFeature != c3.fulfillmentFeature && c2.fulfillmentFeature != c3.fulfillmentFeature;
			default:
				throw new IllegalArgumentException( );
		}
	}

	@Override
	public boolean equals(Object o){
		if(this == o) return true;
		if(!( o instanceof Card )) return false;

		Card card = (Card) o;

		if(colorFeature != card.colorFeature) return false;
		if(fulfillmentFeature != card.fulfillmentFeature) return false;
		if(quantityFeature != card.quantityFeature) return false;
		//noinspection RedundantIfStatement
		if(shapeFeature != card.shapeFeature) return false;

		return true;
	}

	@Override
	public int hashCode(){
		int result = colorFeature.hashCode();
		result = 31 * result + shapeFeature.hashCode();
		result = 31 * result + fulfillmentFeature.hashCode();
		result = 31 * result + quantityFeature.hashCode();
		return result;
	}
}
