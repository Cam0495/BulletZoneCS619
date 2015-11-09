package com.cs619.karen.tankclient;

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

    protected Button up_button,down_button,left_button, right_button;

    @RestService
    BulletZoneRestClient restClient;

    @Bean
    PollerTask gridPollTask;

    private long tankId = -1;

    private TankService mTankService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridView);
        mGridAdapter = new GridAdapter( TankClientActivity.this );
        up_button = (Button) findViewById( R.id.up_button );
        up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d( TAG, "MOVE UP");
                mTankService.move((byte) 2);
            }
        });
        displayGrid();
        //addButtonLister();
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
        gridPollTask.addObserver( mGridAdapter );
    }

    /*private void addButtonLister(){

        up_button = (Button) findViewById(R.id.up_button);
        down_button = (Button) findViewById(R.id.down_button);
        right_button = (Button) findViewById(R.id.right_button);
        left_button = (Button) findViewById(R.id.left_button);

        up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Button Clicked", "Up Button");
                mTankService.move( (byte)0 );
            }
        });

        down_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Button Clicked", "Down Button");
            }
        });

        right_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Button Clicked", "Right Button");
            }
        });

        left_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Button Clicked", "Left Button");
            }
        });
    }*/

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
        Log.d( TAG, "TURN LEFT");
        Log.d( TAG, "" + moved );
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
        Log.d(TAG, "" + fired );
    }
}
