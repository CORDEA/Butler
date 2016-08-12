package jp.cordea.butler

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View

/**
 * Created by Yoshihiro Tanaka on 2016/08/09.
 */
class MonitorDataListItemDecoration(private val context: Context) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        view?.let {
            val lp = it.layoutParams as StaggeredGridLayoutManager.LayoutParams

            if (lp.isFullSpan) {
                return
            }

            val margin = (context.resources.getDimension(R.dimen.computer_detail_card_margin) / 2.0f).toInt()

            when (lp.spanIndex) {
                0 -> {
                    lp.rightMargin = margin
                }
                1 -> {
                    lp.leftMargin = margin
                }
            }
        }
    }

}
