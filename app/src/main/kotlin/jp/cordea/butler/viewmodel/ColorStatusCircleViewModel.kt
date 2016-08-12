package jp.cordea.butler.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.view.View
import jp.cordea.butler.*
import kotlin.properties.Delegates

/**
 * Created by Yoshihiro Tanaka on 2016/07/15.
 */
class ColorStatusCircleViewModel : BaseObservable() {

    var status: Color by Delegates.observable(Color.UNKNOWN) {
        props, old, new ->
        if (new.isAnim()) {
            viewVisibility = View.GONE
            progressVisibility = View.VISIBLE
        } else {
            viewVisibility = View.VISIBLE
            progressVisibility = View.GONE
        }
        circleColor = new.resId()
        circleText = new
        notifyChange()
    }

    @Bindable
    var circleColor = R.color.statusUnknown
        private set

    @Bindable
    var circleText = Color.NONE
        private set

    @Bindable
    var viewVisibility = View.VISIBLE
        private set

    @Bindable
    var progressVisibility = View.GONE
        private set

    @Bindable
    var fullText = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.fullText)
        }

}
