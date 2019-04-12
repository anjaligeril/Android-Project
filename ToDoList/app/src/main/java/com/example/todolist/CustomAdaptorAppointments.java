package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdaptorAppointments extends BaseAdapter {

   //base adaptor for the appointments
    ArrayList<String> detailsList1 = new ArrayList<String>();
    ArrayList<String> dateList1 = new ArrayList<String>();
    ArrayList<String> timeList1 = new ArrayList<String>();
    Context context;

    private static LayoutInflater inflater = null;

    public CustomAdaptorAppointments(Context context, ArrayList detailsList, ArrayList dateList,ArrayList timeList) {

        detailsList1 = detailsList;
        context = context;

        dateList1 = dateList;
        timeList1=timeList;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return detailsList1.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    private class Holder {
        TextView tv;
        ImageView img;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        View rowView;
        rowView = inflater.inflate(R.layout.activity_item_appointment, null);
        TextView tv = (TextView) rowView.findViewById(R.id.item);
        TextView tv1 = (TextView) rowView.findViewById(R.id.txtDate);
        TextView tv2 = (TextView) rowView.findViewById(R.id.time);
        tv.setText(detailsList1.get(position));
        tv1.setText(dateList1.get(position));
        tv2.setText(timeList1.get(position));

        return rowView;
    }
}