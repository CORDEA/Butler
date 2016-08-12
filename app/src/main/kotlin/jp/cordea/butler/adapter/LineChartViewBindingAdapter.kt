package jp.cordea.butler.adapter

import android.databinding.BindingMethod
import android.databinding.BindingMethods
import jp.cordea.butler.view.LineChartView

/**
 * Created by Yoshihiro Tanaka on 2016/07/31.
 */
@BindingMethods(
        BindingMethod(type = LineChartView::class,
                attribute = "app:lines",
                method = "setLines")
)
object LineChartViewBindingAdapter {
}