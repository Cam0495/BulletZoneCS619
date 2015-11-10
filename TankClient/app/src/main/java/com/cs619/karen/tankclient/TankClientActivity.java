package com.cs619.karen.tankclient;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.cs619.karen.tankclient.rest.BulletZoneRestClient;
import com.cs619.karen.tankclient.rest.PollerTask;
import com.cs619.karen.tankclient.ui.GridAdapter;
import com.cs619.karen.tankclient.util.ButtonListener;
import com.cs619.karen.tankclient.util.ShakeListener;
import com.cs619.karen.tankclient.util.TankService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

@EActivity(R.layout.activity_main)
public class TankClientActivity extends AppCompatActivity {

    private static final String TAG = "TankClientActivity";


    @Bean
    protected GridAdapter mGridAdapter;

    @ViewById
    protected GridView gridView;

    @RestService
    BulletZoneRestClient restClient;

    @Bean
    PollerTask gridPollTask;

    private long tankId = -1;

    private TankService mTankService;

    private ButtonListener myListener;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeListener mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridView);
        mGridAdapter = new GridAdapter( TankClientActivity.this );
        displayGrid();
        addButtonLister();
        addShaker( );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @AfterViews
    protected void afterViewInjection() {
        joinAsync();
        SystemClock.sleep(500);
        displayGrid();
    }

    @Background
    void joinAsync() {
        try {
            tankId = restClient.join().getResult();
            mTankService = new TankService( restClient, tankId );
            Log.d(TAG, "tankId is " + tankId);
            gridPollTask.doPoll(); // start polling the server
        } catch (Exception e) {

        }
    }


    private void displayGrid( ){
        gridView.setAdapter(mGridAdapter);
        gridPollTask.addObserver(mGridAdapter);
    }

    private void addButtonLister(){
        myListener = new ButtonListener( mTankService );
    }

    private void addShaker( ){
        mSensorManager = (SensorManager) getSystemService( Context.SENSOR_SERVICE );
        mAccelerometer = mSensorManager.getDefaultSensor( Sensor.TYPE_ACCELEROMETER );
        mShakeDetector = new ShakeListener();
        mShakeDetector.setOnShakeListener(new ShakeListener.OnShakeListener() {
            @Override
            public void onShake(int count) {
                Log.d( TAG, "SHOOK");
                fire( );
            }
        });
    }

    @Background
    public void moveUp(View view ){
        boolean moved = mTankService.move( (byte) 0 ).isResult();
        Log.d(TAG, "MOVE UP");
        Log.d(TAG, "" + moved);
    }

    @Background
    public void moveDown( View view ){
        boolean moved = mTankService.move( (byte ) 4 ).isResult();
        Log.d(TAG, "MOVE DOWN");
        Log.d(TAG, "" + moved);
    }

    @Background
    public void turnLeft(View view ){
        boolean moved = mTankService.turn((byte) 6).isResult();
        Log.d(TAG, "TURN LEFT");
        Log.d(TAG, "" + moved);
    }

    @Background
    public void turnRight(View view ){
        boolean moved = mTankService.turn( (byte) 2 ).isResult();
        Log.d( TAG, "TURN RIGHT");
        Log.d( TAG, "" + moved );
    }

    @Background
    public void fire( View view ){
        boolean fired = mTankService.fire().isResult();
        Log.d(TAG,"FIRED");
        Log.d(TAG, "" + fired);
    }

    @Background
    public void fire(  ){
        boolean fired = mTankService.fire().isResult();
        Log.d(TAG,"FIRED");
        Log.d(TAG, "" + fired);
    }

}
