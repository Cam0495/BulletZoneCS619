package com.cs619.karen.tankclient.ui;

import com.cs619.karen.tankclient.util.GridWrapper;

/**
 * Created by cdevine on 10/30/2015.
 */
public class GridUI {

    GridWrapper gridWrapper;
    int[][] mGrid;
    GameEntityUI[][] gameEntities;

    public GridUI( ){
        gridWrapper = null;
        mGrid = null;
        gameEntities = null;
    }

    public GridUI( GridWrapper gw ){
        gridWrapper = gw;
        mGrid = gw.getGrid();
        gameEntities = setGridUI( mGrid );
    }

    private GameEntityUI[][] setGridUI( int[][] grid ){
        return gameEntities;
    }

    public int[][] getGrid( ){
        return mGrid;
    }
}
