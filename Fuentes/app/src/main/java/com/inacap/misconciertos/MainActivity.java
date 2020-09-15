package com.inacap.misconciertos;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    ArrayAdapter<String> a1, a2;
    private String[] genMusical = {"Seleccione","Rock", "Jazz", "Pop", "Reguetoon", "Salsa", "Metal"};
    private String[] calificaion = {"Seleccione","1","2","3","4","5","6","7"};
    private static final String TAG = "MainActivity";
    DatePickerDialog.OnDateSetListener mDsL;
    private EditText txtName;
    private EditText txtValor;
    private TextView txtFecha;
    private Button btn;
    private Spinner spin;
    private Spinner spinDos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Nombre
        this.txtName =  findViewById(R.id.EtxtName);
        //boton agregar
        this.btn = findViewById(R.id.agregarBtn);
        //Valor
        this.txtValor = findViewById(R.id.EvalorEntrada);
        //Calendario
        this.txtFecha = findViewById(R.id.seFecha);
        txtFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int mes = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, android.R.style.Widget_Material, mDsL, year - 10, mes, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dialog.show();
            }
        });
        mDsL = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int mes, int day) {
                mes = mes +1;
                Log.d(TAG, "onDateSet: dd/mm/yyyy" + day + "/" + mes + "/" + year);
                String date = day + "/"+ mes + "/"+ year;
                txtFecha.setText(date);
            }
        };


        this.spin = findViewById(R.id.spinner);
        this.spinDos = findViewById(R.id.spinnerDos);

        a1= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,genMusical);
        a2= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, calificaion);
        spin.setAdapter(a1);
        spinDos.setAdapter(a2);


        this.btn.setOnClickListener(new View.OnClickListener() {
            boolean isNameValid;
            @Override
            public void onClick(View view) {
                List<String> errores = new ArrayList<>();
                //nombre Validacion
                if(txtName.getText().toString().isEmpty()){
                   txtName.setError(getResources().getString(R.string.name_error));
                   isNameValid = false;
                }else{
                    isNameValid = true;
                }

                //Fecha validacion
                if(txtFecha.getText().toString().isEmpty()){
                    txtFecha.setError(getResources().getString(R.string.date_error));
                    isNameValid = false;
                }else{
                    isNameValid = true;
                }

                // validar Genero Musical
                if(spin.getSelectedItemPosition()==0){
                    TextView errorText = (TextView) spin.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.BLUE);
                    errorText.setText("Seleccione un Genero");
                }

                //Validar Valor
                txtValor.setError(null);
                String valor = txtValor.getText().toString();
                if ("".equals(valor)){
                   txtValor.setError("Introduce un valor mayor a 0");
                   txtValor.requestFocus();
                   return;
                }
                int num = Integer.parseInt(valor);
                if (num > 0){

                }

                //Validar Calificacion
                if (spinDos.getSelectedItemPosition()==0 ){
                    TextView errorText = (TextView) spinDos.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.BLUE);
                    errorText.setText("Clasifique al Artista");
                }




        }

    });
    }



    @Override
    public void onItemSelected(AdapterView<?> arg0, View v, int position, long id) {
        Toast.makeText(getApplicationContext(),genMusical[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
