package com.example.pszczolkowski.thesetgame.Card.drawings;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.pszczolkowski.thesetgame.Card.features.ColorFeature;
import com.example.pszczolkowski.thesetgame.Card.features.FulfillmentFeature;
import com.example.pszczolkowski.thesetgame.Card.features.QuantityFeature;

class RectangleDrawer implements Drawer{


	@Override
	public final void draw(Canvas canvas, QuantityFeature quantity, ColorFeature color,
					 FulfillmentFeature fulfillment){
		int width = canvas.getWidth();
		int height = canvas.getHeight();

		canvas.drawColor( Color.WHITE );

		int elementHeight = Math.min( height - 4 , width / 3 );
		int elementWidth = elementHeight - 10;
		int center = width / 2;

		Paint paint = new Paint();
		if( fulfillment == FulfillmentFeature.FILLED )
			paint.setStyle(Paint.Style.FILL);
		else{
			paint.setStyle( Paint.Style.STROKE );
			paint.setStrokeWidth( 4 );
		}
		paint.setColor( color.getColor() );
		paint.setAntiAlias( true );

		if( quantity == QuantityFeature.ONE ){
			canvas.drawRect(
					center - elementWidth / 2,
					( height - elementHeight ) / 2,
					center + elementWidth / 2,
					( height + elementHeight ) / 2,
					paint );
			if( fulfillment == FulfillmentFeature.HALF_FILLED ){
				paint.setStyle( Paint.Style.FILL );
				paint.setAlpha( 60 );
				canvas.drawRect(
						center - elementWidth / 2 + 2,
						( height - elementHeight ) / 2 + 2,
						center + elementWidth / 2 - 2,
						( height + elementHeight ) / 2 - 2,
						paint );
			}
		}else if( quantity == QuantityFeature.TWO ){
			canvas.drawRect(
					center - elementWidth - 2,
					( height - elementHeight ) / 2,
					center - 2,
					( height + elementHeight ) / 2,
					paint );
			canvas.drawRect(
					center + 2,
					( height - elementHeight ) / 2,
					center + elementWidth + 2,
					( height + elementHeight ) / 2,
					paint );
			if( fulfillment == FulfillmentFeature.HALF_FILLED ){
				paint.setStyle( Paint.Style.FILL );
				paint.setAlpha( 60 );
				canvas.drawRect(
						center - elementWidth,
						( height - elementHeight ) / 2 + 2,
						center - 4,
						( height + elementHeight ) / 2 - 2,
						paint );
				canvas.drawRect(
						center + 2,
						( height - elementHeight ) / 2,
						center + elementWidth + 2,
						( height + elementHeight ) / 2,
						paint );
			}
		}else if( quantity == QuantityFeature.THREE ){
			canvas.drawRect(
					center - elementWidth / 2,
					( height - elementHeight ) / 2,
					center + elementWidth / 2,
					( height + elementHeight ) / 2,
					paint );
			canvas.drawRect(
					center - 3 * elementWidth / 2 - 2,
					( height - elementHeight ) / 2,
					center - elementWidth / 2 - 2,
					( height + elementHeight ) / 2,
					paint );
			canvas.drawRect(
					center + 3 * elementWidth / 2 + 2,
					( height - elementHeight ) / 2,
					center + elementWidth / 2 + 2,
					( height + elementHeight ) / 2,
					paint );
			if( fulfillment == FulfillmentFeature.HALF_FILLED ){
				paint.setStyle( Paint.Style.FILL );
				paint.setAlpha( 60 );
				canvas.drawRect(
						center - elementWidth / 2 + 2,
						( height - elementHeight ) / 2 + 2,
						center + elementWidth / 2 - 2,
						( height + elementHeight ) / 2 - 2,
						paint );
				canvas.drawRect(
						center - 3 * elementWidth / 2 ,
						( height - elementHeight ) / 2 + 2,
						center - elementWidth / 2 - 4,
						( height + elementHeight ) / 2 + 2,
						paint );
				canvas.drawRect(
						center + 3 * elementWidth / 2 + 4,
						( height - elementHeight ) / 2 + 2,
						center + elementWidth / 2 ,
						( height + elementHeight ) / 2 - 2,
						paint );
			}
		}
	}
}
