package com.jlluenma.helloandroidstudio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var counterTextView: TextView
    private var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        counterTextView = findViewById(R.id.counterTextView)

        button.setOnClickListener {
            counter++
            counterTextView.text = "Contador: $counter"
        }
    }
}

"Este código se copiará en la carpeta 'MainActivity.kt' de la carpeta 'com.jlluenma.helloandroidstudio' de la carpeta 'java' "
