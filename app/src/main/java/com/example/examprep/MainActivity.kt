package com.example.examprep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = getSharedPreferences("auth", MODE_PRIVATE)

        val sLogin = prefs.getString("login","")
        val sPass = prefs.getString("pass", "")

        var fragment =
        if (sLogin != "" && sPass !="")
        {
            ShapeSelectionFragment()
        }
        else
        {
            LoginFragment()
        }

        if (savedInstanceState == null)
        {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container,fragment)
                .commit()
        }
    }
}