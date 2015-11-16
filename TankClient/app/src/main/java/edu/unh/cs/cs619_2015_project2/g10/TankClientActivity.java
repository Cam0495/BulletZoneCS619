package edu.unh.cs.cs619_2015_project2.g10;

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
import android.widget.GridView;

import edu.unh.cs.cs619_2015_project2.g10.rest.BulletZoneRestClient;
import edu.unh.cs.cs619_2015_project2.g10.rest.PollerTask;
import edu.unh.cs.cs619_2015_project2.g10.ui.GridAdapter;
import edu.unh.cs.cs619_2015_project2.g10.util.ShakeListener;
import edu.unh.cs.cs619_2015_project2.g10.util.TankService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

@EActivity(edu.unh.cs.cs619_2015_project2.g10.R.layout.activity_main)
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

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeListener mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(edu.unh.cs.cs619_2015_project2.g10.R.layout.activity_main);

        gridView = (GridView) findViewById(edu.unh.cs.cs619_2015_project2.g10.R.id.gridView);
        mGridAdapter = new GridAdapter( TankClientActivity.this );
        displayGrid();
        addShaker();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(edu.unh.cs.cs619_2015_project2.g10.R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == edu.unh.cs.cs619_2015_project2.g10.R.id.action_settings) {
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
       // myListener = new ButtonListener( mTankService );
    }

    private void addShaker( ){
        mSensorManager = (SensorManager) getSystemService( Context.SENSOR_SERVICE );
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
