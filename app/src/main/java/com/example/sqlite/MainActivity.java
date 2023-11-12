package com.example.sqlite;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Button btnStore, btnGetall;

    //tiempo time = new tiempo();

    //private String fechayhora;

    private Spinner spinner;

    private EditText etrut, etname, etdescripcion;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);



       ArrayList<Laboratorio> laboratorios = new ArrayList<>();
       laboratorios.add(new Laboratorio("C2"));
       laboratorios.add(new Laboratorio("LEICA"));
       laboratorios.add(new Laboratorio("LINF"));
       laboratorios.add(new Laboratorio("LNET"));
       laboratorios.add(new Laboratorio("LTEL"));

       ArrayAdapter<Laboratorio> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,laboratorios);
       spinner.setAdapter(adapter);


        databaseHelper = new DatabaseHelper(this);
        btnStore = (Button) findViewById(R.id.btnstore);
        btnGetall = (Button) findViewById(R.id.btnget);
        etrut = (EditText) findViewById(R.id.etrut);
        etname = (EditText) findViewById(R.id.etname);
        etdescripcion = (EditText) findViewById(R.id.etdescripcion);


        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rut = etrut.getText().toString();
                String name = etname.getText().toString();
                String descripcion = etdescripcion.getText().toString();



                if(rut.isEmpty() || name.isEmpty() || descripcion.isEmpty()){
                    Toast.makeText(MainActivity.this, "todos loa campos deben estar llenos", Toast.LENGTH_SHORT).show();
                }else {
                    Laboratorio laboratorio = (Laboratorio) spinner.getSelectedItem();
                    String nombreLaboratorio = laboratorio.getNombre();

                    databaseHelper.addUserDetail(rut, name, descripcion, nombreLaboratorio);
                    etrut.setText("");
                    etname.setText("");
                    etdescripcion.setText("");


                    Toast.makeText(MainActivity.this, "Datos grabados!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnGetall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,GetAllUsersActivity.class);
                startActivity(intent);
            }
        });

    }

}
