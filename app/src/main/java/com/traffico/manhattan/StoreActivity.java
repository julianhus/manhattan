package com.traffico.manhattan;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.traffico.manhattan.classes.MyOpenHelper;
import com.traffico.manhattan.entities.Tienda;

import java.util.ArrayList;

public class StoreActivity extends AppCompatActivity {

    private ArrayList<String> registros;
    private ArrayAdapter<String> adaptador;
    private ListView lv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        MyOpenHelper dbHelper = new MyOpenHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db != null) {
            ArrayList<Tienda> tiendas = dbHelper.getTiendas(db);
            while(tiendas.iterator().hasNext()){

            }
        }
    }
}
