package com.cs619.karen.tankclient.ui;

import android.content.Context;

/**
 * Created by cam04_000 on 11/9/2015.
 */
public abstract class EntityFactory {

    public void makeEntity( Context context, int value ){
        GameEntityUI entity = factoryMethod( context, value );
    }

    protected abstract GameEntityUI factoryMethod( Context context, int value );
}
