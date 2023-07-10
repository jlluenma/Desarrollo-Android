package com.cixteam.balance;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.cixteam.balance.databinding.ActivityMain4Binding;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;
public class MainActivity4 extends AppCompatActivity {

    ActivityMain4Binding binding;

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
        binding = ActivityMain4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnScanner.setOnClickListener(view -> escanear());
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