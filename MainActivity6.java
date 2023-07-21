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


// Importar las clases necesarias
public class MainActivity6 extends AppCompatActivity {

    // Declaración de variables para interactuar con los elementos de la interfaz de usuario
    private EditText nameProductSales;
    private EditText pay;
    private EditText units;

    private DbHelper dbHelper; // Instancia de la clase DbHelper para manejar la base de datos
    Spinner estado;
    Spinner comboProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6); // Establecer el layout (diseño) de la actividad

        // Asignar los elementos de la interfaz a las variables declaradas
        nameProductSales = findViewById(R.id.nameProductSales);
        pay = findViewById(R.id.pay);
        units = findViewById(R.id.units);
        Button btnRegister = findViewById(R.id.btnRegister);

        dbHelper = new DbHelper(this); // Inicializar la instancia de DbHelper

        // Asignar un Listener al botón de registro para capturar el evento de clic
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores ingresados por el usuario en los campos de texto
                String nombre = nameProductSales.getText().toString();
                String importeString = pay.getText().toString();
                float importe = Float.parseFloat(importeString);
                String unidadesString = units.getText().toString();
                double unidades = Double.parseDouble(unidadesString);

                // Llamar al método para insertar la venta en la base de datos
                insertarVenta(nombre, importe, unidades);

                // Mostrar un mensaje de éxito al usuario
                Toast.makeText(MainActivity6.this, "Venta registrada exitosamente", Toast.LENGTH_SHORT).show();
            }
        });

        // Asignar los Spinners a sus respectivas variables y cargar los datos en el Spinner "comboProductos"
        estado = (Spinner) findViewById(R.id.typeProduct);
        comboProductos = (Spinner) findViewById(R.id.typeProduct);

        // Crear un ArrayAdapter para el Spinner "comboProductos" y establecer los datos desde el recurso "typeProduct"
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,
                R.array.typeProduct, android.R.layout.simple_spinner_item);

        // Asignar el adaptador al Spinner "comboProductos"
        comboProductos.setAdapter(adapter);
    }

    // Método para insertar una nueva venta en la base de datos
    private void insertarVenta(String nombre, float importe, double unidades) {
        SQLiteDatabase db = dbHelper.getWritableDatabase(); // Obtener la base de datos en modo escritura

        // Crear un objeto ContentValues para almacenar los valores a insertar en la tabla de ventas
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("importe", importe);
        values.put("unidades", unidades);

        // Insertar los valores en la tabla de ventas
        db.insert(DbHelper.TABLE_SALES, null, values);

        db.close(); // Cerrar la base de datos para liberar recursos
    }
}
