package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
//update and delete item from shopping list
public class DeleteItemShopping extends AppCompatActivity {

    EditText item;
    EditText quantity;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_item_shopping);
        /*item and quantity about shopping in edittext*/
        item=(EditText)findViewById(R.id.item);
        quantity=(EditText)findViewById(R.id.quantity);
        /*get data from the intent to show in the above text boxes*/
        String item1 = getIntent().getStringExtra("item");

        String quantity1 = getIntent().getStringExtra("quantity");
        String str_id = getIntent().getStringExtra("id");

        this.id = Integer.parseInt(str_id);

        // Integer pos1 = getIntent().getIntExtra("integer" , 1);
        item.setText(item1);
        quantity.setText(quantity1);
    }

    public void ClickDelete(View v)
    {
        /*delete item from shopping list(database) and redirect to main activity*/
        DBHelper dbH = new DBHelper(this);

        dbH.deleteItem(id);

        Toast.makeText(this, "Deleted Successfully" , Toast.LENGTH_SHORT).show();

        Intent mainPageIntent = new Intent(this, MainActivity.class);

        startActivity(mainPageIntent);
    }
    public void ClickUpdate(View v)
    {
        /*get changed details item and quantity from textbox and update the database*/
        item=(EditText)findViewById(R.id.item);
        quantity=(EditText)findViewById(R.id.quantity);

        if (item.getText().toString() != "" &&quantity.getText().toString()!="" ) {
            DBHelper dbH = new DBHelper(this);

            boolean up=dbH.updateItem(item.getText().toString(),quantity.getText().toString(),id);

            if (up) {
                Toast.makeText(this, "Updated successfully ", Toast.LENGTH_LONG).show();
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
