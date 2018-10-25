package com.traffico.manhattan;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

    private Tienda tienda = new Tienda();

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
        loadStoreforUpdate();
    }

    public void updateStore(View view) {
        EditText eTDescription = findViewById(R.id.eTDescription);
        EditText eTAddress = findViewById(R.id.eTAddress);
        EditText eTLocation = findViewById(R.id.eTLocation);
        Spinner sMunicipio = findViewById(R.id.sCity);
        boolean flagCheck = validate(true);
        if (!flagCheck) {
            Toast.makeText(getBaseContext(), R.string.redInfo, Toast.LENGTH_SHORT).show();
        } else {
            tienda.setDescTienda(eTDescription.getText().toString());
            tienda.setDireccionTienda(eTAddress.getText().toString());
            tienda.setCoordenadasTienda(eTLocation.getText().toString());
            Municipio municipio = new Municipio();
            municipio = (Municipio) sMunicipio.getSelectedItem();
            tienda.setMunicipio(municipio);
            MyOpenHelper dbHelper = new MyOpenHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            if (db != null) {
                int flagInsert = dbHelper.updateTienda(db, tienda);
                Toast.makeText(getBaseContext(), R.string.update, Toast.LENGTH_SHORT).show();
                finish();
                startActivity(getIntent());
            }
        }
    }

    private boolean validate(boolean flagCheck) {
        EditText eTDescription = findViewById(R.id.eTDescription);
        EditText eTAddress = findViewById(R.id.eTAddress);
        EditText eTLocation = findViewById(R.id.eTLocation);
        TextView tvCity = findViewById(R.id.tVCity);
        TextView tvDescription = findViewById(R.id.tVDescStore);
        TextView tvAddress = findViewById(R.id.tVAddress);
        TextView tvLocation = findViewById(R.id.tVLocation);
        Spinner sMunicipio = findViewById(R.id.sCity);

        if (sMunicipio.getSelectedItemPosition() <= 0) {
            tvCity.setTextColor(Color.rgb(200, 0, 0));
            flagCheck = false;
        } else {
            tvCity.setTextColor(-1979711488);
        }
        if (eTLocation.getText().toString().isEmpty()) {
            tvLocation.setTextColor(Color.rgb(200, 0, 0));
            flagCheck = false;
        } else {
            tvLocation.setTextColor(-1979711488);
        }
        if (eTAddress.getText().toString().isEmpty()) {
            tvAddress.setTextColor(Color.rgb(200, 0, 0));
            flagCheck = false;
        } else {
            tvAddress.setTextColor(-1979711488);
        }
        if (eTDescription.getText().toString().isEmpty()) {
            tvDescription.setTextColor(Color.rgb(200, 0, 0));
        } else {
            tvDescription.setTextColor(-1979711488);
        }
        return flagCheck;
    }

    private void loadStoreforUpdate() {
        final ListView lvStores = findViewById(R.id.lvStores);
        lvStores.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                //
                AlertDialog.Builder confirmForUpdate = new AlertDialog.Builder(StoreActivity.this);
                confirmForUpdate.setTitle(R.string.wait);
                confirmForUpdate.setMessage(R.string.aygtut);
                confirmForUpdate.setCancelable(false);
                confirmForUpdate.setPositiveButton(R.string.y, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //
                        StoreActivity.this.tienda = (Tienda) lvStores.getItemAtPosition(position);
                        EditText eTDescription = findViewById(R.id.eTDescription);
                        eTDescription.setText(tienda.getDescTienda());
                        EditText eTAddress = findViewById(R.id.eTAddress);
                        eTAddress.setText(tienda.getDireccionTienda());
                        EditText eTLocation = findViewById(R.id.eTLocation);
                        eTLocation.setText(tienda.getCoordenadasTienda());
                        Spinner sDepartamento = findViewById(R.id.sState);
                        //
                        for (int i = 0; i < sDepartamento.getAdapter().getCount(); i++) {
                            Departamento departamento = (Departamento) sDepartamento.getAdapter().getItem(i);
                            if (tienda.getMunicipio().getDepartamento().getIdDepartamento() == departamento.getIdDepartamento()) {
                                sDepartamento.setSelection(i);
                                i = sDepartamento.getAdapter().getCount();
                            }
                        }
                        //
                        cargaSpinnerMunicipio(sDepartamento.getSelectedItem());
                        Spinner sMunicipio = findViewById(R.id.sCity);
                        //
                        for (int i = 0; i < sMunicipio.getAdapter().getCount(); i++) {
                            Municipio municipio = (Municipio) sMunicipio.getAdapter().getItem(i);
                            if (tienda.getMunicipio().getIdMunicipio() == municipio.getIdMunicipio()) {
                                sMunicipio.setSelection(i);
                                i = sMunicipio.getAdapter().getCount();
                            }
                        }
                        Button bnewStore = findViewById(R.id.bNewStore);
                        bnewStore.setVisibility(View.INVISIBLE);
                        Button bUpdateStore = findViewById(R.id.bUpdateStore);
                        bUpdateStore.setVisibility(View.VISIBLE);
                        //
                    }
                });
                confirmForUpdate.setNegativeButton(R.string.n, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                confirmForUpdate.show();
                //
                return false;
            }
        });
    }


    private void cargaSpinnerMunicipio(Object item) {
        ArrayList<Municipio> municipioList = (ArrayList<Municipio>) ((Departamento) item).getMunicipios();
        if (municipioList.get(0).getIdMunicipio() != 0) {
            municipioList.add(0, new Municipio(0, getResources().getString(R.string.select) + " ... ", new Departamento()));
        }
        Spinner sMunicipio = findViewById(R.id.sCity);
        ArrayAdapter<Municipio> aMunicipio = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, municipioList);
        aMunicipio.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sMunicipio.setAdapter(aMunicipio);
    }

    public void newStore(View view) {

        EditText eTDescription = findViewById(R.id.eTDescription);
        EditText eTAddress = findViewById(R.id.eTAddress);
        EditText eTLocation = findViewById(R.id.eTLocation);
        Spinner sMunicipio = findViewById(R.id.sCity);
        boolean flagCheck = validate(true);
        if (!flagCheck) {
            Toast.makeText(getBaseContext(), R.string.redInfo, Toast.LENGTH_SHORT).show();
        } else {
            Tienda tienda = new Tienda();
            tienda.setDescTienda(eTDescription.getText().toString());
            tienda.setDireccionTienda(eTAddress.getText().toString());
            tienda.setCoordenadasTienda(eTLocation.getText().toString());
            Municipio municipio = new Municipio();
            municipio = (Municipio) sMunicipio.getSelectedItem();
            tienda.setMunicipio(municipio);
            MyOpenHelper dbHelper = new MyOpenHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            if (db != null) {
                long flagInsert = dbHelper.insertTienda(db, tienda);
                Toast.makeText(getBaseContext(), R.string.create, Toast.LENGTH_SHORT).show();
                Intent storeIntent = new Intent(this, StoreActivity.class);
                startActivity(storeIntent);

            }
        }
    }

    private void cargaSpinners(SQLiteDatabase db, MyOpenHelper dbHelper) {
        ArrayList<Departamento> departamentoList = dbHelper.getDepartamentos(db);
        //
        try {
            if (departamentoList.get(0).getIdDepartamento() == 0) {
                departamentoList.add(0, new Departamento(0, getResources().getString(R.string.select) + " ... "));
            }
            //
            Spinner sDepartamento = findViewById(R.id.sState);
            final ArrayAdapter<Departamento> aDepartamento = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, departamentoList);
            aDepartamento.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

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

    private void cargaStores(SQLiteDatabase db, MyOpenHelper dbHelper) {
        ArrayList<Tienda> tiendaList = dbHelper.getTiendas(db);
        ListView lvStores = findViewById(R.id.lvStores);
        ArrayAdapter<Tienda> aTienda = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tiendaList);
        lvStores.setAdapter(aTienda);

    }

}
