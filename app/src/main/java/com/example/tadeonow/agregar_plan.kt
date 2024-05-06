package com.example.tadeonow

import com.example.tadeonow.planes
import com.example.tadeonow.PlanesAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore


class agregar_plan : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PlanesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_plan)

        val imageButtonUserPlanes = findViewById<ImageButton>(R.id.imageButton_user_planes)
        imageButtonUserPlanes.setOnClickListener {
            val intent = Intent(this, usuario::class.java)
            startActivity(intent)
        }

        val btnAgregarPlan = findViewById<ImageButton>(R.id.bt_agregar_plan)
        btnAgregarPlan.setOnClickListener {
            val intent = Intent(this, crear_plan::class.java)
            startActivity(intent)
        }

        val imageButtonInfoPlanes = findViewById<ImageButton>(R.id.image_bt_info_planes)
        imageButtonInfoPlanes.setOnClickListener {
            val intent = Intent(this, Informacion::class.java)
            startActivity(intent)
        }


        // Inicializar Firebase Firestore
        firestore = FirebaseFirestore.getInstance()

        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PlanesAdapter()
        recyclerView.adapter = adapter

        // Obtener y mostrar los datos de Firestore
        obtenerDatosFirestore()
    }

    private fun obtenerDatosFirestore() {
        // Obtener la colección "planes" de Firestore
        firestore.collection("planes")
            .get()
            .addOnSuccessListener { documents ->
                // Convertir los documentos en una lista de Planes
                val planesList = mutableListOf<planes>()
                for (document in documents) {
                    val nombre = document.getString("nombre") ?: ""
                    val horario = document.getString("horario") ?: ""
                    val ubicacion = document.getString("ubicacion") ?: ""
                    val asistentes = document.getLong("asistentes")?.toInt() ?: 0
                    val plan = planes(nombre, horario, ubicacion, asistentes)
                    planesList.add(plan)
                }
                // Mostrar la lista de Planes en el RecyclerView
                adapter.setPlanes(planesList)
            }
            .addOnFailureListener { exception ->
                // Manejar errores
            }
    }
}