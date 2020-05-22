package com.yasharth.covid_19tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Details> {

    //list that will be displayed
    private List<Details> DetailsList;

    //context object
    private Context context;

    public ListViewAdapter(List<Details> DetailsList, Context context){
        super(context, R.layout.list_items, DetailsList);
        this.DetailsList = DetailsList;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //getting layout inflator
        LayoutInflater inflater = LayoutInflater.from(context);

        View ListViewItem = inflater.inflate(R.layout.list_items, null, true);

        TextView stateView = ListViewItem.findViewById(R.id.record_regionData);
        TextView infectedText = ListViewItem.findViewById(R.id.record_Infected);
        TextView recoveredText = ListViewItem.findViewById(R.id.record_Recovered);
        TextView deceasedText = ListViewItem.findViewById(R.id.record_Deceased);

        Details details = DetailsList.get(position);

        //settingValues
        stateView.setText(details.getRegion());
        infectedText.setText(details.getTotalInfected());
        recoveredText.setText(details.getRecovered());
        deceasedText.setText(details.getDeceased());

        return ListViewItem;
    }

}
