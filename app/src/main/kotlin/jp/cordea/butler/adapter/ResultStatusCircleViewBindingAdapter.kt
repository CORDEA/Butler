package jp.cordea.butler.adapter

import android.databinding.BindingAdapter
import jp.cordea.butler.Result
import jp.cordea.butler.view.ResultStatusCircleView

/**
 * Created by Yoshihiro Tanaka on 2016/07/14.
 */
object ResultStatusCircleViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("status")
    fun bind(view: ResultStatusCircleView, result: Result) {
        view.viewModel.result = result
    }

}
