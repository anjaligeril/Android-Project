package com.example.todolist;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
//update and delete item from list
public class ChangeAppointment extends AppCompatActivity {
    Button btnDatePicker, btnTimePicker, add;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    View v;
    ListView lv;

    EditText details;


    int id;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_appointment);
        /*date time and details about appointments in edittext*/
        txtDate=(EditText)findViewById(R.id.txtDate);
        txtTime=(EditText)findViewById(R.id.txtTime);
        details=(EditText)findViewById(R.id.item);
        /*get data from the intent to show in the above text boxes*/
        String details1 = getIntent().getStringExtra("details");
        String date1 = getIntent().getStringExtra("date");
        String time1=getIntent().getStringExtra("time");
        String str_id = getIntent().getStringExtra("id");
        this.id = Integer.parseInt(str_id);
        details.setText(details1);
        txtDate.setText(date1);
        txtTime.setText(time1);

    }

    public void changeDate(View v){
        /*function to change date using date picker*/
        txtDate=(EditText)findViewById(R.id.txtDate);
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        /*get current calender date and set on the date picker*/
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        //set new date
                        txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();


    }

    public void changeTime(View v){
        /*function to change time using date picker*/
        txtTime=(EditText)findViewById(R.id.txtTime);
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog with current time and set time
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        txtTime.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    public void deleteAppointment(View v){

        /*delete appointment from database and redirect to main activity*/
        DBHelper1 dbH = new DBHelper1(this);

        dbH.deleteAppointment(id);

        Toast.makeText(this, "Successfully deleted" , Toast.LENGTH_SHORT).show();

        Intent mainPageIntent = new Intent(this, MainActivity.class);

        startActivity(mainPageIntent);
    }

    public void updateAppointments(View v){
        /*get changed details date and time from textbox and update the database*/
        details=(EditText)findViewById(R.id.item);
        txtDate=(EditText)findViewById(R.id.txtDate);
        txtTime=(EditText)findViewById(R.id.txtTime);
        if (details.getText().toString() != "" &&txtDate.getText().toString()!="" && txtTime.getText().toString()!="" ) {
            DBHelper1 dbH = new DBHelper1(this);

            boolean up=dbH.updateAppointment(details.getText().toString(),txtDate.getText().toString(),txtTime.getText().toString(),id);

            if (up) {
                Toast.makeText(this, "updated successfully ", Toast.LENGTH_LONG).show();
                Intent mainPageIntent = new Intent(this, MainActivity.class);
                startActivity(mainPageIntent);

            } else {
                Toast.makeText(this, "failed ", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "failed ", Toast.LENGTH_LONG).show();
        }

        Intent mainPageIntent = new Intent(this, MainActivity.class);

        startActivity(mainPageIntent);
    }

}
