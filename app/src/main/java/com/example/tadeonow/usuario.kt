package com.example.tadeonow

import com.example.tadeonow.agregar_plan
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType{
    BASIC
}

class usuario : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)


        val btnInformacion = findViewById<ImageButton>(R.id.image_bt_info)
        btnInformacion.setOnClickListener {
            val intent = Intent(this, Informacion::class.java)
            startActivity(intent)
        }
        val btnCrearPlan = findViewById<ImageButton>(R.id.image_crear_usuario)
        btnCrearPlan.setOnClickListener {
            val intent = Intent(this, agregar_plan::class.java)
            startActivity(intent)
        }



        //Setup

        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setup(email ?: "", provider ?: "")
    }

    private fun setup(email: String, provider: String) {

        title = "Inicio"
        findViewById<TextView>(R.id.email_usuario).text = email
        findViewById<TextView>(R.id.proveedor_usuario).text = provider

        findViewById<Button>(R.id.Bt_cerrar_sesion).setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }

    }
}