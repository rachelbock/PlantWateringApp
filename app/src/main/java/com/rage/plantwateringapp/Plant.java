package com.rage.plantwateringapp;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

/**
 * Contains data on the plant object
 */

public class Plant implements BaseColumns, Parcelable{

    private int id;
    private String name;
    private int numDays;
    private String details;
    private long dateLastWateredMilis;

    public static final String COL_ID = "plant_id";
    public static final String COL_NAME = "plant_name";
    public static final String COL_NUM_DAYS = "num_days";
    public static final String COL_DETAILS = "plant_details";
    public static final String COL_DATE_LAST_WATERED = "date_last_watered";
    public static final String TABLE_NAME = "plants_table";

    public Plant(String name, int numDays, String details, long dateLastWateredMilis) {
        this.name = name;
        this.numDays = numDays;
        this.details = details;
        this.dateLastWateredMilis = dateLastWateredMilis;
    }

    protected Plant(Parcel in) {
        id = in.readInt();
        name = in.readString();
        numDays = in.readInt();
        details = in.readString();
        dateLastWateredMilis = in.readLong();
    }

    public static final Creator<Plant> CREATOR = new Creator<Plant>() {
        @Override
        public Plant createFromParcel(Parcel in) {
            return new Plant(in);
        }

        @Override
        public Plant[] newArray(int size) {
            return new Plant[size];
        }
    };

    public long getDateLastWateredMilis() {
        return dateLastWateredMilis;
    }

    public void setDateLastWateredMilis(long dateLastWateredMilis) {
        this.dateLastWateredMilis = dateLastWateredMilis;
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
        contentValues.put(COL_DATE_LAST_WATERED, dateLastWateredMilis);
        return contentValues;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(numDays);
        dest.writeString(details);
        dest.writeLong(dateLastWateredMilis);
    }
}
