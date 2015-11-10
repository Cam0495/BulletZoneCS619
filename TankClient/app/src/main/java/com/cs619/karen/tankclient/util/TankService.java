package com.cs619.karen.tankclient.util;

import com.cs619.karen.tankclient.rest.BulletZoneRestClient;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by cdevine on 10/30/2015.
 */
public class TankService{

    long tankID;
    byte mDirection;
    BulletZoneRestClient mRestClient;

    public TankService( BulletZoneRestClient restClient, long tankid ){
        tankID = tankid;
        mDirection = 0;
        mRestClient = restClient;
    }

    public BooleanWrapper move( byte direction ){
        if( direction == (byte)0 ) {
            return mRestClient.move(tankID, mDirection);
        }
        else {
            return mRestClient.move(tankID, direction);
        }
    }

    public BooleanWrapper turn( byte direction ){
        if( direction == (byte)2 ) {
            mDirection = (byte)(direction + 2);
            if( mDirection == 8 )
                mDirection = 0;
            return mRestClient.turn(tankID, mDirection);
        }
        else {
            mDirection = (byte)(direction - 2);
            if( mDirection == -2 )
                mDirection = 6;
            return mRestClient.turn(tankID, mDirection);
        }
    }

    public BooleanWrapper fire(  ){
        return mRestClient.fire( tankID );
    }

    public BooleanWrapper leave( ){
        return mRestClient.leave( tankID );
    }

}
