package komnacki.gallery.ui.detail

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import coil.request.Disposable
import komnacki.gallery.R
import komnacki.gallery.domain.model.Image
import komnacki.gallery.ui.gallery.GalleryViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val viewModel by activityViewModels<GalleryViewModel>()
    private lateinit var disposable : Disposable
    private var currentInfoState = 0

    private companion object CONSTANT {
        const val CURRENT_INFO_STATE = "CURRENT_INFO_STATE"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item = viewModel.selected.value

        disposable = iv_detail.load(item?.urls?.small) {
            placeholder(ContextCompat.getDrawable(requireContext(), R.color.secondary_light))
        }

        setAuthorText(item)
        setDescriptionText(item)

        savedInstanceState?.let {
            currentInfoState = it.getInt(CURRENT_INFO_STATE)
            ml_detail.transitionToState(currentInfoState)
        }

        iv_detail.setOnClickListener {
            onFragmentClicked()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(CURRENT_INFO_STATE, ml_detail.currentState)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if(disposable.isDisposed.not()) {
            disposable.dispose()
        }
    }

    private fun onFragmentClicked() {
        currentInfoState = ml_detail.currentState
        if (currentInfoState == ml_detail.endState) {
            ml_detail.transitionToStart()
        } else {
            ml_detail.transitionToEnd()
        }
    }

    private fun setDescriptionText(item: Image?) {
        item?.description?.let {
            tv_description.visibility = View.VISIBLE
            tv_description.text = it
        }
    }

    private fun setAuthorText(item: Image?) {
        item?.user?.let {
            tv_author.visibility = View.VISIBLE
            tv_author.text = String.format(getString(R.string.detail_author_pattern), it.username)
        }
    }
}