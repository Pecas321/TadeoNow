package com.example.tadeonow

import com.example.tadeonow.agregar_plan
import android.content.Intent
import android.os.Bundle
import android.widget.Button
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

        val btnAgregarPlan = findViewById<ImageButton>(R.id.crea_info)
        btnAgregarPlan.setOnClickListener {
            val intent = Intent(this, agregar_plan::class.java)
            startActivity(intent)
        }

        val btnDeportes = findViewById<Button>(R.id.Bt_deportes)
        btnDeportes.setOnClickListener {
            val intent = Intent(this, Deportes::class.java)
            startActivity(intent)
        }
        val btnBiblioteca = findViewById<Button>(R.id.Bt_biblioteca)
        btnBiblioteca.setOnClickListener {
            val intent = Intent(this, Biblioteca::class.java)
            startActivity(intent)
        }





    }
}