package com.example.tadeonow

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Informacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacion)

        val btnUsuario = findViewById<ImageButton>(R.id.bt_usuario_info)
        btnUsuario.setOnClickListener {
            val intent = Intent(this, usuario::class.java)
            startActivity(intent)
        }
        val btnPlanes = findViewById<ImageButton>(R.id.casa_info)
        btnPlanes.setOnClickListener {
            val intent = Intent(this, planes::class.java)
            startActivity(intent)
        }
        val btnAgregarPlan = findViewById<ImageButton>(R.id.crea_info)
        btnAgregarPlan.setOnClickListener {
            val intent = Intent(this, agregar_plan::class.java)
            startActivity(intent)
        }





    }
}