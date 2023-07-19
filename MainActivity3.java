package com.cixteam.balance;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.cixteam.balance.db.DbHelper;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import android.content.Intent;
import android.widget.Button;

public class MainActivity3 extends AppCompatActivity {

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() == null){
            Toast.makeText(this, "CANCELADO", Toast.LENGTH_SHORT).show();
        } else {
            String scannedValue = result.getContents();

            DbHelper dbHelper = new DbHelper(this);
            SQLiteDatabase database = dbHelper.getReadableDatabase();

            obtenerDatosProducto(scannedValue);
        }
    });

    private DbHelper dbHelper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        dbHelper = new DbHelper(this);
        database = dbHelper.getReadableDatabase();

        Button btnScanner = findViewById(R.id.btnScanner);
        btnScanner.setOnClickListener(view -> escanear());

        Button btnList = findViewById(R.id.btnList);
        btnList.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity3.this, MainActivity5.class);
            startActivity(intent);
        });
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
    }

    private void obtenerDatosProducto(String scannedValue) {
        String[] columns = {"nombre", "tipo"};
        String selection = "code = ?";
        String[] selectionArgs = {scannedValue};

        Cursor cursor = database.query(DbHelper.TABLE_PRODUCTS, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            String nombreProducto = getString(cursor.getColumnIndex("nombre"));
            String tipoProducto = getString(cursor.getColumnIndex("tipo"));
            TextView editTextNombreProducto = findViewById(R.id.editTextNombreProducto);
            TextView editTextTipoProducto = findViewById(R.id.editTextTipoProducto);

            editTextNombreProducto.setText(nombreProducto);
            editTextTipoProducto.setText(tipoProducto);

            insertarProducto(nombreProducto, tipoProducto, scannedValue);
        }

        cursor.close();
    }

    private void insertarProducto(String nombre, String tipo, String code) {
        SQLiteDatabase writableDatabase = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("tipo", tipo);
        values.put("code", code);

        long id = writableDatabase.insert(DbHelper.TABLE_STOCK, null, values);
        if (id != -1) {
            Toast.makeText(this, "Producto agregado a la tabla de Stock.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al agregar el producto a la tabla de Stock.", Toast.LENGTH_SHORT).show();
        }
        writableDatabase.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();
        database.close();
    }
}
