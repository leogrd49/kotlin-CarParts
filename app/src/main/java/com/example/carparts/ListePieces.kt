package com.example.carparts

import PiecesRecyclerAdapter
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.ImageView


class ListePieces : AppCompatActivity() {

    private val favorisList = mutableListOf<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_liste_pieces)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.listePieces)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val arbre = Pieces(
            "Arbre à Cames",
            "L'arbre à cames est une pièce mécanique utilisée, principalement, dans des moteurs à pistons à quatre temps pour la commande synchronisée des soupapes. Il se compose d'une tige cylindrique disposant d'autant de cames que de soupapes à commander indépendamment ou par groupe, glissant sur la queue de soupape, ou sur un renvoi mécanique.",
            "arbre___cames"
        )

        val lesPieces = listOf(arbre, arbre, arbre, arbre, arbre, arbre, arbre, arbre, arbre, arbre, arbre, arbre, arbre, arbre, arbre, arbre)

        if (savedInstanceState != null) {
            val savedFavoris = savedInstanceState.getBooleanArray("favorisList")
            if (savedFavoris != null) {
                favorisList.clear()
                favorisList.addAll(savedFavoris.toList())
            } else {
                for (i in lesPieces.indices) {
                    favorisList.add(false)
                }
            }
        } else {
            for (i in lesPieces.indices) {
                favorisList.add(false)
            }
        }

        val recyclerView = findViewById<RecyclerView>(R.id.listePieces)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = PiecesRecyclerAdapter(this, lesPieces, favorisList) { position ->
            toggleFavorite(position)
        }
    }

    fun toggleFavorite(position: Int) {
        favorisList[position] = !favorisList[position]
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBooleanArray("favorisList", favorisList.toBooleanArray())
    }
}

