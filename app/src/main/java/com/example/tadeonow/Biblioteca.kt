package com.example.tadeonow

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Biblioteca : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biblioteca)
        val btnUsuario = findViewById<ImageButton>(R.id.bt_usuario_biblioteca)
        btnUsuario.setOnClickListener {
            val intent = Intent(this, usuario::class.java)
            startActivity(intent)
        }
        val btnPlanes = findViewById<ImageButton>(R.id.casa_biblioteca)
        btnPlanes.setOnClickListener {
            val intent = Intent(this, planes::class.java)
            startActivity(intent)
        }
        val btnAgregarPlan = findViewById<ImageButton>(R.id.crea_biblioteca)
        btnAgregarPlan.setOnClickListener {
            val intent = Intent(this, agregar_plan::class.java)
            startActivity(intent)
        }

    }
}