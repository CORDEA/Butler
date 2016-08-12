package jp.cordea.butler.adapter

import android.databinding.BindingAdapter
import jp.cordea.butler.Color
import jp.cordea.butler.view.ColorStatusCircleView

/**
 * Created by Yoshihiro Tanaka on 2016/07/15.
 */
object ColorStatusCircleViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("fullText")
    fun bindFullText(view: ColorStatusCircleView, isFullText: Boolean) {
        view.viewModel.fullText = isFullText
    }

    @JvmStatic
    @BindingAdapter("status")
    fun bindStatus(view: ColorStatusCircleView, color: Color) {
        view.viewModel.status = color
    }

}
