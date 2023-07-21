package com.cixteam.balance;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
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

    // Este lanzador se utilizará para obtener el resultado del escaneo
    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(), result -> {
        String scannedResult = result.getContents(); // Guardar el resultado del escaneo en una variable

        if (scannedResult == null) {
            // Mostrar mensaje si se canceló el escaneo
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
                // Obtener los datos (nombre y tipo) de la consulta
                nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
                tipo = cursor.getString(cursor.getColumnIndexOrThrow("tipo"));

                // Insertar el producto en la tabla TABLE_SALES
                ContentValues values = new ContentValues();
                values.put("nombre", nombre);
                values.put("tipo", tipo);
                values.put("importe", 0.0); // Ajusta el valor del importe según corresponda
                values.put("unidades", 0.0); // Ajusta el valor de las unidades según corresponda
                values.put("code", scannedResult);
                db.insert(DbHelper.TABLE_SALES, null, values);

                // Mostrar mensaje de éxito
                Toast.makeText(this, "PRODUCTO VENDIDO", Toast.LENGTH_SHORT).show();
            }
            cursor.close();
            db.close();
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        // Binding para vincular los elementos de la interfaz de usuario
        binding = ActivityMain4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configurar el botón para iniciar el escaneo
        binding.btnScanner.setOnClickListener(view -> escanear());
    }

    // Método para navegar a la actividad 6 mediante un botón (no utilizado en el código actual)
    public void btnWriteSales(View view){
        Intent btnWriteSales = new Intent(this, MainActivity6.class);
        startActivity(btnWriteSales);
    }

    // Método para iniciar el escaneo del código de barras
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
    }
}
