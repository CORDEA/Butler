package jp.cordea.butler.view

import android.content.Context
import android.databinding.DataBindingUtil
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import jp.cordea.butler.R
import jp.cordea.butler.databinding.ViewColorStatusCircleBinding
import jp.cordea.butler.viewmodel.ColorStatusCircleViewModel

/**
 * Created by Yoshihiro Tanaka on 2016/07/14.
 */
class ColorStatusCircleView : FrameLayout {

    val viewModel = ColorStatusCircleViewModel()

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val binding = DataBindingUtil.inflate<ViewColorStatusCircleBinding>(LayoutInflater.from(context), R.layout.view_color_status_circle, this, true)
        binding.vm = viewModel
    }

}