package com.example.tadeonow

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import android.app.Activity
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Registrarse : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)

    }

    private fun setup() {

        title = "Autentificación"

        findViewById<Button>(R.id.bt_registrarse).setOnClickListener {
            if (findViewById<Button>(R.id.emailEditText).text.isNotEmpty() && findViewById<Button>(R.id.passwordEditText).text.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(
                        findViewById<Button>(R.id.emailEditText).text.toString(),
                        findViewById<Button>(R.id.passwordEditText).text.toString()
                    ).addOnCompleteListener {
                        if (it.isSuccessful) {
                            showhome(it.result?.user?.email ?: "", ProviderType.BASIC)
                        } else {
                            showAlert()
                        }
                    }
            }
        }
    }

}