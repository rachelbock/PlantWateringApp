package com.rage.plantwateringapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import org.joda.time.DateTime;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Adapter to display plant rows
 */
public class PlantDisplayRecyclerViewAdapter extends RecyclerView.Adapter<PlantDisplayRecyclerViewAdapter.PlantViewHolder>{

    protected List<Plant> plants;
    protected RowClickListener rowClickListener;
    protected WateredCheckboxListener wateredCheckboxListener;

    public PlantDisplayRecyclerViewAdapter(List<Plant> plants, RowClickListener rowClickListener, WateredCheckboxListener wateredCheckboxListener ){
        this.plants = plants;
        this.rowClickListener = rowClickListener;
        this.wateredCheckboxListener = wateredCheckboxListener;
    }


    public interface WateredCheckboxListener {
        void onWaterCheckboxChecked(Plant plant);
    }

    public interface RowClickListener {
        void onPlantRowClicked(Plant plant);
    }

    @Override
    public PlantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View plantView = inflater.inflate(R.layout.plant_display_row, parent, false);

        return new PlantViewHolder(plantView);
    }

    @Override
    public void onBindViewHolder(final PlantViewHolder holder, int position) {
        final Plant plant = plants.get(position);
        final Context context = holder.dateLastWatered.getContext();

        long lastWateredMilis = plant.getDateLastWateredMilis();

        holder.nameTextView.setText(plant.getName());
        Integer days = plant.getNumDays();
        String lastWateringDate = DateUtils.formatDateTime(context, lastWateredMilis, DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
        holder.dateLastWatered.setText(lastWateringDate);
        DateTime dt = new DateTime(plant.getDateLastWateredMilis());
        dt = dt.plusDays(days);
        String nextWatering = DateUtils.formatDateTime(context, dt.getMillis(), DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL );
        holder.nextWateringDate.setText(nextWatering);

        holder.wateredCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wateredCheckboxListener != null) {
                    wateredCheckboxListener.onWaterCheckboxChecked(plants.get(holder.getAdapterPosition()));
                }
            }
        });

        holder.fullView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rowClickListener != null) {
                    rowClickListener.onPlantRowClicked(plants.get(holder.getAdapterPosition()));
                }
            }
        });

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

        @Bind(R.id.plant_detail_row_date_last_watered)
        protected TextView dateLastWatered;

        @Bind(R.id.plant_detail_row_next_watering_date)
        protected TextView nextWateringDate;

        @Bind(R.id.plant_detail_row_watered_checkbox)
        protected CheckBox wateredCheckbox;

        protected View fullView;

        public PlantViewHolder(View itemView) {
            super(itemView);
            fullView = itemView;
            ButterKnife.bind(this, itemView);

        }
    }
}
