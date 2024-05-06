package com.example.tadeonow

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore

class crear_plan : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_crear_plan)

        // Inicializar Firebase Firestore
        firestore = FirebaseFirestore.getInstance()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obtener referencias de los EditText y el botón
        val nombrePlanEditText = findViewById<EditText>(R.id.editText_nombrePlan)
        val horarioEditText = findViewById<EditText>(R.id.editTextText2)
        val ubicacionEditText = findViewById<EditText>(R.id.editTextText3)
        val asistentesEditText = findViewById<EditText>(R.id.editTextText4)
        val crearButton = findViewById<Button>(R.id.btn_crear)

        // Configurar el evento clic del botón "CREAR"
        crearButton.setOnClickListener {
            // Obtener los valores de los EditText
            val nombrePlan = nombrePlanEditText.text.toString()
            val horario = horarioEditText.text.toString()
            val ubicacion = ubicacionEditText.text.toString()
            val asistentes = asistentesEditText.text.toString().toInt() // Suponiendo que la cantidad de asistentes es un número entero

            // Crear un mapa con los datos a guardar en Firestore
            val plan = hashMapOf(
                "nombre" to nombrePlan,
                "horario" to horario,
                "ubicacion" to ubicacion,
                "asistentes" to asistentes
            )

            // Guardar los datos en Firestore
            firestore.collection("planes").add(plan)
                .addOnSuccessListener { documentReference ->
                    // Éxito al guardar los datos
                    // Puedes mostrar un mensaje de éxito o realizar alguna otra acción aquí
                }
                .addOnFailureListener { e ->
                    // Error al guardar los datos
                    // Puedes mostrar un mensaje de error o realizar alguna otra acción aquí
                }
        }
    }
}