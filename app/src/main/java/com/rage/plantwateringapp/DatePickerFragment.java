package com.rage.plantwateringapp;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Dialog Fragment for date picker to udpate date last watered.
 */
public class DatePickerFragment extends DialogFragment {


    @Bind(R.id.date_picker)
    protected DatePicker datePicker;

    public DatePickerFragment() {
        // Required empty public constructor
    }

    public static DatePickerFragment newInstance(){
        return new DatePickerFragment();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_date_picker, null);
        ButterKnife.bind(this, rootView);

        AlertDialog dialog = new AlertDialog.Builder(getActivity()).setView(rootView)
                .setTitle(R.string.select_date_last_watered)
                .setPositiveButton("Choose", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int day = datePicker.getDayOfMonth();
                        int month = datePicker.getMonth();
                        int year = datePicker.getYear();
                        Log.d("TAG", "date = : " + day + ", " + month + ", " + year);
                        ((AddPlantDialogFragment) getTargetFragment()).onDateRetrieved();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Do nothing
                    }
                })
                .create();

        return dialog;
    }


}
