package com.rage.plantwateringapp;

import android.content.ContentValues;
import android.provider.BaseColumns;

/**
 * Contains data on the plant object
 */

public class Plant implements BaseColumns{

    private int id;
    private String name;
    private int numDays;
    private String details;

    public static final String COL_ID = "plant_id";
    public static final String COL_NAME = "plant_name";
    public static final String COL_NUM_DAYS = "num_days";
    public static final String COL_DETAILS = "plant_details";
    public static final String TABLE_NAME = "plants_table";

    public Plant(String name, int numDays, String details) {
        this.name = name;
        this.numDays = numDays;
        this.details = details;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumDays() {
        return numDays;
    }

    public void setNumDays(int numDays) {
        this.numDays = numDays;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_NUM_DAYS, numDays);
        contentValues.put(COL_DETAILS, details);
        return contentValues;
    }

}
