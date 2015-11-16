package edu.unh.cs.cs619_2015_project2.g10.util;

import edu.unh.cs.cs619_2015_project2.g10.rest.BulletZoneRestClient;


/**
 * Created by cdevine on 10/30/2015.
 *
 * Does the work for controlling the Tank.
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

    /**
     * Moves the tank. If the tank isn't facing that direction
     * it will turn the tank first, then move.
     *
     * @param currentState
     * @param requestedMove
     */
    public void move(byte currentState, byte requestedMove) {
        if (currentState == requestedMove) {
            try {
                mRestClient.move(tankID, mDirection);
            }catch( Exception e ){

            }
        } else
            turn(currentState, requestedMove);
    }

    /**
     * This turns the tank.
     *
     * @param currentState
     * @param requestedMove
     */
    public void turn(byte currentState, byte requestedMove) {
        if (Math.abs((double) currentState - (double) requestedMove) != 4) {//Can complete request
            mDirection = requestedMove;
            try {
                mRestClient.turn(tankID, requestedMove);
            }catch( Exception e ){

            }
        }
        else {//Tank is trying to turn around
            if( requestedMove == 0 ){
                mDirection = (byte) (2);
                try {
                    mRestClient.turn(tankID, (byte) 2);
                }catch ( Exception e ){

                }
            }
            else if( requestedMove == 2){
                mDirection = (byte) (4);
                try {
                    mRestClient.turn(tankID, (byte) 4);
                }catch( Exception e ){

                }
            }
            else if( requestedMove == 4){
                mDirection = (byte) (6);
                try {
                    mRestClient.turn(tankID, (byte) 6);
                }catch( Exception e ){

                }
            }
            else {
                mDirection = (byte) 0;
                try {
                    mRestClient.turn(tankID, (byte) 0);
                }catch( Exception e ){

                }
            }
        }
    }


    public void fire(  ){
        try {
            mRestClient.fire(tankID);
        }catch ( Exception e ){

        }

    }

    /**
     * Leave the game.
     * @return bool
     */
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

