package com.cixteam.balance;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    private EditText nameUsr;
    private String userInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameUsr = findViewById(R.id.nameUsr);

        Button btnOpenActivity = findViewById(R.id.btnOpenActivity);
        btnOpenActivity.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
            userInput = nameUsr.getText().toString();

            intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.putExtra("userInput", userInput);
            startActivity(intent);

        });
    }
}
