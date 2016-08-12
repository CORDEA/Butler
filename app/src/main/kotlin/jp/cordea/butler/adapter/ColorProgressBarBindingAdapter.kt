package jp.cordea.butler.adapter

import android.databinding.BindingMethod
import android.databinding.BindingMethods
import jp.cordea.butler.view.ColorProgressBar

/**
 * Created by Yoshihiro Tanaka on 2016/07/17.
 */
@BindingMethods(
        BindingMethod(type = ColorProgressBar::class,
                attribute = "app:progressColor",
                method = "setProgressColor")
)
object ColorProgressBarBindingAdapter {
}
