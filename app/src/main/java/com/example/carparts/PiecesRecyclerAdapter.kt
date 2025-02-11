import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.carparts.Pieces
import com.example.carparts.R

class PiecesRecyclerAdapter(
    private val context: Context,
    private val piecesList: List<Pieces>,
    private val favorisList: MutableList<Boolean>,
    private val toggleFavoriteListener: (Int) -> Unit
) : RecyclerView.Adapter<PiecesRecyclerAdapter.PiecesViewHolder>() {

    inner class PiecesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val heartButton: CheckBox = itemView.findViewById(R.id.likeButton)
        val nomPiece: TextView = itemView.findViewById(R.id.nomPiece)
        val descriptionPiece: TextView = itemView.findViewById(R.id.descriptionPiece)

        init {
           heartButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    toggleFavoriteListener(position)
                    heartButton.isSelected = favorisList[position]
                    notifyItemChanged(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PiecesViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_piece, parent, false)
        return PiecesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PiecesViewHolder, position: Int) {
        val currentPiece = piecesList[position]
        holder.nomPiece.text = currentPiece.nom
        holder.descriptionPiece.text = currentPiece.description
        holder.heartButton.isSelected = favorisList[position]
    }

    override fun getItemCount() = piecesList.size
}


