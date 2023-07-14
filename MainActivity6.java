package com.cixteam.balance;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.cixteam.balance.db.DbHelper;
import android.widget.Spinner;

public class MainActivity6 extends AppCompatActivity {

    private EditText nameProductSales;
    private EditText typeProduct;
    private EditText pay;
    private EditText units;
    private Button btnRegister;

    private DbHelper dbHelper;
    Spinner estado;
    Spinner comboProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        nameProductSales = findViewById(R.id.nameProductSales);
        pay = findViewById(R.id.pay);
        units = findViewById(R.id.units);
        btnRegister = findViewById(R.id.btnRegister);

        dbHelper = new DbHelper(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nameProductSales.getText().toString();
                String importeString = pay.getText().toString();
                float importe = Float.parseFloat(importeString);
                String unidadesString = units.getText().toString();
                double unidades = Double.parseDouble(unidadesString);

                insertarVenta(nombre, importe, unidades);

                Toast.makeText(MainActivity6.this, "Venta registrada exitosamente", Toast.LENGTH_SHORT).show();
            }
        });

        estado = (Spinner) findViewById(R.id.typeProduct);
        comboProductos = (Spinner) findViewById(R.id.typeProduct);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,
                R.array.typeProduct, android.R.layout.simple_spinner_item);

        comboProductos.setAdapter(adapter);
    }

    private void insertarVenta(String nombre, float importe, double unidades) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("importe", importe);
        values.put("unidades", unidades);

        db.insert(DbHelper.TABLE_SALES, null, values);
        db.close();
    }
}
