package jp.cordea.butler.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ListView

/**
 * Created by Yoshihiro Tanaka on 2016/08/09.
 */
class AdjustedListView(context: Context, attr: AttributeSet) : ListView(context, attr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val spec = MeasureSpec.makeMeasureSpec(View.MEASURED_SIZE_MASK, MeasureSpec.AT_MOST)
        super.onMeasure(widthMeasureSpec, spec)
    }

}