package com.traffico.manhattan;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.traffico.manhattan.classes.MyOpenHelper;
import com.traffico.manhattan.entities.Departamento;
import com.traffico.manhattan.entities.Municipio;
import com.traffico.manhattan.entities.Tienda;

import java.util.ArrayList;

public class StoreActivity extends AppCompatActivity {

    Spinner sDepartamento;
    Spinner sMunicipio;
    ListView lvStores;
    EditText eTDescription;
    EditText eTAddress;
    EditText eTLocation;
    TextView tvLocation;
    TextView tvAddress;
    TextView tvDescription;
    Municipio municipio = new Municipio();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        MyOpenHelper dbHelper = new MyOpenHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db != null) {
            cargaStores(db, dbHelper);
            cargaSpinners(db, dbHelper);
        }
    }

    private void cargaStores(SQLiteDatabase db, MyOpenHelper dbHelper) {
        ArrayList<Tienda> tiendaList = dbHelper.getTiendas(db);
        lvStores = findViewById(R.id.lvStores);
        ArrayAdapter<Tienda> aTienda = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tiendaList);
        lvStores.setAdapter(aTienda);

    }

    private void cargaSpinners(SQLiteDatabase db, MyOpenHelper dbHelper) {
        ArrayList<Departamento> departamentoList = dbHelper.getDepartamentos(db);
        departamentoList.add(0, new Departamento(0, getResources().getString(R.string.select) + " ... "));
        sDepartamento = findViewById(R.id.sState);
        final ArrayAdapter<Departamento> aDepartamento = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, departamentoList);
        aDepartamento.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        try {
            sDepartamento.setAdapter(aDepartamento);
            sDepartamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if ((position != 0) && (id != 0)) {
                        Object item = parent.getItemAtPosition(position);
                        int idSeleccionado = ((Departamento) item).getIdDepartamento();
                        //Toast.makeText(getBaseContext(), ((Departamento) item).getIdDepartamento() + " " + ((Departamento) item).getDescDepartamento(), Toast.LENGTH_SHORT).show();
                        //
                        cargaSpinnerMunicipio(item);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "Fatal Error", Toast.LENGTH_SHORT).show();
            Log.e("Error", "cargaSpiners: " + e.getMessage(), null);
        }
    }

    private void cargaSpinnerMunicipio(Object item) {
        ArrayList<Municipio> municipioList = (ArrayList<Municipio>) ((Departamento) item).getMunicipios();
        //ArrayList<Municipio> municipioList = new ArrayList<>();
        municipioList.add(0, new Municipio(0, getResources().getString(R.string.select) + " ... ", new Departamento()));
        sMunicipio = findViewById(R.id.sCity);
        ArrayAdapter<Municipio> aMunicipio = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, municipioList);
        aMunicipio.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sMunicipio.setAdapter(aMunicipio);
        sMunicipio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if ((position != 0) && (id != 0)) {
                    Object item = parent.getItemAtPosition(position);
                    municipio.setIdMunicipio(((Municipio) item).getIdMunicipio());
                    municipio.setDescMunicipio(((Municipio) item).getDescMunicipio());
                    municipio.setDepartamento(((Municipio) item).getDepartamento());
                    //Toast.makeText(getBaseContext(), ((Departamento) item).getIdDepartamento() + " " + ((Departamento) item).getDescDepartamento(), Toast.LENGTH_SHORT).show();
                    //
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void newStore(View view) {
        eTDescription = findViewById(R.id.eTDescription);
        eTAddress = findViewById(R.id.eTAddress);
        eTLocation = findViewById(R.id.eTLocation);
        tvDescription=findViewById(R.id.tVDescStore);
        tvAddress=findViewById(R.id.tVAddress);
        tvLocation=findViewById(R.id.tVLocation);
        if (eTDescription.getText().toString().isEmpty() && eTAddress.getText().toString().isEmpty() && eTLocation.getText().toString().isEmpty()) {
            tvDescription.setTextColor(Color.rgb(200, 0, 0));
            tvAddress.setTextColor(Color.rgb(200, 0, 0));
            tvLocation.setTextColor(Color.rgb(200, 0, 0));
            Toast.makeText(getBaseContext(), R.string.redInfo, Toast.LENGTH_SHORT).show();
        } else {
            Tienda tienda = new Tienda();
            tienda.setDescTienda(eTDescription.getText().toString());
            tienda.setDireccionTienda(eTAddress.getText().toString());
            tienda.setCoordenadasTienda(eTLocation.getText().toString());
            tienda.setMunicipio(municipio);
            MyOpenHelper dbHelper = new MyOpenHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            if (db != null) {
                long flagInsert = dbHelper.insertTienda(db, tienda);
                Toast.makeText(getBaseContext(), R.string.create, Toast.LENGTH_SHORT).show();
                Intent storeIntent = new Intent(this,StoreActivity.class);
                startActivity(storeIntent);

            }
        }
    }


}
