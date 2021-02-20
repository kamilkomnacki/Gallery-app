package komnacki.gallery.login.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import komnacki.gallery.R
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GalleryFragment : Fragment(R.layout.fragment_gallery) {
    private val viewModel by viewModels<GalleryViewModel>()
    private val adapter: GalleryAdapter = GalleryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("KK: ", "Gallery fragment onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("KK: ", "Gallery fragment onViewCreated")

        setUpViews()

        lifecycleScope.launch {
            viewModel.images.observe(viewLifecycleOwner, {
                    adapter.submitData(lifecycle, it)
            })
        }
    }


    private fun setUpViews() {
        rv_gallery.layoutManager = GridAutofitLayoutManager(requireContext(), 300)
        rv_gallery.adapter = adapter
    }
}