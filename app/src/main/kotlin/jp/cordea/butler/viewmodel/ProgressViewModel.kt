package jp.cordea.butler.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.view.View
import jp.cordea.butler.BR

/**
 * Created by CORDEA on 2016/08/12.
 */
abstract class ProgressViewModel : BaseObservable() {

    @Bindable
    var errorVisibility: Int = View.GONE
        private set

    @Bindable
    var progressVisibility: Int = View.VISIBLE
        private set

    @Bindable
    var baseVisibility: Int = View.VISIBLE
        private set

    val retryButtonOnClick = View.OnClickListener {
        errorVisibility = View.GONE
        notifyPropertyChanged(BR.errorVisibility)
        progressVisibility = View.VISIBLE
        notifyPropertyChanged(BR.progressVisibility)
        retry()
    }

    abstract fun retry()

    protected fun showError() {
        showBaseContent()
        progressVisibility = View.GONE
        notifyPropertyChanged(BR.progressVisibility)
        errorVisibility = View.VISIBLE
        notifyPropertyChanged(BR.errorVisibility)
    }

    protected fun hideProgress() {
        progressVisibility = View.GONE
        notifyPropertyChanged(BR.progressVisibility)
        hideBaseContent()
    }

    private fun showBaseContent() {
        baseVisibility = View.VISIBLE
        notifyPropertyChanged(BR.baseVisibility)
    }

    private fun hideBaseContent() {
        baseVisibility = View.GONE
        notifyPropertyChanged(BR.baseVisibility)
    }

}