package com.cs619.karen.tankclient.util;

import android.util.Log;
import android.view.View;

import com.cs619.karen.tankclient.R;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EService;
import org.androidannotations.annotations.EView;

/**
 * Created by cam04_000 on 11/9/2015.
 */

public class ButtonListener implements View.OnClickListener {

    private static final String TAG = "ButtonListener";

    TankService mTankService;

    public ButtonListener( TankService service ){
        mTankService = service;
    }

    @Override
    public void onClick( View view ){
        switch ( view.getId( ) ){
            case R.id.up_button:
                moveUp(view);
                break;
            case R.id.down_button:
                moveDown(view);
                break;
            case R.id.left_button:
                turnLeft(view);
                break;
            case R.id.right_button:
                turnRight(view);
                break;
            case R.id.fire_button:
                fire(view);
                break;
        }

    }


    //@Background
    public void moveUp(View view ){
        boolean moved = mTankService.move( (byte) 0 ).isResult();
        Log.d(TAG, "MOVE UP");
        Log.d(TAG, "" + moved);
    }

    //@Background
    public void moveDown( View view ){
        boolean moved = mTankService.move( (byte ) 4 ).isResult();
        Log.d(TAG, "MOVE DOWN");
        Log.d(TAG, "" + moved);
    }

    //@Background
    public void turnLeft(View view ){
        boolean moved = mTankService.turn((byte) 6).isResult();
        Log.d( TAG, "TURN LEFT");
        Log.d( TAG, "" + moved );
    }

    //@Background
    public void turnRight(View view ){
        boolean moved = mTankService.turn( (byte) 2 ).isResult();
        Log.d( TAG, "TURN RIGHT");
        Log.d( TAG, "" + moved );
    }

    //@Background
    public void fire( View view ){
        boolean fired = mTankService.fire().isResult();
        Log.d(TAG,"FIRED");
        Log.d(TAG, "" + fired );
    }
}

