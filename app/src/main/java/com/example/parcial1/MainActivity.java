package com.example.parcial1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity{
    DecimalFormat decimal = new DecimalFormat("#.00");
    TextView num_imc, lbl_imc, title_imc;
    EditText input_age, input_height, input_weight;
    ImageView img_body;
    ImageButton btn_calculate, btn_woman, btn_man;
    String state, u_lenght, u_weight;
    int sexo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num_imc = findViewById(R.id.imc_result);
        lbl_imc = findViewById(R.id.body_build_result);
        title_imc = findViewById(R.id.title_imc);

        btn_calculate = findViewById(R.id.btn_calculate);
        btn_woman = findViewById(R.id.btn_woman);
        btn_man = findViewById(R.id.btn_man);

        input_age = findViewById(R.id.input_age);
        input_height = findViewById(R.id.input_height);
        input_weight = findViewById(R.id.input_weight);

        img_body = findViewById(R.id.img_body);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner spinner_weight = findViewById(R.id.spinner_weight);
        ArrayAdapter<CharSequence> adapter_weight = ArrayAdapter.createFromResource(this, R.array.weight_measures, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner_weight.setAdapter(adapter_weight);

        Spinner spinner_lenght = findViewById(R.id.spinner_lenght);
        ArrayAdapter<CharSequence> adapter_lenght = ArrayAdapter.createFromResource(this, R.array.lenght_measures, android.R.layout.simple_spinner_dropdown_item);
        spinner_lenght.setAdapter(adapter_lenght);

        title_imc.setVisibility(View.GONE);
        num_imc.setVisibility(View.GONE);
        lbl_imc.setVisibility(View.GONE);


        btn_woman.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                btn_woman.setImageDrawable(getResources().getDrawable(R.drawable.icon_woman_color_all));
                btn_man.setImageDrawable(getResources().getDrawable(R.drawable.icon_man_color_any));
                sexo = 1;
            }
        });


        btn_man.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            public void onClick(View view) {
                btn_woman.setImageDrawable(getResources().getDrawable(R.drawable.icon_woman_color_any));
                btn_man.setImageDrawable(getResources().getDrawable(R.drawable.icon_man_color_all));
                sexo = 2;
            }
        });


        spinner_lenght.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                u_lenght = adapterView.getItemAtPosition(i).toString();
            }
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });


        spinner_weight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                u_weight = adapterView.getItemAtPosition(i).toString();
            }
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });


        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            public void onClick(View view) {
                try{
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(btn_calculate.getWindowToken(), 0);

                    if(!input_age.getText().toString().equals("") && !input_height.getText().toString().equals("")  && !input_weight.getText().toString().equals("") && sexo!=0){
                        double value_height = Double.parseDouble(input_height.getText().toString());
                        double value_weight = Double.parseDouble(input_weight.getText().toString());
                        int hedad = Integer.parseInt(input_age.getText().toString());

                        value_height = (u_lenght.equals("PL")) ? value_height/39.37 :
                                        (u_lenght.equals("CM")) ? value_height/100 : value_height;

                        value_weight = (u_weight.equals("LB")) ? value_weight/2.2 : value_weight;

                        double c_imc = value_weight / Math.pow(value_height, 2);
                        num_imc.setText(String.valueOf((decimal.format(c_imc))));

                        if(hedad < 16){
                            if (sexo == 1){
                                if (c_imc >= 19 && c_imc <= 24){
                                    lbl_imc.setText("Normal");
                                }
                                else if (c_imc < 19){
                                    lbl_imc.setText("Delgadez");
                                }
                                else {
                                    lbl_imc.setText("Sobrepeso");
                                }
                            }
                            if (sexo == 2){
                                if (c_imc >= 19 && c_imc <= 24){
                                    lbl_imc.setText("Normal");
                                }
                                else if (c_imc < 19){
                                    lbl_imc.setText("Delgadez");
                                }
                                else {
                                    lbl_imc.setText("Sobrepeso");
                                }
                            }
                        }

                        //edad 16-18 ***
                        if (hedad >= 16 && hedad <= 18){
                            if (sexo == 1){
                                if (c_imc >= 19 && c_imc <= 24){
                                    lbl_imc.setText("Normal");
                                }
                                else if (c_imc < 19){
                                    lbl_imc.setText("Delgadez");
                                }
                                else {
                                    lbl_imc.setText("Sobrepeso");
                                }
                            }
                            if (sexo == 2){
                                if (c_imc >= 20 && c_imc <= 25){
                                    lbl_imc.setText("Normal");
                                }
                                else if (c_imc < 20){
                                    lbl_imc.setText("Delgadez");
                                }
                                else {
                                    lbl_imc.setText("Sobrepeso");
                                }
                            }
                        }

                        //edad 19-24 ***
                        if (hedad >= 19 && hedad <= 24){
                            if (sexo == 1){
                                if (c_imc >= 19 && c_imc <= 24){
                                    lbl_imc.setText("Normal");
                                }
                                else if (c_imc <19 ){
                                    lbl_imc.setText("Delgadez");
                                }
                                else {
                                    lbl_imc.setText("Sobrepeso");
                                }
                            }
                            if (sexo == 2){
                                if (c_imc >= 21 && c_imc <=26){
                                    lbl_imc.setText("Normal");
                                }
                                else if (c_imc < 21){
                                    lbl_imc.setText("Delgadez");
                                }
                                else {
                                    lbl_imc.setText("Sobrepeso");
                                }
                            }
                        }

                        //edad 25-34 ***
                        if (hedad >= 25 && hedad <= 34){
                            if (sexo == 1){
                                if (c_imc >= 20 && c_imc <= 25){
                                    lbl_imc.setText("Normal");
                                }
                                else if (c_imc < 20){
                                    lbl_imc.setText("Delgadez");
                                }
                                else {
                                    lbl_imc.setText("Sobrepeso");
                                }
                            }
                            if (sexo == 2){
                                //22-27
                                if (c_imc >= 22 && c_imc <= 27){
                                    lbl_imc.setText("Normal");
                                }
                                else if (c_imc < 22){
                                    lbl_imc.setText("Delgadez");
                                }
                                else {
                                    lbl_imc.setText("Sobrepeso");
                                }
                            }
                        }

                        //edad 35-44
                        if (hedad >= 35 && hedad <= 44){
                            if (sexo == 1){
                                if (c_imc >= 21 && c_imc <= 26) {
                                    lbl_imc.setText("Normal");
                                }
                                if (c_imc < 21){
                                    lbl_imc.setText("Delgadez");
                                }
                                if (c_imc > 26){
                                    lbl_imc.setText("Sobrepeso");
                                }
                            }
                            if (sexo == 2){
                                if (c_imc >= 23 && c_imc <= 28) {
                                    lbl_imc.setText("Normal");
                                }
                                if (c_imc < 23){
                                    lbl_imc.setText("Delgadez");
                                }
                                if (c_imc > 28){
                                    lbl_imc.setText("Sobrepeso");
                                }
                            }
                        }

                        //45-54
                        if (hedad >= 45 && hedad <= 54){
                            if (sexo == 1){
                                if (c_imc >= 22 && c_imc <= 27) {
                                    lbl_imc.setText("Normal");
                                }
                                if (c_imc < 22){
                                    lbl_imc.setText("Delgadez");
                                }
                                if (c_imc > 27){
                                    lbl_imc.setText("Sobrepeso");
                                }
                            }
                            if (sexo == 2){
                                if (c_imc >= 23 && c_imc <= 29) {
                                    lbl_imc.setText("Normal");
                                }
                                if (c_imc < 23){
                                    lbl_imc.setText("Delgadez");
                                }
                                if (c_imc > 29){
                                    lbl_imc.setText("Sobrepeso");
                                }
                            }
                        }

                        //55-64if
                        if (hedad >= 55 && hedad <= 64){
                            if (sexo == 1){
                                if (c_imc >= 23 && c_imc <= 28) {  //23-28
                                    lbl_imc.setText("Normal");
                                }
                                if (c_imc < 23){
                                    lbl_imc.setText("Delgadez");
                                }
                                if (c_imc > 28){
                                    lbl_imc.setText("Sobrepeso");
                                }
                            }
                            if (sexo == 2){
                                if (c_imc >= 24 && c_imc <= 29) { //24 29
                                    lbl_imc.setText("Normal");
                                }
                                if (c_imc < 24){
                                    lbl_imc.setText("Delgadez");
                                }
                                if (c_imc > 29){
                                    lbl_imc.setText("Sobrepeso");
                                }
                            }
                        }

                        if (hedad >= 65){
                            if (sexo == 1){
                                if (c_imc >= 25 && c_imc <= 30){
                                    lbl_imc.setText("Normal");
                                }
                                if (c_imc < 25){
                                    lbl_imc.setText("Delgadez");
                                }
                                if (c_imc > 30){
                                    lbl_imc.setText("Sobrepeso");
                                }
                            }
                            if (sexo == 2){
                                if (c_imc >= 25 && c_imc <= 30){
                                    lbl_imc.setText("Normal");
                                }
                                if (c_imc < 25){
                                    lbl_imc.setText("Delgadez");
                                }
                                if (c_imc > 30){
                                    lbl_imc.setText("Sobrepeso");
                                }
                            }
                        }

                        switch(sexo){
                            case 1:
                                if(lbl_imc.getText().equals("Normal")){
                                    num_imc.setTextColor(getResources().getColor(R.color.green));
                                    lbl_imc.setTextColor(getResources().getColor(R.color.green));
                                    img_body.setImageDrawable(getResources().getDrawable(R.drawable.woman_color_all_second));
                                }else if(lbl_imc.getText().equals("Delgadez")){
                                    num_imc.setTextColor(getResources().getColor(R.color.orange));
                                    lbl_imc.setTextColor(getResources().getColor(R.color.orange));
                                    img_body.setImageDrawable(getResources().getDrawable(R.drawable.woman_color_all_third));
                                }else if(lbl_imc.getText().equals("Sobrepeso")){
                                    num_imc.setTextColor(getResources().getColor(R.color.red));
                                    lbl_imc.setTextColor(getResources().getColor(R.color.red));
                                    img_body.setImageDrawable(getResources().getDrawable(R.drawable.woman_color_all_first));
                                }
                                break;

                            case 2:
                                if(lbl_imc.getText().equals("Normal")){
                                    num_imc.setTextColor(getResources().getColor(R.color.green));
                                    lbl_imc.setTextColor(getResources().getColor(R.color.green));
                                    img_body.setImageDrawable(getResources().getDrawable(R.drawable.man_color_second));
                                }else if(lbl_imc.getText().equals("Delgadez")){
                                    num_imc.setTextColor(getResources().getColor(R.color.orange));
                                    lbl_imc.setTextColor(getResources().getColor(R.color.orange));
                                    img_body.setImageDrawable(getResources().getDrawable(R.drawable.man_color_third));
                                }else if(lbl_imc.getText().equals("Sobrepeso")){
                                    num_imc.setTextColor(getResources().getColor(R.color.red));
                                    lbl_imc.setTextColor(getResources().getColor(R.color.red));
                                    img_body.setImageDrawable(getResources().getDrawable(R.drawable.man_color_first));
                                }
                                break;
                        }

                        title_imc.setVisibility(View.VISIBLE);
                        num_imc.setVisibility(View.VISIBLE);
                        lbl_imc.setVisibility(View.VISIBLE);
                        img_body.setVisibility(View.VISIBLE);
                    }else{
                        Toast.makeText(MainActivity.this, "ERR_EMPTY_INPUT", Toast.LENGTH_SHORT).show();
                    }
                }catch(Exception e){
                    Toast.makeText(MainActivity.this, "ERR_GLOBAL", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_restore:
                input_age.setText("");
                input_height.setText("");
                input_weight.setText("");
                title_imc.setVisibility(View.GONE);
                num_imc.setVisibility(View.GONE);
                lbl_imc.setVisibility(View.GONE);
                img_body.setVisibility(View.GONE);
                btn_woman.setImageDrawable(getResources().getDrawable(R.drawable.icon_woman_color_any));
                btn_man.setImageDrawable(getResources().getDrawable(R.drawable.icon_man_color_any));
                sexo = 0;
                return true;

            case R.id.btn_exit:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("¿Desea Salir de la Aplicación?")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                System.exit(0);
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
                builder.create().show();
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