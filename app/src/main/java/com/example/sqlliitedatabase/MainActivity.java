package com.example.sqlliitedatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mdb;
    EditText txtId,txtName,txtLname,txtMarks;
    Button btnInsert,btnShow,btnUpdate,btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean inserted = mdb.insertData(txtName.getText().toString(),
                        txtLname.getText().toString(),txtMarks.getText().toString());
                if(inserted==true)
                {
                    Toast.makeText(MainActivity.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Data is not inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cur = mdb.getAllData();
                if(cur.getCount()==0)
                {
                    Toast.makeText(MainActivity.this, "Data is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(cur.moveToNext())
                {
                    buffer.append("ID "+cur.getString(0)+"\n");
                    buffer.append("name "+cur.getString(1)+"\n");
                    buffer.append("lastname "+cur.getString(2)+"\n");
                    buffer.append("marks "+cur.getString(3)+"\n");
                }
                show("Data",buffer.toString());
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean updated = mdb.updateData(txtId.getText().toString(),txtName.getText().toString(),
                        txtLname.getText().toString(),txtMarks.getText().toString());
                if(updated==true)
                {
                    Toast.makeText(MainActivity.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Data is not updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deleterow= mdb.deleteData(txtId.getText().toString());
                if(deleterow>0)
                {
                    Toast.makeText(MainActivity.this, "Data deleted successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Data is not deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void show(String title,String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void init()
    {
        mdb= new DatabaseHelper(this);
        txtId=(EditText)findViewById(R.id.std_id);
        txtName=(EditText)findViewById(R.id.std_name);
        txtLname=(EditText)findViewById(R.id.std_lname);
        txtMarks=(EditText) findViewById(R.id.std_marks);
        btnInsert=(Button)findViewById(R.id.btn_insert);
        btnShow=(Button)findViewById(R.id.btn_view);
        btnUpdate=(Button)findViewById(R.id.btn_update);
        btnDelete=(Button)findViewById(R.id.btn_delete);
    }
}