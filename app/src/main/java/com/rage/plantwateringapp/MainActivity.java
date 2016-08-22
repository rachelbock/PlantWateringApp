package com.rage.plantwateringapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements AddPlantDialogFragment.PlantCreatedListener, PlantDisplayRecyclerViewAdapter.RowClickListener, PlantDisplayRecyclerViewAdapter.WateredCheckboxListener{

    public static final String TAG = MainActivity.class.getSimpleName();
    private PlantLocalDatabase plantLocalDatabase;

    @Bind(R.id.main_activity_recycler_view)
    protected RecyclerView recyclerView;
    protected PlantDisplayRecyclerViewAdapter adapter;
    protected List<Plant> plants;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        plantLocalDatabase = PlantLocalDatabase.getInstance(getApplicationContext());
        plants = plantLocalDatabase.getPlants();


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PlantDisplayRecyclerViewAdapter(plants, this, this);
        recyclerView.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AddPlantDialogFragment dialogFragment = AddPlantDialogFragment.newInstance();
                dialogFragment.show(getFragmentManager(), "dialog");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPlantCreated(Plant plant) {
        plantLocalDatabase.addPlant(plant);
        plants.add(plant);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onPlantRowClicked(Plant plant) {
        Intent intent = new Intent(MainActivity.this, PlantDetailActivity.class);
        intent.putExtra(PlantDetailActivity.ARG_PLANT, plant);
        startActivity(intent);
    }

    @Override
    public void onWaterCheckboxChecked(Plant plant) {
        plantLocalDatabase.updateDateLastWatered(plant);

    }
}
