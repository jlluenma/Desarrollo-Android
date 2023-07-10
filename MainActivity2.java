package com.cixteam.balance;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String userInput = intent.getStringExtra("userInput");

        TextView userTextView = findViewById(R.id.userTextView);
        userTextView.setText(userInput);

        Button btnOpenStock = findViewById(R.id.btnOpenStock);
        btnOpenStock.setOnClickListener(v -> {
            // Abrir la nueva actividad (MainActivity3)
            Intent intent1 = new Intent(MainActivity2.this, MainActivity3.class);
            startActivity(intent1);
        });
        Button btnOpenSales = findViewById(R.id.btnOpenSales);
        btnOpenSales.setOnClickListener(v -> {
            // Abrir la nueva actividad (MainActivity4)
            Intent intent2 = new Intent(MainActivity2.this, MainActivity4.class);
            startActivity(intent2);
        });
    }
}
