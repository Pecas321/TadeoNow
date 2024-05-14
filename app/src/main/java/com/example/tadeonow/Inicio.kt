package com.example.tadeonow

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthCredential
import com.google.firebase.auth.GoogleAuthProvider

class
Inicio : AppCompatActivity() {

    private val GOOGLE_SIGN_IN = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        //Setup
        setup()
        session()
    }
    private fun session(){
        val prefs = getSharedPreferences(getString(R.string.prefs_file), MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)

        if(email != null && provider != null){
            showhome(email, ProviderType.valueOf(provider))
        }
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
        findViewById<ImageButton>(R.id.bt_google).setOnClickListener{
            // Configuración

            val googleConf =
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val googleClient = GoogleSignIn.getClient(this, googleConf)
            googleClient.signOut()

            startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == GOOGLE_SIGN_IN){

            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account = task.getResult(ApiException::class.java)

                if (account != null) {

                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)

                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {

                            if (it.isSuccessful) {
                                showhome(account.email ?: "", ProviderType.GOOGLE)
                            } else {
                                showAlert()
                            }
                        }
                }
            } catch (e: ApiException){
                showAlert()

            }
        }
    }


}