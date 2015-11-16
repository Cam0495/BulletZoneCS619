package edu.unh.cs.cs619_2015_project2.g10;

import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.androidannotations.annotations.Background;

import edu.unh.cs.cs619_2015_project2.g10.util.ShakeListener;
import edu.unh.cs.cs619_2015_project2.g10.util.TankService;

/**
 * Created by cam04_000 on 11/15/2015.
 */
public class GameController extends AsyncTask{

    static private final String TAG = "Game_Controller_Class";
    private TankService mTankService;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeListener mShakeDetector;

    private Context mContext;

    public GameController( Context context ){
        mContext = context;
    }

    private void addButtonLister(){
        // myListener = new ButtonListener( mTankService );
    }

    private void addShaker( ){
        mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor( Sensor.TYPE_ACCELEROMETER );
        mShakeDetector = new ShakeListener();
        mShakeDetector.setOnShakeListener(new ShakeListener.OnShakeListener() {
            @Override
            public void onShake(int count) {
                Log.d(TAG, "SHOOK");
                //fire( );
            }
        });
    }

    @Override
    protected Object doInBackground(Object[] params) {
        return true;
    }

    @Background
    public void moveUp(View view ){
        mTankService.move(mTankService.getOrientation(), (byte) 0);
        Log.d(TAG, "MOVE UP");
    }

    @Background
    public void moveDown( View view ){
        mTankService.move(mTankService.getOrientation(), (byte) 4);
        Log.d(TAG, "MOVE DOWN");
    }

    @Background
    public void turnLeft(View view ){

        mTankService.move(mTankService.getOrientation(), (byte) 6);
        Log.d(TAG, "TURN LEFT");
    }

    @Background
    public void turnRight(View view ){
        mTankService.move(mTankService.getOrientation(), (byte) 2);
        Log.d(TAG, "TURN RIGHT");
    }

    @Background
    public void fire( View view ){
        mTankService.fire();
        Log.d(TAG,"FIRED");
    }


}
