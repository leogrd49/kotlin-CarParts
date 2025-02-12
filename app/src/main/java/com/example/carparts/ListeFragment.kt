package com.example.carparts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carparts.databinding.FragmentListeBinding

class ListeFragment : Fragment() {

    private val viewModel: PiecesViewModel by activityViewModels()
    private var _binding: FragmentListeBinding? = null
    private val binding get() = _binding!!
    private val favorisList = mutableListOf<Boolean>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.setOnApplyWindowInsetsListener(binding.listePieces) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel.pieces.observe(viewLifecycleOwner) { lesPieces ->
            if (savedInstanceState != null) {
                val savedFavoris = savedInstanceState.getBooleanArray("favorisList")
                if (savedFavoris != null) {
                    favorisList.clear()
                    favorisList.addAll(savedFavoris.toList())
                } else {
                    favorisList.addAll(List(lesPieces.size) { false })
                }
            } else {
                favorisList.addAll(List(lesPieces.size) { false })
            }

            val adapter = PiecesRecyclerAdapter(requireActivity(), lesPieces, favorisList, ::toggleFavorite)

            binding.listePieces.layoutManager = LinearLayoutManager(requireContext())
            binding.listePieces.adapter = adapter
        }
    }

    fun toggleFavorite(position: Int) {
        favorisList[position] = !favorisList[position]
        binding.listePieces.adapter?.notifyItemChanged(position)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBooleanArray("favorisList", favorisList.toBooleanArray())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
