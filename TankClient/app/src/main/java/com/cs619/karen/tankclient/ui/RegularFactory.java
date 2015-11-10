package com.cs619.karen.tankclient.ui;

/**
 * Created by cam04_000 on 11/9/2015.
 */
public class RegularFactory extends EntityFactory {
    protected GameEntityUI factoryMethod( int value ){
        if (value == 1000) {
            return new WallUI( value );
        } else if (value >= 2000000 && value <= 3000000) {
            return new BulletUI( value );
        } else if (value >= 10000000 && value <= 20000000) {
            return new TankUI(value);
        } else {
            return null;
        }
    }
}
