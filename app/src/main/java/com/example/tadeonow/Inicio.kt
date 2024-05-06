package com.example.tadeonow

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class
Inicio : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        //Setup
        setup()
    }
    private fun setup() {

        title = "Autentificación"

        findViewById<Button>(R.id.Bt_registrarse_inicio).setOnClickListener {
            if (findViewById<EditText>(R.id.email_inicio).text.isNotEmpty() && findViewById<EditText>(R.id.contraseña_inicio).text.isNotEmpty()) {

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                        findViewById<EditText>(R.id.email_inicio).text.toString(),
                        findViewById<EditText>(R.id.contraseña_inicio).text.toString()).addOnCompleteListener {
                        if (it.isSuccessful) {
                            showhome(it.result?.user?.email ?: "", ProviderType.BASIC)
                        } else {
                            showAlert()
                        }
                    }
            }
        }
        findViewById<Button>(R.id.Bt_inicio_sesion).setOnClickListener{
            if (findViewById<EditText>(R.id.email_inicio).text.isNotEmpty() && findViewById<EditText>(R.id.contraseña_inicio).text.isNotEmpty()) {

                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    findViewById<EditText>(R.id.email_inicio).text.toString(),
                    findViewById<EditText>(R.id.contraseña_inicio).text.toString()).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showhome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            }
        }

    }

    private fun showAlert(){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    private fun showhome(email: String, provider: ProviderType){

        val homeIntent = Intent(this, usuario::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }


}