package com.cixteam.balance;

import android.content.Intent;
import android.os.Bundle;
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
    }
}
