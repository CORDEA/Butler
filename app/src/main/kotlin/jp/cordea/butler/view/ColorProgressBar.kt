package jp.cordea.butler.view

import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.ProgressBar

/**
 * Created by Yoshihiro Tanaka on 2016/07/17.
 */
open class ColorProgressBar(context: Context?, attrs: AttributeSet?) : ProgressBar(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val size = MeasureSpec.getSize(widthMeasureSpec)
        setMeasuredDimension(size, size)
    }

    fun setProgressColor(color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            progressTintList = ColorStateList.valueOf(ContextCompat.getColor(context, color))
        }
    }

}
