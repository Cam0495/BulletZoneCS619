package com.cs619.karen.tankclient.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.cs619.karen.tankclient.R;
import com.cs619.karen.tankclient.TankClientActivity;


/**
 * Created by cdevine on 10/30/2015.
 */

public class TankUI {


    public void TankUI(){


        TankClientActivity tAct = new TankClientActivity();

        ImageView imageView = null;

        imageView = (ImageView) imageView.findViewById(R.id.imageView);
        Drawable drawable =  ContextCompat.getDrawable(tAct.getApplicationContext(),R.drawable.tank);
        imageView.setImageDrawable(drawable);
    }
}
