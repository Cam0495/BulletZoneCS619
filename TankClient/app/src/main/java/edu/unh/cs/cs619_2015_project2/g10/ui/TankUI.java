package edu.unh.cs.cs619_2015_project2.g10.ui;

import android.content.Context;

/**
 * Created by cdevine on 10/30/2015.
 * Tank UI to keep track of health and bullets.
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
