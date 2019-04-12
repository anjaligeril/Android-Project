package com.example.todolist;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;


/**
 * Appointments list in the main activity
 */
public class AppointmentsFragment extends Fragment {


    public AppointmentsFragment() {
        // Required empty public constructor
    }
    Button btnDatePicker, btnTimePicker, add;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    View v;
    ListView lv;

    EditText details;
    EditText date;
    EditText time;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_appointments, container, false);
        this.v=view;
        /*button for date picker and time picker and add to list*/
        btnDatePicker=(Button)view.findViewById(R.id.btnDatePicker);
        btnTimePicker=(Button)view.findViewById(R.id.btnTimePicker);
        add=(Button)view.findViewById(R.id.add);
        /*date and time textbox*/
        txtDate=(EditText)view.findViewById(R.id.txtDate);
        txtTime=(EditText)view.findViewById(R.id.txtTime);
        /*array list for data from DB*/
       final ArrayList<String> detailsFromDB = new ArrayList<String>();
        final ArrayList<String> timeFromDB = new ArrayList<String>();
      final ArrayList<String> dateFromDB = new ArrayList<String>();
       final ArrayList<String> idFromDB = new ArrayList<String>();
       /*showing data from DB in appointments fragments in the list*/
        DBHelper1 dbH1=new DBHelper1(getContext());
        Cursor data=dbH1.getAppointment();
        while(data.moveToNext()){
            detailsFromDB.add(data.getString(1));
            dateFromDB.add(data.getString(2));
            timeFromDB.add(data.getString(3));
            idFromDB.add(data.getString(0));
        }
        lv=(ListView)v.findViewById(R.id.list);
        lv.setAdapter(new CustomAdaptorAppointments(getActivity(), detailsFromDB,dateFromDB,timeFromDB));

       /*set date from date picker*/
        btnDatePicker.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Calendar c = Calendar.getInstance();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);

/*get current calender date and set on the date picker*/
                        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                                new DatePickerDialog.OnDateSetListener() {
//set the date
                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int monthOfYear, int dayOfMonth) {
                                        txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                    }
                                }, mYear, mMonth, mDay);
                        datePickerDialog.show();

                    }
                }
        );
        /*set time from time picker*/
        btnTimePicker.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Calendar c = Calendar.getInstance();
                        mHour = c.get(Calendar.HOUR_OF_DAY);
                        mMinute = c.get(Calendar.MINUTE);

                        // Launch Time Picker Dialog with current time and set time
                        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                                new TimePickerDialog.OnTimeSetListener() {

                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay,
                                                          int minute) {

                                        txtTime.setText(hourOfDay + ":" + minute);
                                    }
                                }, mHour, mMinute, false);
                        timePickerDialog.show();

                    }
                }
        );
        /*get data from textbox and add it to list*/
        details=(EditText)v.findViewById(R.id.item);
        date=(EditText)v.findViewById(R.id.txtDate);
        time=(EditText)v.findViewById(R.id.txtTime);
        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DBHelper1 dbH1=new DBHelper1(getContext());
                        boolean result=dbH1.addAppointment(details.getText().toString(),date.getText().toString(),time.getText().toString());
                       if(result){
                            Toast.makeText(getActivity()," Added successfully",Toast.LENGTH_LONG).show();
                       }else{
                            Toast.makeText(getActivity(),"failed",Toast.LENGTH_LONG).show();

                       }
                        updateList();


                    }
                }
        );

        /*click list item and go to the change appointments activity*/
        lv=(ListView)v.findViewById(R.id.list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updateList();
                Intent delete = new Intent(getContext(),ChangeAppointment.class);
                delete.putExtra("details", detailsFromDB.get(position) );
                delete.putExtra("date" , dateFromDB.get(position));
                delete.putExtra("time" , timeFromDB.get(position));
                delete.putExtra("id" , idFromDB.get(position).toString());
                startActivity(delete);
            }
        });

        return view;

    }

    public void updateList(){
        /*get list from database and show it on list*/
        ArrayList<String> detailsFromDB = new ArrayList<String>();
        ArrayList<String> timeFromDB = new ArrayList<String>();
        ArrayList<String> dateFromDB = new ArrayList<String>();
        ArrayList<String> idFromDB = new ArrayList<String>();
        DBHelper1 dbH1=new DBHelper1(getContext());
        Cursor data=dbH1.getAppointment();
        while(data.moveToNext()){
            detailsFromDB.add(data.getString(1));
            dateFromDB.add(data.getString(2));
            timeFromDB.add(data.getString(3));
            idFromDB.add(data.getString(0));
        }
        lv=(ListView)v.findViewById(R.id.list);
        lv.setAdapter(new CustomAdaptorAppointments(getActivity(), detailsFromDB,dateFromDB,timeFromDB));
    }
}
