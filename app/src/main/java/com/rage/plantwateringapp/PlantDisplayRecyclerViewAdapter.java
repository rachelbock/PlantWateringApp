package com.rage.plantwateringapp;

import android.support.annotation.BinderThread;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Adapter to display plant rows
 */
public class PlantDisplayRecyclerViewAdapter extends RecyclerView.Adapter<PlantDisplayRecyclerViewAdapter.PlantViewHolder>{

    protected List<Plant> plants;

    public PlantDisplayRecyclerViewAdapter(List<Plant> plants){
        this.plants = plants;
    }

    @Override
    public PlantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View plantView = inflater.inflate(R.layout.plant_display_row, parent, false);
        return new PlantViewHolder(plantView);
    }

    @Override
    public void onBindViewHolder(PlantViewHolder holder, int position) {
        Plant plant = plants.get(position);
        holder.nameTextView.setText(plant.getName());
        Integer days = plant.getNumDays();
        holder.daysWatering.setText(days.toString());

    }

    @Override
    public int getItemCount() {
        return plants.size();
    }

    public static class PlantViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.plant_detail_row_name_text)
        protected TextView nameTextView;

        @Bind(R.id.plant_detail_row_plant_image)
        protected ImageView plantImage;

        @Bind(R.id.plant_detail_row_watering_days)
        protected TextView daysWatering;

        public PlantViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
