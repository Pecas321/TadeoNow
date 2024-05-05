package com.example.tadeonow

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Inicio : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)


        val botonInicioSesion = findViewById<Button>(R.id.Bt_inicio_sesion)
        botonInicioSesion.setOnClickListener {

            val intent = Intent(this, IniciarSesion::class.java)
            startActivity(intent)
        }
        val botonRegistrarseInicio = findViewById<Button>(R.id.Bt_registrarse_inicio)
        botonRegistrarseInicio.setOnClickListener {

            val intent = Intent(this, Registrarse::class.java)
            startActivity(intent)
        }

        //Setup
        setup()
    }
    private fun setup() {

        title = "Autentificación"

        findViewById<Button>(R.id.Bt_registrarse_inicio).setOnClickListener {
            if (findViewById<Button>(R.id.email_inicio).text.isNotEmpty() && findViewById<Button>(R.id.contraseña_inicio).text.isNotEmpty()) {

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                        findViewById<Button>(R.id.email_inicio).text.toString(),
                        findViewById<Button>(R.id.contraseña_inicio).text.toString()).addOnCompleteListener {
                        if (it.isSuccessful) {
                            showhome(it.result?.user?.email ?: "", ProviderType.BASIC)
                        } else {
                            showAlert()
                        }
                    }
            }
        }
        findViewById<Button>(R.id.Bt_inicio_sesion).setOnClickListener{
            if (findViewById<Button>(R.id.email_inicio).text.isNotEmpty() && findViewById<Button>(R.id.contraseña_inicio).text.isNotEmpty()) {

                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    findViewById<Button>(R.id.email_inicio).text.toString(),
                    findViewById<Button>(R.id.contraseña_inicio).text.toString()).addOnCompleteListener {
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