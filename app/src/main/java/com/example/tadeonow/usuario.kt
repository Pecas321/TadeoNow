package com.example.tadeonow

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

enum class ProviderType{
    BASIC
}

class usuario : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)

        //Setup
        setup()
    }

    private fun setup() {

        title = "Inicio"
    }
}