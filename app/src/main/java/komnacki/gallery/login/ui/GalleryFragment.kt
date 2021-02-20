package komnacki.gallery.login.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import komnacki.gallery.R
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GalleryFragment : Fragment(R.layout.fragment_gallery) {
    private val viewModel by viewModels<GalleryViewModel>()
    private lateinit var adapter: GalleryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = GalleryAdapter()
        setUpViews()

        lifecycleScope.launch {
            viewModel.fetchImages()
                .distinctUntilChanged()
                .collectLatest {
                    adapter.submitData(it)
                }
        }
    }


    private fun setUpViews() {
        rv_gallery.layoutManager = GridLayoutManager(context, 2)
        rv_gallery.adapter = adapter
    }
}