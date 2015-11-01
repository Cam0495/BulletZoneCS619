package com.cs619.karen.tankclient.rest;

/**
 * Created by karenjin on 10/21/15.
 */

import android.os.SystemClock;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.rest.RestService;

import android.os.SystemClock;
import android.util.Log;

import com.cs619.karen.tankclient.util.GridWrapper;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.rest.RestService;

@EBean
public class PollerTask {
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

        //busProvider.getEventBus().post(new GridUpdateEvent(gw));
    }

    public GridWrapper getGridWrapper( ){
        return restClient.grid();
    }
}
