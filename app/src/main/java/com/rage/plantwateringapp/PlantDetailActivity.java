package com.rage.plantwateringapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.TextView;

import org.joda.time.DateTime;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PlantDetailActivity extends AppCompatActivity {


    @Bind(R.id.plant_detail_page_details)
    protected TextView detailsText;
    @Bind(R.id.plant_detail_page_last_watered_date)
    protected TextView lastWateredDate;
    @Bind(R.id.plant_Detail_page_next_watering_date)
    protected TextView nextWateringDate;
    @Bind(R.id.plant_detail_page_number_days_watering)
    protected TextView numberOfDaysBetweenWateringText;

    public static final String ARG_PLANT = "plant_detail_fragment_plant";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_detail);
        ButterKnife.bind(this);

        Plant plant = getIntent().getParcelableExtra(ARG_PLANT);

        DateTime dt = new DateTime(plant.getDateLastWateredMilis());
        dt = dt.plusDays(plant.getNumDays());
        String lastWateringDate = DateUtils.formatDateTime(getApplicationContext(), plant.getDateLastWateredMilis(), DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
        String nextWatering = DateUtils.formatDateTime(getApplicationContext(), dt.getMillis(), DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL );

        detailsText.setText(plant.getDetails());
        numberOfDaysBetweenWateringText.setText(getString(R.string.number_of_days_between_watering_s, plant.getNumDays()));
        lastWateredDate.setText(getString(R.string.date_last_watered_s, lastWateringDate));
        nextWateringDate.setText(getString(R.string.date_of_next_watering_s, nextWatering));


    }
}
