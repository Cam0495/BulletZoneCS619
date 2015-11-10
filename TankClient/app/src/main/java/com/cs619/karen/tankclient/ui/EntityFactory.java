package com.cs619.karen.tankclient.ui;

/**
 * Created by cam04_000 on 11/9/2015.
 */
public abstract class EntityFactory {

    public void makeEntity( int value ){
        GameEntityUI entity = factoryMethod( value );
    }

    protected abstract GameEntityUI factoryMethod( int value );
}
