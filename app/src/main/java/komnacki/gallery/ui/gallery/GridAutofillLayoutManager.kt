package komnacki.gallery.ui.gallery

import android.content.Context
import android.util.TypedValue
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import kotlin.math.max


class GridAutofillLayoutManager(
    context: Context,
    columnWidth: Int
) : GridLayoutManager(context, INITIAL_SPAN_COUNT) {

    private companion object CONSTANTS {
        const val INITIAL_SPAN_COUNT = 1
        const val DEFAULT_COLUMN_WIDTH = 48f
    }

    private var columnWidth = 0
    private var isColumnWidthChanged = true
    private var lastWidth = 0
    private var lastHeight = 0

    init {
        setColumnWidth(
            verifyColumnWidth(context, columnWidth)
        )
    }

    private fun setColumnWidth(newColumnWidth: Int) {
        if (columnWidth != newColumnWidth) {
            columnWidth = newColumnWidth
            isColumnWidthChanged = true
        }
    }

    private fun verifyColumnWidth(context: Context, columnWidth: Int): Int {
        return if (columnWidth <= 0) {
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                DEFAULT_COLUMN_WIDTH,
                context.resources.displayMetrics
            ).toInt()
        } else {
            columnWidth
        }
    }

    override fun onLayoutChildren(recycler: Recycler, state: RecyclerView.State) {
        if (isLayoutChange()) {
            val totalSpace = getTotalSpace(width, height)
            val spanCount = max(INITIAL_SPAN_COUNT, getColumnCount(totalSpace))
            setSpanCount(spanCount)
            isColumnWidthChanged = false
        }
        lastWidth = width
        lastHeight = height
        super.onLayoutChildren(recycler, state)
    }

    private fun isLayoutChange() =
        columnWidth > 0 && width > 0 && height > 0 && (isColumnWidthChanged || lastWidth != width || lastHeight != height)

    private fun getTotalSpace(width: Int, height: Int) =
        if (orientation == VERTICAL) {
            width - paddingRight - paddingLeft
        } else {
            height - paddingTop - paddingBottom
        }

    private fun getColumnCount(totalSpace: Int) = totalSpace / columnWidth
}