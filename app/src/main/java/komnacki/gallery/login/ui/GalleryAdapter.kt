package komnacki.gallery.login.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import komnacki.gallery.R
import kotlinx.android.synthetic.main.item_gallery.view.*

class GalleryAdapter : PagingDataAdapter<String, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem


            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ImageHolder).onBind(item = getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImageHolder.getInstance(parent)
    }

    class ImageHolder(view : View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun getInstance(parent: ViewGroup) : ImageHolder {
                val inflater  = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.item_gallery, parent, false)
                return ImageHolder(view)
            }
        }

        fun onBind(item : String?) {
            itemView.run {
                ivItem.load(item) {
                    placeholder(ContextCompat.getDrawable(context, R.color.colorAccent))
                }
            }
        }
    }

}