package com.example.xml_incercare

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.xml_incercare.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextNumber = findViewById<EditText>(R.id.editTextNumber)
        val buttonCheck = findViewById<Button>(R.id.buttonCheck)
        val textResult = findViewById<TextView>(R.id.textResult)

        buttonCheck.setOnClickListener {
            val input = editTextNumber.text.toString()
            val number = input.toIntOrNull()

            textResult.text = when {
                number == null -> "Please enter a valid number."
                number % 2 == 0 -> "$number is even."
                else -> "$number is odd."
            }
        }
    }
}
