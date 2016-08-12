package jp.cordea.butler.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import jp.cordea.butler.BR
import jp.cordea.butler.R
import jp.cordea.butler.Result
import jp.cordea.butler.resId
import kotlin.properties.Delegates

/**
 * Created by Yoshihiro Tanaka on 2016/07/15.
 */
class ResultStatusCircleViewModel : BaseObservable() {

    var result: Result by Delegates.observable(Result.UNKNOWN) {
        props, old, new ->
        color = new.resId()
        notifyPropertyChanged(BR.color)
    }

    @Bindable
    var color = R.color.statusUnknown
        private set

}
