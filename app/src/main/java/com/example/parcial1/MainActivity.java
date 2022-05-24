package com.example.parcial1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner spinner_weight = findViewById(R.id.spinner_weight);
        ArrayAdapter<CharSequence> adapter_weight = ArrayAdapter.createFromResource(this, R.array.weight_measures, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner_weight.setAdapter(adapter_weight);

        Spinner spinner_lenght = findViewById(R.id.spinner_lenght);
        ArrayAdapter<CharSequence> adapter_lenght = ArrayAdapter.createFromResource(this, R.array.lenght_measures, android.R.layout.simple_spinner_dropdown_item);
        spinner_lenght.setAdapter(adapter_lenght);
    }


    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(MenuItem item) {
        //EditText
        EditText input_age = findViewById(R.id.editText_age);
        EditText input_height = findViewById(R.id.editText_height);
        EditText input_weight = findViewById(R.id.editText_weight);

        switch (item.getItemId()) {
            case R.id.btn_restore:
                input_age.setText("");
                input_height.setText("");
                input_weight.setText("");
                return true;

            case R.id.btn_exit:
                System.exit(0);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public boolean onCreateOptionsMenu(Menu menu){
        //Print two option menu in toolbar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}