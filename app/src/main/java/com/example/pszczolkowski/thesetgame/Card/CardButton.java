package com.example.pszczolkowski.thesetgame.Card;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.widget.Button;

public class CardButton{

	private static final int BUTTON_SELECTED_COLOR = Color.RED;
	private static final int BUTTON_NOT_SELECTED_COLOR = Color.BLACK;

	private boolean selected;
	private boolean drawn = false;
	private Button button;
	private Card card;

	public CardButton(Button button, Card card){
		this( button , card , false);
	}

	private CardButton(Button button, Card card , boolean selected){
		this.selected = selected;
		this.button = button;
		this.card = card;
	}

	public final boolean isSelected(){
		return selected;
	}

	public final void setSelected(boolean selected){
		this.selected = selected;

		if( drawn ){
			if(selected)
				mark( true );
			else
				mark( false );
		}
	}

	public final Card getCard(){
		return card;
	}

	public final void setCard(Card card){
		this.card = card;
	}

	public final void draw(){
		button.setBackground( card.draw( button.getMeasuredWidth(), button.getMeasuredHeight() ) );

		if( selected )
			mark( true );
		else
			mark( false );

		drawn = true;
	}

	private void mark( boolean selected ){
		Bitmap bitmap = ((BitmapDrawable)button.getBackground()).getBitmap();
		Canvas canvas = new Canvas( bitmap );
		Paint paint = new Paint(  );
		paint.setStyle( Paint.Style.FILL );

		if( selected ){
			paint.setColor( BUTTON_SELECTED_COLOR );
			paint.setStrokeWidth( 3 );
		}else {
			paint.setColor( BUTTON_NOT_SELECTED_COLOR );
			paint.setStrokeWidth( 3 );
		}

		canvas.drawLine( 1 , 1 , canvas.getWidth()-2 , 1, paint );
		canvas.drawLine( 1 , 1 , 1 , canvas.getHeight()-2, paint );
		canvas.drawLine( canvas.getWidth()-2 , 1 , canvas.getWidth()-2 , canvas.getHeight()-2, paint );
		canvas.drawLine( 1 , canvas.getHeight()-2 , canvas.getWidth()-2 , canvas.getHeight()-2, paint );

		//noinspection deprecation
		button.setBackground( new BitmapDrawable( bitmap ) );
		button.refreshDrawableState();
	}

}
