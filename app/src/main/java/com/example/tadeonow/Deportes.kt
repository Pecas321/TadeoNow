package com.example.tadeonow


import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Deportes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_deportes)

        val btnUsuario = findViewById<ImageButton>(R.id.bt_usuario_deportes)
        btnUsuario.setOnClickListener {
            val intent = Intent(this, usuario::class.java)
            startActivity(intent)
        }

        val btnAgregarPlan = findViewById<ImageButton>(R.id.crea_deportes)
        btnAgregarPlan.setOnClickListener {
            val intent = Intent(this, agregar_plan::class.java)
            startActivity(intent)
        }
    }
}