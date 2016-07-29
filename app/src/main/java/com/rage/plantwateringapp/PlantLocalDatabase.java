package com.rage.plantwateringapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Local Database to hold plant data
 */

public class PlantLocalDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME = "plants.db";
    private static PlantLocalDatabase instance;

    public PlantLocalDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static PlantLocalDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new PlantLocalDatabase(context.getApplicationContext(), DB_NAME, null, 1);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO: Set up database when you are sure of the data to collect.
    }

    /**
     * No functionality for upgrading - if called, would delete existing table and call on create.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Plant.TABLE_NAME);
        onCreate(db);

    }
}
