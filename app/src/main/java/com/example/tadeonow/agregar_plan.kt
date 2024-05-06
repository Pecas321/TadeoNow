package com.example.tadeonow

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class agregar_plan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_plan)

        val btnAgregarPlan = findViewById<ImageButton>(R.id.bt_agregar_plan)
        btnAgregarPlan.setOnClickListener {
            val intent = Intent(this, crear_plan::class.java)
            startActivity(intent)
        }


    }
}