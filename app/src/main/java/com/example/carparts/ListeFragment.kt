package com.example.carparts

import PiecesRecyclerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carparts.databinding.FragmentListeBinding
import kotlinx.coroutines.launch

class ListeFragment : Fragment() {

    private var _binding: FragmentListeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PiecesViewModel by viewModels()
    private lateinit var piecesAdapter: PiecesRecyclerAdapter

    private val pieces by lazy {
        val arbre = Pieces(
            "Arbre à Cames",
            "L'arbre à cames est une pièce mécanique utilisée, principalement, dans des moteurs à pistons à quatre temps pour la commande synchronisée des soupapes. Il se compose d'une tige cylindrique disposant d'autant de cames que de soupapes à commander indépendamment ou par groupe, glissant sur la queue de soupape, ou sur un renvoi mécanique.",
            "arbre___cames"
        )
        List(16) { arbre }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupWindowInsets()
        setupRecyclerView()
        observeFavoris()
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.listePieces) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupRecyclerView() {
        piecesAdapter = PiecesRecyclerAdapter(
            requireActivity(),
            pieces,
            ArrayList(viewModel.favoris.value)
        ) { position ->
            viewModel.toggleFavorite(position)
        }

        binding.listePieces.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = piecesAdapter
        }
    }

    private fun observeFavoris() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.favoris.collect { favoris ->
                piecesAdapter.updateFavoris(ArrayList(favoris))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}