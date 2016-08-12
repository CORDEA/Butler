package jp.cordea.butler

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Yoshihiro Tanaka on 2016/08/09.
 */
class JobListItemDecoration(private val context: Context) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        view?.let {
            parent?.let { parent ->
                val lp = it.layoutParams as RecyclerView.LayoutParams

                if (parent.getChildAdapterPosition(view) == 0) {
                    val margin = context.resources.getDimension(R.dimen.job_card_margin).toInt()
                    lp.topMargin = margin
                } else {
                    lp.topMargin = 0
                }
            }
        }
    }

}
