package com.cixteam.balance;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.cixteam.balance.databinding.ActivityMain3Binding;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import android.content.Intent;
import android.view.View;
import android.widget.Button;


public class MainActivity3 extends AppCompatActivity {

    ActivityMain3Binding binding;

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() == null){
            Toast.makeText(this, "CANCELADO", Toast.LENGTH_SHORT).show();
        } else {
            binding.etCodigo.setText(result.getContents());
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding = ActivityMain3Binding.inflate(getLayoutInflater());
       setContentView(binding.getRoot());
        binding.btnScanner.setOnClickListener(view -> escanear());

        Button btnList = findViewById(R.id.btnList);
        btnList.setOnClickListener(v -> {
            // Abrir la nueva actividad (MainActivity5)
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
}
