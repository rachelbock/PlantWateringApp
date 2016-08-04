package com.rage.plantwateringapp;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Dialog Fragment launched from FAB to collect new plant data.
 */
public class AddPlantDialogFragment extends DialogFragment {

    @Bind(R.id.add_plant_plant_name)
    protected EditText plantName;
    @Bind(R.id.add_plant_number_of_days)
    protected EditText numDaysString;
    @Bind(R.id.add_plant_additional_instructions)
    protected EditText careInstructions;
    private PlantCreatedListener listener;


    public AddPlantDialogFragment() {
        // Required empty public constructor
    }

    public interface PlantCreatedListener {
        void onPlantCreated(Plant plant);

    }


    public static AddPlantDialogFragment newInstance() {
        return new AddPlantDialogFragment();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_plant_dialog, null);
        ButterKnife.bind(this, rootView);
        listener = (PlantCreatedListener) getActivity();

        AlertDialog dialog = new AlertDialog.Builder(getActivity()).setView(rootView)
                .setTitle("Add New Plant Data:")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = plantName.getText().toString();
                        int days = Integer.parseInt(numDaysString.getText().toString());
                        String details = careInstructions.getText().toString();
                        Plant plant = new Plant(name, days, details);
                        listener.onPlantCreated(plant);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Do nothing
                    }
                })
                .create();

        return dialog;
    }


    @OnClick(R.id.add_plant_date_last_watered_button)
    public void onDateClickedButton() {
        DatePickerFragment dialogFragment = DatePickerFragment.newInstance();
        dialogFragment.setTargetFragment(this, 0);
        dialogFragment.show(getFragmentManager(), "dialog");
    }

    public void onDateRetrieved() {

    }
}
