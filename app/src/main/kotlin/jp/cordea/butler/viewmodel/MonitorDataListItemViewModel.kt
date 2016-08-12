package jp.cordea.butler.viewmodel

import android.content.Context
import jp.cordea.butler.DisplayType

/**
 * Created by Yoshihiro Tanaka on 2016/07/21.
 */
open class MonitorDataListItemViewModel {

    val type: DisplayType
    val title: String

    constructor(context: Context, type: DisplayType, titleResId: Int, title: String? = null) {
        this.type = type
        title?.let {
            this.title = title
            return
        }
        this.title = context.resources.getString(titleResId)
    }

}
