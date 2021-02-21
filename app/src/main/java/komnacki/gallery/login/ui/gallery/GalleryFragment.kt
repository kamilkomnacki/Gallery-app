package komnacki.gallery.login.ui.gallery

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import komnacki.gallery.R
import komnacki.gallery.login.Constants
import kotlinx.android.synthetic.main.fragment_gallery.*

@AndroidEntryPoint
class GalleryFragment : Fragment(R.layout.fragment_gallery) {

    private companion object {
        const val IS_DIALOG_VISIBLE = "IS_DIALOG_VISIBLE"
    }

    private val viewModel by activityViewModels<GalleryViewModel>()
    private val adapter: GalleryAdapter = GalleryAdapter {
        viewModel.select(it)
        if(viewModel.isUserLogIn()) {
            this.findNavController().navigate(R.id.toDetailFragment)
        } else {
            dialog.show()
        }
    }
    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog = getLoginDialog(requireContext())

        val isDialogVisible = savedInstanceState?.getBoolean(IS_DIALOG_VISIBLE)
        if (isDialogVisible == true) {
            dialog.show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpStateListener { showSnackbarError(view) }
        setUpViews()

        viewModel.images.observe(viewLifecycleOwner, {
            adapter.submitData(lifecycle, it)
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(IS_DIALOG_VISIBLE, dialog.isShowing)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dialog.dismiss()
    }

    private fun setUpStateListener(onError: () -> Unit) {
        adapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                progress_gallery.show()
            } else {
                progress_gallery.hide()

                val errorState = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    onError.invoke()
                }
            }
        }
    }

    private fun setUpViews() {
        rv_gallery.layoutManager = GridAutofillLayoutManager(requireContext(), Constants.GALLERY_DEFAULT_COLUMN_WIDTH)
        rv_gallery.adapter = adapter
    }

    private fun showSnackbarError(parent: View) {
        Snackbar.make(parent, getString(R.string.gallery_snackbar_message), Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.gallery_retry_button)) {
                adapter.retry()
            }.show()
    }

    private fun getLoginDialog(context: Context): AlertDialog {
        return AlertDialog.Builder(context)
            .setTitle(getString(R.string.gallery_dialog_title))
            .setPositiveButton(getString(R.string.gallery_dialog_positive_button)) { dialogInterface, i ->
                this.findNavController().navigate(R.id.toLoginFragment)
            }
            .setNegativeButton(getString(R.string.gallery_dialog_negative_button)) { dialogInterface, i ->
                dialogInterface.dismiss()
            }.create()
    }
}