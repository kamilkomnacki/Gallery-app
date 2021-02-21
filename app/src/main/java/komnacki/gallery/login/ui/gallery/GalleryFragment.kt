package komnacki.gallery.login.ui.gallery

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import komnacki.gallery.R
import kotlinx.android.synthetic.main.fragment_gallery.*

@AndroidEntryPoint
class GalleryFragment : Fragment(R.layout.fragment_gallery) {

    private companion object Constants {
        const val IS_DIALOG_VISIBLE = "IS_DIALOG_VISIBLE"

    }
    private val viewModel by viewModels<GalleryViewModel>()
    private val adapter: GalleryAdapter = GalleryAdapter {
//        if ((0..3).random().rem(2) == 0) {
        dialog.show()
//        }
    }
    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("KK: ", "Gallery fragment onCreate")

        dialog = getLoginDialog(requireContext())

        val isDialogVisible = savedInstanceState?.getBoolean(IS_DIALOG_VISIBLE)
        if (isDialogVisible == true) {
            dialog.show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("KK: ", "Gallery fragment onViewCreated")

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
        rv_gallery.layoutManager = GridAutofillLayoutManager(requireContext(), 300)
        rv_gallery.adapter = adapter
    }

    private fun showSnackbarError(parent: View) {
        Snackbar.make(parent, "Something went wrong, please try again", Snackbar.LENGTH_INDEFINITE)
            .setAction("Retry") {
                adapter.retry()
            }.show()
    }

    private fun getLoginDialog(context: Context): AlertDialog {
        return AlertDialog.Builder(context)
            .setTitle("Account Required")
            .setPositiveButton("Sing in") { dialogInterface, i ->
                Log.d("KK: ", "login")
            }
            .setNegativeButton("Cancel") { dialogInterface, i ->
                Log.d("KK: ", "login cancel")
                dialogInterface.dismiss()
            }.create()
    }
}