package com.example.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateDeleteActivity extends AppCompatActivity {

    private UserModel userModel;
    private EditText etname, etdescripcion, etrut;
    private Button btnupdate, btndelete;
    private Spinner spinner;
    private List<Laboratorio> laboratorioList;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        Intent intent = getIntent();
        userModel = (UserModel) intent.getSerializableExtra("user");

        databaseHelper = new DatabaseHelper(this);
        etrut = (EditText) findViewById(R.id.etrut);
        etname = (EditText) findViewById(R.id.etname);
        etdescripcion = (EditText) findViewById(R.id.etdescripcion);
        btndelete = (Button) findViewById(R.id.btndelete);
        btnupdate = (Button) findViewById(R.id.btnupdate);
        spinner = (Spinner) findViewById(R.id.spinner);


        laboratorioList = getLabFromDatabase();
        ArrayAdapter<Laboratorio> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, laboratorioList);
        spinner.setAdapter(adapter);

        for (int i = 0; i < laboratorioList.size(); i++){
            if(laboratorioList.get(i).getNombre().equals(userModel.getLab())){
                spinner.setSelection(i);
                break;
            }
        }

        etrut.setText(userModel.getRut());
        etname.setText(userModel.getName());
        etdescripcion.setText(userModel.getDescripcion());

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Laboratorio laboratorio = (Laboratorio) spinner.getSelectedItem();

                databaseHelper.updateUser(userModel.getId(), etrut.getText().toString(), etname.getText().toString(), etdescripcion.getText().toString(), laboratorio.getNombre());

                Toast.makeText(UpdateDeleteActivity.this, "ACTUALIZACIÓN EXITOSA!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateDeleteActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteUSer(userModel.getId());
                Toast.makeText(UpdateDeleteActivity.this, "ELIMINADO CORRECTAMENTE!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateDeleteActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }
    private List<Laboratorio> getLabFromDatabase(){
        List<Laboratorio> laboratorios = new ArrayList<>();
        return laboratorios;
    }
}
