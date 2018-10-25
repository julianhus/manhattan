package com.traffico.manhattan;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.traffico.manhattan.classes.MyOpenHelper;
import com.traffico.manhattan.entities.Producto;
import com.traffico.manhattan.entities.Tienda;
import com.traffico.manhattan.google.zxing.integration.android.IntentIntegrator;
import com.traffico.manhattan.google.zxing.integration.android.IntentResult;

public class ShoppingProductActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton bScann;
    private EditText etBarCode;
    private Tienda tienda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_product);
        etBarCode = findViewById(R.id.etBarCode);
        bScann = findViewById(R.id.iBScan);
        bScann.setOnClickListener((View.OnClickListener) this);
        Intent shoppingProductActivity = getIntent();
        tienda = (Tienda) shoppingProductActivity.getSerializableExtra("Store");
        TextView tvStore = findViewById(R.id.tvStore);
        tvStore.setText(tienda.toString());
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
            } else {
                AlertDialog.Builder confirmForUpdate = new AlertDialog.Builder(ShoppingProductActivity.this);
                confirmForUpdate.setTitle(R.string.wait);
                confirmForUpdate.setMessage(R.string.product_no_found);
                confirmForUpdate.setCancelable(false);
                confirmForUpdate.setPositiveButton(R.string.y, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                confirmForUpdate.setNegativeButton(R.string.n, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                confirmForUpdate.show();
            }
        }
    }
}
