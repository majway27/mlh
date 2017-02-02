package com.majway.mlh.rugrat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.majway.mlh.R;

import java.util.ArrayList;

public class CustomChildAdapter extends ArrayAdapter<Child> {
    public CustomChildAdapter(Context context, ArrayList<Child> children) {
        super(context, 0, children);
     }

     @Override
     public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Child child = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
           convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_child, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvId = (TextView) convertView.findViewById(R.id.tvID);
        TextView tvType = (TextView) convertView.findViewById(R.id.tvType);
        // Populate the data into the template view using the data object
        tvName.setText(child.getName());
        tvId.setText(Integer.toString(child.getId()));
        // Sorry about this
        if (child.getType() == "782") {
            tvType.setText("Child");
        } else {
            tvType.setText("Infant");
        }
        // Return the completed view to render on screen
        return convertView;

    }

    public void mlh_notify() {
        notifyDataSetChanged();
    }

}
