package com.inacap.misconciertos;

import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.inacap.misconciertos.dao.EventoDAO;
import com.inacap.misconciertos.dto.Evento;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private List<Evento> eventos = new ArrayList<>();
    private EditText txtName;//Nombre del artista
    private EditText txtFecha;// Fecha
    private EditText txtValor;// Valor de Entrada
    private Spinner spin; // Genero Musical
    private Spinner spinDos; // Calificacion
    private Button btn; // btn agregar

    ArrayAdapter<Evento> eventoAdapter;

    private  String[] genMusical = {"Seleccione","Rock", "Jazz", "Pop", "Reguetoon", "Salsa", "Metal"};
    private String[] calificaion = {"Seleccione","1","2","3","4","5","6","7"};

    private static final String TAG = "MainActivity";
    DatePickerDialog.OnDateSetListener setListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Referencia de Los View
        //Nombre
        this.txtName =  findViewById(R.id.EtxtName);
        //Calendario
        this.txtFecha = findViewById(R.id.seFecha);
        //Genero Musical
        this.spin = findViewById(R.id.spinner);

        //Valor
        this.txtValor = findViewById(R.id.EvalorEntrada);
        //Calificacion
        this.spinDos = findViewById(R.id.spinnerDos);

        ArrayAdapter<String> a1, a2; // a1 = spin , a2 = spinDos

        //boton agregar
        this.btn = findViewById(R.id.agregarBtn);
        /////////////////////// Calendario ///////////////////////////////////
        txtFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int mes = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, android.R.style.Widget_Material, setListener, year, mes, day - 1);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int mes, int day) {
                mes = mes +1;
                Log.d(TAG, "onDateSet: dd/mm/yyyy" + day + "/" + mes + "/" + year);
                String date = day + "/"+ mes + "/"+ year;
                txtFecha.setText(date);
            }
        };
        ////////////////////// Fin de Calendario////////////////////////////////////

        //////////////////////Spinner////////////////////////////
        a1= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,genMusical);
        a2= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, calificaion);
        spin.setAdapter(a1);
        spinDos.setAdapter(a2);
        //////////////////////Fin de Spinner//////////////////////

        this.btn.setOnClickListener(new View.OnClickListener() {
            boolean isNameValid;
            @Override
            public void onClick(View view) {
                List<String> errores = new ArrayList<>();
                //nombre Validacion
                /*if(txtName.getText().toString().isEmpty()){
                   txtName.setError(getResources().getString(R.string.name_error));
                   isNameValid = false;
                }else{
                    isNameValid = true;
                }*/
                String nn =  txtName.getText().toString().trim();
                if(nn.isEmpty()){
                    errores.add("Ingrese Nombre");
                }

                //Fecha validacion
              /* if(txtFecha.getText().toString().isEmpty()){
                    txtFecha.setError(getResources().getString(R.string.date_error));
                    isNameValid = false;
                }else{
                    isNameValid = true;
                }*/

              String ff = txtFecha.getText().toString().trim();
              if (ff.isEmpty()){
                  errores.add("Ingrese Una Fecha");
              }

                // validar Genero Musical
               /* if(spin.getSelectedItemPosition()==0){
                    TextView errorText = (TextView) spin.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.BLUE);
                    errorText.setText("Seleccione un Genero");
                }*/
               Boolean ss;
               if(ss = spin.getSelectedItemPosition()==0){
                   TextView errorText = (TextView) spin.getSelectedView();
                   errorText.setError("");
                   errorText.setTextColor(Color.BLUE);
                   errorText.setText("Seleccione un Genero");
               }


                //Validar Valor
               /* txtValor.setError(null);
                String valor = txtValor.getText().toString();
                if ("".equals(valor)){
                   txtValor.setError("Introduce un valor mayor a 0");
                   txtValor.requestFocus();
                   return;
                }
                int num = Integer.parseInt(valor);
                if (num > 0){
                }*/
                String valora = txtValor.getText().toString().trim();
                int valor=0;
                try{
                    valor = Integer.parseInt(valora);
                    if(valor < 0){
                        throw new NumberFormatException();
                    }
                }catch (NumberFormatException ex){
                    errores.add("El valor debe ser mayor a 0");
                }

                //Validar Calificacion
                /*
                if (spinDos.getSelectedItemPosition()==0 ){
                    TextView errorText = (TextView) spinDos.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.BLUE);
                    errorText.setText("Clasifique al Artista");
                }*/
                String ssDos = spinDos.getSelectedItem().toString();
                int ssD = 0;
                try {
                    ssD = Integer.parseInt(ssDos);
                    if (ssD < 0) {
                        throw new NumberFormatException();
                 }
                } catch (NumberFormatException e) {
                    errores.add("Seleccione una Calificacion");
                }

                if (errores.isEmpty()){
                Evento evento = new Evento();
                evento.setName(nn);
                evento.setFecha(ff);
                evento.setGeneroMusical(ss);
                evento.setValorEntrada(valor);
                evento.setCalifi(ssD);

                new EventoDAO().add(evento);

                eventoAdapter.notifyDataSetChanged();
        }else{
                    mostrarErrores(errores);
                }

            }

    });
    }


    @Override
    public void onItemSelected(AdapterView<?> arg0, View v, int position, long id) {
        Toast.makeText(getApplicationContext(),genMusical[position], Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),calificaion[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    private void mostrarErrores(List<String> errores){
        String mensaje = "";
        for(String e: errores){
            mensaje+= "*" + e + "\n";
        }
        //Mostrar-mensaje de alerta
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
        alertBuilder.setTitle("Error de validación")
                .setMessage(mensaje)
                .setPositiveButton("Agregar", null)
                .create()
                .show();


    }
}
