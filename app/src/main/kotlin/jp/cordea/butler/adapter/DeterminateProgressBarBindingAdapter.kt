package jp.cordea.butler.adapter

import android.databinding.BindingMethod
import android.databinding.BindingMethods
import jp.cordea.butler.view.DeterminateProgressBar

/**
 * Created by Yoshihiro Tanaka on 2016/07/15.
 */
@BindingMethods(
        BindingMethod(type = DeterminateProgressBar::class,
                attribute = "app:progress",
                method = "setProgress"),
        BindingMethod(type = DeterminateProgressBar::class,
                attribute = "app:isTextVisible",
                method = "setTextVisible")
)
object DeterminateProgressBarBindingAdapter {
}