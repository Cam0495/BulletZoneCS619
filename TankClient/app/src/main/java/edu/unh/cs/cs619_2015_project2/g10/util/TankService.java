package edu.unh.cs.cs619_2015_project2.g10.util;

import edu.unh.cs.cs619_2015_project2.g10.rest.BulletZoneRestClient;


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


    public void move(byte currentState, byte requestedMove) {
        if (currentState == requestedMove) {
            mRestClient.move(tankID, mDirection);
        } else
            turn(currentState, requestedMove);
    }

    public void turn(byte currentState, byte requestedMove) {
        if (Math.abs((double) currentState - (double) requestedMove) != 4) {//Can complete request
            mDirection = requestedMove;
            mRestClient.turn(tankID, requestedMove);
        }
        else {//Tank is trying to turn around
            if( requestedMove == 0 ){
                mDirection = (byte) (2);
                mRestClient.turn(tankID, (byte) 2);
            }
            else if( requestedMove == 2){
                mDirection = (byte) (4);
                mRestClient.turn(tankID, (byte) 4);
            }
            else if( requestedMove == 4){
                mDirection = (byte) (6);
                mRestClient.turn(tankID, (byte) 6);
            }
            else {
                mDirection = (byte) 0;
                mRestClient.turn(tankID, (byte) 0);
            }
        }
    }


    public BooleanWrapper fire(  ){
        return mRestClient.fire(tankID);

    }

    public BooleanWrapper leave( ){
        return mRestClient.leave( tankID );
    }

    public byte getOrientation(){
        return mDirection;
    }
    public long getID(){
        return tankID;
    }
    public void setID(long id ){
        tankID = id;
    }
}

