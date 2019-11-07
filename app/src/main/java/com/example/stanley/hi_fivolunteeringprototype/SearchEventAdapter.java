package com.example.stanley.hi_fivolunteeringprototype;

import android.app.usage.UsageEvents;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stanley.hi_fivolunteeringprototype.dummy.Content;

import java.util.ArrayList;
import java.util.List;


public class SearchEventAdapter extends BaseAdapter implements Filterable {
    private Context context; //context
    static public ArrayList<String> cat = new ArrayList<String>();


    ArrayList<VoluEvent> voluEvents = new ArrayList<VoluEvent>();
    ArrayList<VoluEvent> filteredVoluEvents;

    //public constructor
    public SearchEventAdapter(Context context) {
        for ( int i = 0; i < Content.TITLES.size(); i ++ ) {
            voluEvents.add(new VoluEvent(Content.TITLES.get(i),Content.DATES.get(i), Content.LOCATIONS.get(i), Content.DESCRIPTIONS.get(i), Content.PICTURES.get(i)));
        }
        filteredVoluEvents = voluEvents;
        this.context = context;
        cat.clear();
    }

    @Override
    public int getCount() {
        return filteredVoluEvents.size(); //returns total of items in the list
    }

    @Override
    public Object getItem(int position) {
        return filteredVoluEvents.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // inflate the layout for each list row
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.fragment_list, parent, false);
        }

        // get current item to be displayed
        VoluEvent currentItem = (VoluEvent) getItem(position);

        // get the TextView for item name and item description
        TextView textViewItemTitle = convertView.findViewById(R.id.item_number);
        textViewItemTitle.setText(currentItem.title);


        TextView TextViewContentView = convertView.findViewById(R.id.content);
        TextViewContentView.setText(currentItem.content);

        TextView mLocationView = convertView.findViewById(R.id.text_location);
        mLocationView.setText(currentItem.location);

        TextView mDateView = convertView.findViewById(R.id.text_date);
        mDateView.setText(currentItem.date);

        ImageView mImageView = convertView.findViewById(R.id.cardImage);
        ImageView mLocationIcon = convertView.findViewById(R.id.location_icon);
        ImageView mCalendarIcon = convertView.findViewById(R.id.calendar_icon);

        mImageView.setImageResource(currentItem.picture);
        mLocationIcon.setImageResource(R.drawable.location_icon);
        mCalendarIcon.setImageResource(R.drawable.calendar_icon);




        // returns the view for the current row
        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                filteredVoluEvents = (ArrayList<VoluEvent>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<VoluEvent> FilteredArrList = new ArrayList<VoluEvent>();

                if (voluEvents == null) {
                    voluEvents = new ArrayList<VoluEvent>(filteredVoluEvents); // saves the original data in mOriginalValues
                }

                /********
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********/
                if (constraint == null) {

                    // set the Original result to return
                    results.count = voluEvents.size();
                    results.values = voluEvents;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < voluEvents.size(); i++) {
                        String data = voluEvents.get(i).title;
                        String data2 = voluEvents.get(i).location;
                        String data3 = voluEvents.get(i).content;

                        if (constraint.length() == 0 || data.toLowerCase().contains(constraint.toString()) ||
                                data2.toLowerCase().contains(constraint.toString()) ||
                                data3.toLowerCase().contains(constraint.toString())) {
                            boolean b = true;
                            for (int j = 0; j < cat.size(); j++) {
                                if (!data3.toLowerCase().contains(cat.get(j).toLowerCase()))
                                    b = false;
                            }
                            if(b) FilteredArrList.add(voluEvents.get(i));

                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }

        };
        return filter;
    }
}


