package com.example.todolist;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * shopping list in the main activity
 */
public class ShoppingFragment extends Fragment {


    ListView lv;
    Context context;
/*arraylist to get data from database*/
    ArrayList<String> itemListFromDB=new ArrayList<>();
    ArrayList<String> quantityFromDB=new ArrayList<>();
    ArrayList<String> idFromDB=new ArrayList<>();

    EditText item;
    EditText quantity1;

    View v;
    public ShoppingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shopping, container, false);
        context=getContext();
        this.v=view;
        /*button to add listitem*/
        Button add = (Button)view.findViewById(R.id.add);
        item=(EditText)view.findViewById(R.id.item);
        quantity1=(EditText)view.findViewById(R.id.quantity);
        /*showing data from DB in shopping fragment in the list*/
        DBHelper dbH=new DBHelper(getContext());
        Cursor data=dbH.getItems();
        while(data.moveToNext()){
            idFromDB.add(data.getString(0));
            itemListFromDB.add(data.getString(1));
            quantityFromDB.add(data.getString(2));

        }
        lv=(ListView)v.findViewById(R.id.list);
        lv.setAdapter(new CustomAdaptor(getActivity(), itemListFromDB,quantityFromDB));
        /*add item to database on click of add button*/
        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        DBHelper dbH=new DBHelper(getContext());
                        boolean result=dbH.addItem(item.getText().toString(),quantity1.getText().toString());
                        if(result){
                            Toast.makeText(getActivity(),"Added successfully",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getActivity(),"failed",Toast.LENGTH_LONG).show();
                        }
                      updateList();
                    }
                }
        );
        lv=(ListView)v.findViewById(R.id.list);
        /*click list item and go to the change shppoing item activity*/
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updateList();
                Intent delete = new Intent(getContext() , DeleteItemShopping.class);
                delete.putExtra("item", itemListFromDB.get(position) );
                delete.putExtra("quantity" , quantityFromDB.get(position));
                delete.putExtra("id" , idFromDB.get(position).toString());
                startActivity(delete);
            }
        });



        return view;

    }
    public void updateList(){
        /*get list from database and show it on list*/
        DBHelper dbH=new DBHelper(getContext());
        Cursor data=dbH.getItems();
        while(data.moveToNext()){
            idFromDB.add(data.getString(0));
            itemListFromDB.add(data.getString(1));
            quantityFromDB.add(data.getString(2));
        }
        lv=(ListView)v.findViewById(R.id.list);
        lv.setAdapter(new CustomAdaptor(getActivity(), itemListFromDB,quantityFromDB));
    }

}
