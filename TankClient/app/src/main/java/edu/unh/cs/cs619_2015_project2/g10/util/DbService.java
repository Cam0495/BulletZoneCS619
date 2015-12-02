package edu.unh.cs.cs619_2015_project2.g10.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import org.androidannotations.annotations.rest.RestService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.unh.cs.cs619_2015_project2.g10.TankClientActivity;
import edu.unh.cs.cs619_2015_project2.g10.rest.BulletZoneRestClient;

/**
 * Created by BBryant12 on 12/1/15.
 */
public class DbService {


    private SQLiteDatabase database;
    private DbHelper dbHelper;
    private String[] allColumns = { dbHelper.COLUMN_ID, dbHelper.COLUMN_GRID };
    private TankClientActivity tank;

    DbService( TankClientActivity ta ){
        tank = ta;
    }


    /**
     * Creates connection with replay database.
     *
     * @throws SQLException
     */
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }


    public void insert( int [][] grid, Context context){
        DbHelper dbHelper = new DbHelper(context);

        try {
            // Serialize the input array
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(stream);
            objectStream.writeObject(grid);
            byte[] blob = stream.toByteArray();

            // Set up database for insert
            String sql = "INSERT INTO " + DbHelper.TABLE_NAME + " VAlUES(?,?);";
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            SQLiteStatement statement = db.compileStatement(sql);

            // Insert into database
            db.beginTransaction();
            statement.clearBindings();
            statement.bindLong(1, tank.getTime());
            statement.bindBlob(2, blob);
            statement.execute();
            db.setTransactionSuccessful();
            db.endTransaction();

        } catch( IOException e ){
            //Log.e(LOG_TAG, "IO EXCEPTION WRITING TO DB");
        }
    }

    /**
     * Returns every snapshot taken of the grid that was stored in the database.
     *
     * @return
     */
    public ArrayList<int[][]> getAllGrids() throws IOException, ClassNotFoundException {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<int[][]> grids = new ArrayList<int[][]>();
        Cursor cursor = database.query(dbHelper.TABLE_GRID, allColumns, null, null, null, null, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                byte[] blob = cursor.getBlob(1);
                ByteArrayInputStream bStream = new ByteArrayInputStream(blob);
                ObjectInputStream oStream = new ObjectInputStream(bStream);
                int[][] dSerialize = (int[][]) oStream.readObject();
                grids.add(dSerialize);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return grids;
    }
}
