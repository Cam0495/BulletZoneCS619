package com.cs619.karen.tankclient.ui;

import android.graphics.drawable.Drawable;
import android.content.Context;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.cs619.karen.tankclient.R;

/**
 * Created by cdevine on 10/30/2015.
 */
public class TankUI implements GameEntityUI{

    int direction, life, tankID, bullets;

    public TankUI( Context context, int value  ){

        String stringID = Integer.toString(value);
        setValues( stringID );

    }

    private void setValues( String id ){
        direction = Integer.parseInt( id.substring( 6, 7 ) );
        life = Integer.parseInt( id.substring( 4, 6 ) );
        tankID = Integer.parseInt( id.substring( 1, 3 ) );
        bullets = 0;
    }

}
