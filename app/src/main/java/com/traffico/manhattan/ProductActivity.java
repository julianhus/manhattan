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
import android.widget.TextView;
import android.widget.Toast;

import com.traffico.manhattan.classes.MyOpenHelper;
import com.traffico.manhattan.entities.Producto;
import com.traffico.manhattan.google.zxing.integration.android.IntentIntegrator;
import com.traffico.manhattan.google.zxing.integration.android.IntentResult;


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
            loadProduct(scanContent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    private void loadProduct(String scanContent) {
        MyOpenHelper dbHelper = new MyOpenHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db != null) {
            Producto producto = new Producto();
            producto = dbHelper.getProducto(db, scanContent);
            if(producto != null){
                EditText etMake = findViewById(R.id.etMake);
                etMake.setText(producto.getMarca());
                EditText etDesc = findViewById(R.id.eTDescription);
                etDesc.setText(producto.getDescProducto());
            }
        }
    }
}
