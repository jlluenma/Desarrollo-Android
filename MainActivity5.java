package com.cixteam.balance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity5 extends AppCompatActivity {

    Spinner estado;
    Spinner comboProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        estado = (Spinner) findViewById(R.id.typeProduct);
        comboProductos = (Spinner) findViewById(R.id.typeProduct);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,
                R.array.typeProduct, android.R.layout.simple_spinner_item);

        comboProductos.setAdapter(adapter);
    }
}
