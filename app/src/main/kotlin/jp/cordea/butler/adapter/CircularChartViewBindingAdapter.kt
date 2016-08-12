package jp.cordea.butler.adapter

import android.databinding.BindingMethod
import android.databinding.BindingMethods
import jp.cordea.butler.view.CircularChartView

/**
 * Created by Yoshihiro Tanaka on 2016/07/29.
 */
@BindingMethods(
        BindingMethod(type = CircularChartView::class,
                attribute = "app:percentage",
                method = "setPercentage")
)
object CircularChartViewBindingAdapter {
}