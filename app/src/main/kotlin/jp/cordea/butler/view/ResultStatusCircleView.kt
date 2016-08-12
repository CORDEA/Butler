package jp.cordea.butler.view

import android.content.Context
import android.databinding.DataBindingUtil
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import jp.cordea.butler.R
import jp.cordea.butler.databinding.ViewResultStatusCircleBinding
import jp.cordea.butler.viewmodel.ResultStatusCircleViewModel

/**
 * Created by Yoshihiro Tanaka on 2016/07/14.
 */
class ResultStatusCircleView : FrameLayout {

    val viewModel = ResultStatusCircleViewModel()

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val binding = DataBindingUtil.inflate<ViewResultStatusCircleBinding>(LayoutInflater.from(context), R.layout.view_result_status_circle, this, true)
        binding.vm = viewModel
    }
}