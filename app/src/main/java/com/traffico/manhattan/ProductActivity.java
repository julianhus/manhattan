package com.traffico.manhattan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.traffico.manhattan.classes.MyOpenHelper;
import com.traffico.manhattan.entities.Producto;
import com.traffico.manhattan.entities.ValorProducto;
import com.traffico.manhattan.google.zxing.integration.android.IntentIntegrator;
import com.traffico.manhattan.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;


public class ProductActivity extends AppCompatActivity implements OnClickListener {

    private ImageButton bScann;
    private EditText etBarCode;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        etBarCode = findViewById(R.id.etBarCode);
        bScann = findViewById(R.id.iBScan);
        bScann.setOnClickListener((View.OnClickListener) this);
        //

    }

    public void onClick(View view) {
        if (view.getId() == R.id.iBScan) {
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            //we have a result
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            etBarCode.setText(scanContent);
            loadProduct();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    private void loadProduct() {
        Button bNewButton = findViewById(R.id.bNewProduct);
        MyOpenHelper dbHelper = new MyOpenHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db != null) {
            Producto producto = dbHelper.getProducto(db, etBarCode.getText().toString());
            if (producto.getIdProducto() != 0) {
                etBarCode.setEnabled(false);
                EditText etMake = findViewById(R.id.etMake);
                etMake.setText(producto.getMarca());
                etMake.setEnabled(false);
                EditText etDesc = findViewById(R.id.etProduct);
                etDesc.setText(producto.getDescProducto());
                etDesc.setEnabled(false);
                bNewButton.setEnabled(false);
            } else {
                bNewButton.setEnabled(true);
            }
        }
    }

    public void loadProduct(View view) {
        loadProduct();
        Button bNewProduct = findViewById(R.id.bNewProduct);
        if (bNewProduct.isEnabled()) {
            Producto producto = new Producto();
            ArrayList<ValorProducto> aValorProductos = new ArrayList<>();
            ValorProducto valorProducto = new ValorProducto();
            producto.setBarCode(etBarCode.getText().toString());
            EditText etMake = findViewById(R.id.etMake);
            producto.setMarca(etMake.getText().toString());
            EditText etDesc = findViewById(R.id.etProduct);
            producto.setDescProducto(etDesc.getText().toString());
            MyOpenHelper dbHelper = new MyOpenHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            if (db != null) {
                long flagInsert =  dbHelper.insertProduct(db, producto);
                Toast.makeText(getBaseContext(), R.string.create, Toast.LENGTH_SHORT).show();
                Intent storeIntent = new Intent(this, ProductListActivity.class);
                startActivity(storeIntent);
            }
        }
    }
}


