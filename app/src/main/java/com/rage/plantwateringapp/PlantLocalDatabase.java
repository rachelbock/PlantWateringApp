package com.rage.plantwateringapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Database setup with one table containing auto-incremented id, plant name, number of days
     * between watering and plant details.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Plant.TABLE_NAME + " (" +
                Plant.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Plant.COL_NAME + " TEXT, " +
                Plant.COL_NUM_DAYS + " INT, " +
                Plant.COL_DETAILS + " TEXT)"
        );

    }

    /**
     * No functionality for upgrading - if called, would delete existing table and call on create.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Plant.TABLE_NAME);
        onCreate(db);

    }

    public void addPlant(Plant plant) {
        getWritableDatabase().insert(Plant.TABLE_NAME, null, plant.getContentValues());
    }

    public void removePlant(int plantId){
        Integer id = plantId;
        String stringId = id.toString();
        String query = Plant.COL_ID + " = ? ";
        getWritableDatabase().delete(Plant.TABLE_NAME, query, new String[]{stringId});
    }


    public String getCursorString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public int getCursorInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public List<Plant> getPlants() {

        List<Plant> plants = new ArrayList<>();
        String query = "SELECT * FROM " + Plant.TABLE_NAME;
        Cursor cursor = getReadableDatabase().rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String name = getCursorString(cursor, Plant.COL_NAME);
                String details = getCursorString(cursor, Plant.COL_DETAILS);
                int numDays = getCursorInt(cursor, Plant.COL_NUM_DAYS);
                Plant plant = new Plant(name, numDays, details);
                plants.add(plant);
            }
            while(cursor.moveToNext());
        }
        cursor.close();

        return plants;
    }

}
