package com.example.carparts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PiecesViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _favoris = MutableStateFlow(savedStateHandle.get<ArrayList<Boolean>>("favoris") ?: ArrayList(List(16) { false }))
    val favoris = _favoris.asStateFlow()

    fun toggleFavorite(position: Int) {
        val currentFavoris = ArrayList(_favoris.value)
        currentFavoris[position] = !currentFavoris[position]
        _favoris.value = currentFavoris
        savedStateHandle.set("favoris", currentFavoris)
    }
}