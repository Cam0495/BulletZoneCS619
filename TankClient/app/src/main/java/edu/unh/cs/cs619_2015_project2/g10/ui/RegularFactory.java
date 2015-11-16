package edu.unh.cs.cs619_2015_project2.g10.ui;

import android.content.Context;

/**
 * Created by cam04_000 on 11/9/2015.
 */
public class RegularFactory extends EntityFactory {
    protected GameEntityUI factoryMethod( Context context, int value ){
        if (value >= 10000000 && value <= 20000000) {
            return new TankUI(context, value);
        } else {
            return null;
        }
    }
}
