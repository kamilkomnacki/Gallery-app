package komnacki.gallery.ui.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import komnacki.gallery.R
import komnacki.gallery.domain.model.Image
import kotlinx.android.synthetic.main.item_gallery.view.*

class GalleryAdapter(
    private val itemClickListener: (Image) -> Unit
) : PagingDataAdapter<Image, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Image>() {
            override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean =
                oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImageHolder.getInstance(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ImageHolder).onBind(getItem(position), itemClickListener)
    }

    class ImageHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        companion object {
            fun getInstance(parent: ViewGroup): ImageHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.item_gallery, parent, false)
                return ImageHolder(view)
            }
        }

        fun onBind(item: Image?, onItemClickListener: (Image) -> Unit) {
            itemView.run {
                ivItem.load(item?.urls?.small) {
                    placeholder(ContextCompat.getDrawable(context, R.color.secondary_light))
                }

                item?.let {
                    setOnClickListener {
                        onItemClickListener.invoke(item)
                    }
                }
            }
        }
    }
}