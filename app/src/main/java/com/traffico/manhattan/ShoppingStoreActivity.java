package com.traffico.manhattan;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.traffico.manhattan.classes.MyOpenHelper;
import com.traffico.manhattan.entities.Tienda;

import java.io.Serializable;
import java.util.ArrayList;

public class ShoppingStoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_store);
        MyOpenHelper dbHelper = new MyOpenHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db != null) {
            cargaStores(db, dbHelper);
        }

        storeLoadForShopping();
    }

    private void storeLoadForShopping() {
        ListView lvStore = findViewById(R.id.lvStores);
        lvStore.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ListView lvStores = findViewById(R.id.lvStores);
                Tienda tienda = (Tienda) lvStores.getItemAtPosition(position);
                Intent shoppingProductActivity = new Intent(view.getContext(), ShoppingProductActivity.class);
                shoppingProductActivity.putExtra("Store", tienda);
                startActivity(shoppingProductActivity);
                return false;
            }
        });
    }

    private void cargaStores(SQLiteDatabase db, MyOpenHelper dbHelper) {
        ArrayList<Tienda> storesList = dbHelper.getTiendas(db);
        ListView lvStores = findViewById(R.id.lvStores);
        ArrayAdapter<Tienda> aTienda = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, storesList);
        lvStores.setAdapter(aTienda);
    }


}
