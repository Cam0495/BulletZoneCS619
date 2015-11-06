package com.cs619.karen.tankclient.util;

import com.cs619.karen.tankclient.rest.BulletZoneRestClient;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by cdevine on 10/30/2015.
 */
public class TankService{

    long tankID;
    BulletZoneRestClient mRestClient;

    public TankService( BulletZoneRestClient restClient, long tankid ){
        tankID = tankid;
        mRestClient = restClient;
    }

    public BooleanWrapper move( byte direction ){
        return mRestClient.move( tankID, direction );
    }

    public BooleanWrapper turn( byte direction ){
        return mRestClient.turn(tankID, direction);
    }

    public BooleanWrapper fire(  ){
        return mRestClient.fire( tankID );
    }

    public BooleanWrapper leave( ){
        return mRestClient.leave( tankID );
    }

}
