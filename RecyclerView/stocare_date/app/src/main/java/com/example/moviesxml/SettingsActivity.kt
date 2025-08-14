package com.example.moviesxml

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.materialswitch.MaterialSwitch

class SettingsActivity : AppCompatActivity() {

    private lateinit var prefs: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        prefs = PreferencesManager(this)
        when (prefs.getTheme()) {
            PreferencesManager.THEME_DARK -> setTheme(R.style.Theme_MyApp_Dark)
            else -> setTheme(R.style.Theme_MyApp_Light)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener { finish() }

        val switchTheme = findViewById<MaterialSwitch>(R.id.switchTheme)
        val btnSave = findViewById<Button>(R.id.btnSave)

        switchTheme.isChecked = prefs.getTheme() == PreferencesManager.THEME_DARK

        switchTheme.setOnCheckedChangeListener { _, isChecked ->
            val newTheme = if (isChecked) PreferencesManager.THEME_DARK else PreferencesManager.THEME_LIGHT
            prefs.saveTheme(newTheme)
            recreate()
        }

        btnSave.setOnClickListener {
            val newTheme = if (switchTheme.isChecked) PreferencesManager.THEME_DARK else PreferencesManager.THEME_LIGHT
            prefs.saveTheme(newTheme)
            recreate()
        }
    }
}
