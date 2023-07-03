package com.cixteam.balance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        btnOpenStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir la nueva actividad (MainActivity3)
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
            }
        });

    }
}
