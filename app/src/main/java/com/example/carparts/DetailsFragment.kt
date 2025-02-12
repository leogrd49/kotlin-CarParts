package com.example.carparts

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.carparts.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private val viewModel: PiecesViewModel by activityViewModels()
    private var pieceId: Int = 0
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pieceId = args.pieceId
        Log.d("DetailsFragment", "pieceId: $pieceId")

        val piece = viewModel.pieces.value?.get(pieceId)

        piece?.let {
            binding.detailTitreTv.text = it.nom
            binding.detailsDescriptionTv.text = it.description

            binding.largeurTv.text = "${it.largeur} mm"
            binding.longueurTv.text = "${it.longueur} mm"

            val imageResId = resources.getIdentifier(it.image, "drawable", context?.packageName)
            if (imageResId != 0) {
                binding.detailsImageIv.setImageResource(imageResId)
            }
        }

        binding.detailsPlusInformationsTv.setOnClickListener {
            val url = piece?.url ?: ""
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }

    private fun allerVersListe() {
        Toast.makeText(requireContext(), "Navigation vers la liste", Toast.LENGTH_SHORT).show()
    }

    private fun allerVersCarte() {
        Toast.makeText(requireContext(), "Navigation vers la carte", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
