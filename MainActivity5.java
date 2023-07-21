package com.cixteam.balance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
//import android.widget.Toast;

import com.cixteam.balance.db.DbHelper;

public class MainActivity5 extends AppCompatActivity {

    private EditText nameProduct;
    private EditText priceProduct;
    private EditText stock;
    Button btnRegister;
    Spinner estado;
    Spinner comboProductos;

    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        nameProduct = findViewById(R.id.nameProduct);
        priceProduct = findViewById(R.id.priceProduct);
        stock = findViewById(R.id.stock);
        btnRegister = findViewById(R.id.btnRegister);

        dbHelper = new DbHelper(this);

        btnRegister.setOnClickListener(view -> {

            String nombreProducto = nameProduct.getText().toString();
            String precioProductoString = priceProduct.getText().toString();
            String stockString = stock.getText().toString();

            if (TextUtils.isEmpty(nombreProducto) || TextUtils.isEmpty(precioProductoString) || TextUtils.isEmpty(stockString)) {
                Toast.makeText(MainActivity5.this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
                return;
            }
            float precioProducto = Float.parseFloat(precioProductoString);
            float stockProducto = Float.parseFloat(stockString);
            insertarProductoEnStock(nombreProducto, precioProducto, stockProducto);

            Toast.makeText(MainActivity5.this, "Producto registrado exitosamente", Toast.LENGTH_SHORT).show();
        });

        estado = (Spinner) findViewById(R.id.typeProduct);
        comboProductos = (Spinner) findViewById(R.id.typeProduct);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.typeProduct, android.R.layout.simple_spinner_item);

        comboProductos.setAdapter(adapter);
    }
    private void insertarProductoEnStock(String nombre, float precio, float stock) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("precio", precio);
        values.put("stock", stock);

        db.insert(DbHelper.TABLE_STOCK, null, values);
        db.close();
    }
}
