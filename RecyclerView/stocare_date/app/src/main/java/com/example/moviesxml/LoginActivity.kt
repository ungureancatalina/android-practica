package com.example.moviesxml

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var prefs: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        prefs = PreferencesManager(this)
        when (prefs.getTheme()) {
            PreferencesManager.THEME_DARK -> setTheme(R.style.Theme_MyApp_Dark)
            else -> setTheme(R.style.Theme_MyApp_Light)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        prefs.getUsername()?.let { etUsername.setText(it) }

        btnLogin.setOnClickListener {
            val user = etUsername.text.toString().trim()
            val pass = etPassword.text.toString()

            if (user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Completeaza user si parola", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            prefs.saveUsername(user)

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
