package komnacki.gallery.login.ui.detail

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import komnacki.gallery.R
import komnacki.gallery.login.ui.gallery.GalleryViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val viewModel by activityViewModels<GalleryViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iv_detail.load(viewModel.selected.value?.urls?.small) {
            placeholder(ContextCompat.getDrawable(requireContext(), R.color.secondary_light))
        }
    }
}