package edu.unh.cs.cs619_2015_project2.g10.rest;

/**
 * Created by karenjin on 10/21/15.
 */

import android.os.SystemClock;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.rest.RestService;

import android.util.Log;

import edu.unh.cs.cs619_2015_project2.g10.util.GridWrapper;

import java.util.Observable;

@EBean
public class PollerTask extends Observable{
    private static final String TAG = "GridPollerTask";

    @RestService
    BulletZoneRestClient restClient;

    @Background(id = "grid_poller_task")
    public void doPoll() {
        while (true) {
            onGridUpdate(restClient.grid());
            // poll server every 100ms
            SystemClock.sleep(100);
        }
    }

    @UiThread
    public void onGridUpdate(GridWrapper gw) {
        Log.d(TAG, "grid at timestamp: " + gw.getTimeStamp());
        setChanged( );
        notifyObservers( gw.getGrid() );
        clearChanged();
        //busProvider.getEventBus().post(new GridUpdateEvent(gw));
    }



}
