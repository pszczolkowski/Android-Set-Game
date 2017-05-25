package com.example.pszczolkowski.thesetgame.Card.drawings;

import android.graphics.Canvas;

import com.example.pszczolkowski.thesetgame.Card.features.ColorFeature;
import com.example.pszczolkowski.thesetgame.Card.features.FulfillmentFeature;
import com.example.pszczolkowski.thesetgame.Card.features.QuantityFeature;

public interface Drawer{

	public void draw( Canvas canvas , QuantityFeature quantity , ColorFeature color ,
					  FulfillmentFeature fulfillment );

}
