package com.example.carparts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PiecesViewModel : ViewModel() {

    private val _pieces = MutableLiveData<List<Pieces>>()
    private val _favorisList = MutableLiveData<List<Boolean>>()

    val pieces: LiveData<List<Pieces>> get() = _pieces
    val favorisList: LiveData<List<Boolean>> get() = _favorisList

    init {
        _pieces.value = listOf(
            Pieces(
                "Arbre à cames",
                "L'arbre à cames est une pièce mécanique utilisée, principalement, dans des moteurs à pistons à quatre temps pour la commande synchronisée des soupapes. Il scompose d'une tige cylindrique disposant d'autant de cames que de soupapes à commander indépendamment ou par groupe, glissant sur la queue de soupape, ou sur un renvoi mécanique.",
                "arbre___cames",
                "https://fr.wikipedia.org/wiki/Arbre_à_cames",
                62,
                424
            ),
            Pieces(
                "Injecteur",
                "Composant qui pulvérise le carburant sous pression dans la chambre de combustion pour une meilleure combustion.",
                "injecteur",
                "https://fr.wikipedia.org/wiki/Injecteur#:~:text=Injecteur%20de%20moteur,-Animation%20d%27un&text=L%27injecteur%20permet%20l%27apport,essence%20qui%20retient%20les%20impuretés.",
                14,
                70
            ),
            Pieces(
                "Bougie d'allumage",
                "Élément qui génère une étincelle pour enflammer le mélange air-carburant dans un moteur essence.",
                "bougie",
                "https://fr.wikipedia.org/wiki/Bougie_d%27allumage",
                13,
                40
            ),
            Pieces(
                "Piston",
                "Composant mobile dans le cylindre qui comprime le mélange air-carburant et transmet la force de l'explosion au vilebrequin.",
                "piston",
                "https://fr.wikipedia.org/wiki/Piston_(mécanique)#:~:text=Le%20piston%20est%20l%27élément,un%20mouvement%20rotatif%20du%20vilebrequin.",
                75,
                55
            ),
            Pieces(
                "vilebrequin",
                "Pièce tournante qui convertit le mouvement linéaire des pistons en mouvement rotatif pour entraîner les roues.",
                "vilebrequin",
                "https://fr.wikipedia.org/wiki/Vilebrequin_(moteur)#:~:text=Le%20vilebrequin%20est%20un%20dispositif,de%20rotation%20continu%2C%20et%20inversement.",
                60,
                500
            )
        )
        _favorisList.value =
            List(_pieces.value?.size ?: 0) { false }
    }

    fun updateFavoris(newFavorisList: List<Boolean>) {
        _favorisList.value = newFavorisList
    }
}