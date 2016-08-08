package com.rage.plantwateringapp;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Fragment launched on plant row clicked to display additional plant details.
 */
public class PlantDetailFragment extends Fragment {


    @Bind(R.id.plant_detail_page_details)
    protected TextView detailsText;
    @Bind(R.id.plant_detail_page_last_watered_date)
    protected TextView lastWateredDate;
    @Bind(R.id.plant_Detail_page_next_watering_date)
    protected TextView nextWateringDate;
    @Bind(R.id.plant_detail_page_number_days_watering)
    protected TextView numberOfDaysBetweenWateringText;

    public static final String ARG_PLANT = "plant_detail_fragment_plant";

    public PlantDetailFragment() {
        // Required empty public constructor
    }

    public static PlantDetailFragment newInstance(Plant plant) {
        Bundle args = new Bundle();
        PlantDetailFragment fragment = new PlantDetailFragment();
        args.putParcelable(ARG_PLANT, plant);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_plant_detail, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

}
