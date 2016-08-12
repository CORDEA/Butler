package jp.cordea.butler.adapter

import android.databinding.BindingAdapter
import android.databinding.BindingMethod
import android.databinding.BindingMethods
import jp.cordea.butler.view.StatusCircleView

/**
 * Created by Yoshihiro Tanaka on 2016/07/14.
 */
@BindingMethods(
        BindingMethod(type = StatusCircleView::class,
                attribute = "app:circleColor",
                method = "setColor"),
        BindingMethod(type = StatusCircleView::class,
                attribute = "app:circleText",
                method = "setText"),
        BindingMethod(type = StatusCircleView::class,
                attribute = "app:fullText",
                method = "setFullText")
)
object StatusCircleViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("circleTextSize")
    fun circleTextSize(view: StatusCircleView, size: Float) {
        view.charDpSize = size
    }

}
