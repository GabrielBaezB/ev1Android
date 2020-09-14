package com.inacap.misconciertos;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    ArrayAdapter<String> a1, a2;
    private String[] genMusical = {"Rock", "Jazz", "Pop", "Reguetoon", "Salsa", "Metal"};
    private String[] calificaion = {"1","2","3","4","5","6","7"};
    private static final String TAG = "MainActivity";
    DatePickerDialog.OnDateSetListener mDsL;
    private TextView textView;
    private Button btn;
    private Spinner spin;
    private Spinner spinDos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.textView = findViewById(R.id.seFecha);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int mes = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Black, mDsL, day, mes, year);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDsL = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int mes, int day) {
                mes = mes +1;
                Log.d(TAG, "onDateSet: dd/mm/yyyy" + day + "/" + mes + "/" + year);
                String date = day + "/"+ mes + "/"+ year;
               textView.setText(date);
            }
        };

        this.spin = findViewById(R.id.spinner);
        this.spinDos = findViewById(R.id.spinnerDos);

        a1= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,genMusical);
        a2= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, calificaion);
        spin.setAdapter(a1);
        spinDos.setAdapter(a2);



    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View v, int position, long id) {
        Toast.makeText(getApplicationContext(),genMusical[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
