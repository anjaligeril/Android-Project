package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdaptor extends BaseAdapter {


    //base adaptor for shopping list
    ArrayList<String> itemList1 = new ArrayList<String>();
    ArrayList<String> quantity1 = new ArrayList<String>();
    Context context;

    private static LayoutInflater inflater=null;

    public CustomAdaptor(Context context, ArrayList itemList,  ArrayList quantity) {

        itemList1=itemList;
        context=context;

        quantity1=quantity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return itemList1.size();
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

    private class Holder
    {
        TextView tv;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        View rowView;
        /*show the shopping list item in the item_shopping activity */
        rowView = inflater.inflate(R.layout.activity_item_shopping, null);
        TextView tv=(TextView) rowView.findViewById(R.id.item);
        TextView tv1=(TextView) rowView.findViewById(R.id.quantity);

        tv.setText(itemList1.get(position));
        tv1.setText(quantity1.get(position));

        return rowView;
    }
}