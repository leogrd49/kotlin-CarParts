package com.example.carparts

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import com.example.carparts.databinding.ActivityPiecesBinding

class PiecesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPiecesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPiecesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.allerVersListePieces.setOnClickListener{
            findNavController(R.id.nav_host_fragment).navigate(R.id.listeFragment)
        }
    }
}