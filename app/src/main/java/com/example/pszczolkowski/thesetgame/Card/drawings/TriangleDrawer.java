package com.example.pszczolkowski.thesetgame.Card.drawings;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import com.example.pszczolkowski.thesetgame.Card.features.ColorFeature;
import com.example.pszczolkowski.thesetgame.Card.features.FulfillmentFeature;
import com.example.pszczolkowski.thesetgame.Card.features.QuantityFeature;

class TriangleDrawer implements Drawer {

	@Override
	public final void draw(Canvas canvas, QuantityFeature quantity, ColorFeature color, FulfillmentFeature fulfillment){
		int width = canvas.getWidth();
		int height = canvas.getHeight();

		canvas.drawColor( Color.WHITE );

		int elementSize = Math.min( height - 4 , width / 3 - 8);

		int center = width / 2;
		int heightCenter = height / 2;

		Paint paint = new Paint();
		if( fulfillment == FulfillmentFeature.FILLED )
			paint.setStyle(Paint.Style.FILL);
		else{
			paint.setStyle( Paint.Style.STROKE );
			paint.setStrokeWidth( 4 );
		}
		paint.setColor( color.getColor() );
		paint.setAntiAlias( true );

		Path p = new Path(  );

		if( quantity == QuantityFeature.ONE ){
			p.moveTo( center, heightCenter - elementSize / 2 );
			p.rLineTo( elementSize / 2, elementSize );
			p.rLineTo( -elementSize, 0 );
			p.rLineTo( elementSize / 2, -elementSize );
			p.close();
			canvas.drawPath( p , paint );

			if( fulfillment == FulfillmentFeature.HALF_FILLED ){
				p = new Path(  );
				paint.setAlpha( 60 );
				paint.setStyle( Paint.Style.FILL );
				p.moveTo( center, heightCenter - elementSize / 2 );
				p.rLineTo( elementSize / 2, elementSize );
				p.rLineTo( -elementSize, 0 );
				p.rLineTo( elementSize / 2, -elementSize );
				p.close();
				canvas.drawPath( p , paint );
			}
		}else if( quantity == QuantityFeature.TWO ){
			p.moveTo( center - elementSize / 2 - 2, heightCenter - elementSize / 2 );
			p.rLineTo( elementSize / 2, elementSize );
			p.rLineTo( -elementSize, 0 );
			p.rLineTo( elementSize / 2, -elementSize );
			p.close();
			canvas.drawPath( p, paint );
			Path p2 = new Path(  );
			p2.moveTo( center + elementSize / 2 + 2, heightCenter - elementSize / 2 );
			p2.rLineTo( elementSize / 2, elementSize );
			p2.rLineTo( -elementSize, 0 );
			p2.rLineTo( elementSize / 2, -elementSize );
			p2.close();
			canvas.drawPath( p2 , paint );

			if( fulfillment == FulfillmentFeature.HALF_FILLED ){
				paint.setAlpha( 60 );
				paint.setStyle( Paint.Style.FILL );
				canvas.drawPath( p, paint );
				canvas.drawPath( p2 , paint );
			}
		}else if( quantity == QuantityFeature.THREE ){
			p.moveTo( center - elementSize - 2, heightCenter - elementSize / 2 );
			p.rLineTo( elementSize / 2, elementSize );
			p.rLineTo( -elementSize, 0 );
			p.rLineTo( elementSize / 2, -elementSize );
			p.close();
			canvas.drawPath( p, paint );
			Path p2 = new Path();
			p2.moveTo( center, heightCenter - elementSize / 2 );
			p2.rLineTo( elementSize / 2, elementSize );
			p2.rLineTo( -elementSize, 0 );
			p2.rLineTo( elementSize / 2, -elementSize );
			p2.close();
			canvas.drawPath( p2, paint );
			Path p3 = new Path();
			p3.moveTo( center + elementSize + 2, heightCenter - elementSize / 2 );
			p3.rLineTo( elementSize / 2, elementSize );
			p3.rLineTo( -elementSize, 0 );
			p3.rLineTo( elementSize / 2, -elementSize );
			p3.close();
			canvas.drawPath( p3, paint );

			if(fulfillment == FulfillmentFeature.HALF_FILLED){
				paint.setAlpha( 60 );
				paint.setStyle( Paint.Style.FILL );
				canvas.drawPath( p, paint );
				canvas.drawPath( p2, paint );
				canvas.drawPath( p3, paint );
			}
		}
	}

}
