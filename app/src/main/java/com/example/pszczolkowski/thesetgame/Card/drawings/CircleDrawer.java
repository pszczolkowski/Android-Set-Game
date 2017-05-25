package com.example.pszczolkowski.thesetgame.Card.drawings;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.pszczolkowski.thesetgame.Card.features.ColorFeature;
import com.example.pszczolkowski.thesetgame.Card.features.FulfillmentFeature;
import com.example.pszczolkowski.thesetgame.Card.features.QuantityFeature;

class CircleDrawer implements Drawer{

	@Override
	public final void draw(Canvas canvas, QuantityFeature quantity, ColorFeature color, FulfillmentFeature fulfillment){
		int width = canvas.getWidth();
		int height = canvas.getHeight();

		canvas.drawColor( Color.WHITE );

		Paint paint = new Paint();

		if( fulfillment == FulfillmentFeature.FILLED )
			paint.setStyle(Paint.Style.FILL);
		else{
			paint.setStyle( Paint.Style.STROKE );
			paint.setStrokeWidth( 4 );
		}
		paint.setColor( color.getColor() );
		paint.setAntiAlias( true );


		int elementWidth = width / 3 - 8;
		int elementHeight = height - 4;
		int radius = Math.min( elementWidth , elementHeight ) / 2;

		if( quantity == QuantityFeature.ONE ){
			canvas.drawCircle( width / 2 , height / 2, radius, paint);
			if( fulfillment == FulfillmentFeature.HALF_FILLED ){
				paint.setStyle( Paint.Style.FILL );
				paint.setAlpha( 60 );
				canvas.drawCircle( width / 2, height / 2, radius - 2, paint );
			}
		}else if( quantity == QuantityFeature.TWO ){
			int center = width / 2;

			canvas.drawCircle( center - radius - 1 , height / 2, radius , paint);
			canvas.drawCircle( center + radius + 1 , height / 2, radius , paint);
			if( fulfillment == FulfillmentFeature.HALF_FILLED ){
				paint.setStyle( Paint.Style.FILL );
				paint.setAlpha( 60 );
				canvas.drawCircle( center - radius - 1 , height / 2, radius - 2 , paint);
				canvas.drawCircle( center + radius + 1 , height / 2, radius - 2 , paint);
			}
		}else if( quantity == QuantityFeature.THREE ){
			int center = width / 2;

			canvas.drawCircle( width / 2 , height / 2, radius, paint);
			canvas.drawCircle( center - 2*radius - 2 , height / 2, radius , paint);
			canvas.drawCircle( center + 2*radius + 2 , height / 2, radius , paint);
			if( fulfillment == FulfillmentFeature.HALF_FILLED ){
				paint.setStyle( Paint.Style.FILL );
				paint.setAlpha( 60 );
				canvas.drawCircle( width / 2 , height / 2, radius - 2, paint);
				canvas.drawCircle( center - 2*radius - 2 , height / 2, radius - 2 , paint);
				canvas.drawCircle( center + 2*radius + 2 , height / 2, radius - 2 , paint);
			}
		}

	}

}
