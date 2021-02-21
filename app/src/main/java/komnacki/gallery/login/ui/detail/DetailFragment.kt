package komnacki.gallery.login.ui.detail

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import coil.request.Disposable
import komnacki.gallery.R
import komnacki.gallery.login.ui.gallery.GalleryViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val viewModel by activityViewModels<GalleryViewModel>()
    private lateinit var disposable : Disposable

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        disposable = iv_detail.load(viewModel.selected.value?.urls?.small) {
            placeholder(ContextCompat.getDrawable(requireContext(), R.color.secondary_light))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if(disposable.isDisposed.not()) {
            disposable.dispose()
        }
    }
}