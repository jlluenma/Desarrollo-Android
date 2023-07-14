package com.cixteam.balance;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cixteam.balance.databinding.ActivityMain4Binding;
import com.cixteam.balance.db.DbHelper;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class MainActivity4 extends AppCompatActivity {

    ActivityMain4Binding binding;

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(), result -> {
        String scannedResult = result.getContents(); // Guardar el resultado del escaneo en una variable

        if (scannedResult == null) {
            Toast.makeText(this, "CANCELADO", Toast.LENGTH_SHORT).show();
        } else {
            // Realizar la consulta a la tabla TABLE_PRODUCTS en el DbHelper
            DbHelper dbHelper = new DbHelper(this);
            String nombre = "";
            String tipo = "";

            // Realizar la consulta y obtener los datos correspondientes al código escaneado
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String[] projection = {"nombre", "tipo"};
            String selection = "code = ?";
            String[] selectionArgs = {scannedResult};
            Cursor cursor = db.query(DbHelper.TABLE_PRODUCTS, projection, selection, selectionArgs, null, null, null);

            // Verificar si el cursor contiene algún resultado
            if (cursor.moveToFirst()) {
                // Obtener los datos (nombre y tipo)
                nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
                tipo = cursor.getString(cursor.getColumnIndexOrThrow("tipo"));
            }

            cursor.close();
            db.close();

            // Realizar alguna acción con los datos obtenidos (nombre y tipo) en la actividad 4
            // Puedes pasar los datos a la actividad 4 utilizando un Intent o almacenarlos en variables globales
            // Ejemplo de cómo pasar los datos a la actividad 4:
            Intent intent = new Intent(MainActivity4.this, MainActivity6.class);
            intent.putExtra("nombreProducto", nombre);
            intent.putExtra("tipoProducto", tipo);
            startActivity(intent);
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        binding = ActivityMain4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnScanner.setOnClickListener(view -> escanear());
    }

    public void btnWriteSales(View view){
        Intent btnWriteSales = new Intent(this, MainActivity6.class);
        startActivity(btnWriteSales);
    }

    public void escanear(){
        ScanOptions options = new ScanOptions();
        options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES);
        options.setPrompt("ESCANEAR CÓDIGO");
        options.setCameraId(0);
        options.setOrientationLocked(false);
        options.setBeepEnabled(true);
        options.setCaptureActivity(CaptureActivityPortrait.class);
        options.setBarcodeImageEnabled(false);
        barcodeLauncher.launch(options);
}}
