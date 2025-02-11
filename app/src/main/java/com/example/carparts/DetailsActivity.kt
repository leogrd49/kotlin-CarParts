package com.example.carparts

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.carparts.databinding.ActivityDetailsBinding
import android.widget.Toast
import android.util.Log


class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.allerVersListePieces.setOnClickListener {
            Log.d("DetailsActivity", "Bouton Liste cliqué")
            allerVersListe() }
        binding.allerVersCartePieces.setOnClickListener {
            Log.d("DetailsActivity", "Bouton Carte cliqué")
            allerVersCarte() }

        binding.detailsPlusInformationsTv.setOnClickListener {
            val url = "https://fr.wikipedia.org/wiki/Arbre_à_cames"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }

    private fun allerVersListe() {
        Toast.makeText(this, "Navigation vers la liste", Toast.LENGTH_SHORT).show()
    }

    private fun allerVersCarte() {
        Toast.makeText(this, "Navigation vers la carte", Toast.LENGTH_SHORT).show()
    }
}

