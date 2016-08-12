package jp.cordea.butler.viewmodel

import android.content.Context
import jp.cordea.butler.DisplayType

/**
 * Created by Yoshihiro Tanaka on 2016/07/20.
 */
class MonitorDataCircleListItemViewModel : MonitorDataListItemViewModel {

    val percentage: Float

    constructor(context: Context, titleResId: Int, data: Long, totalData: Long) : super(context, DisplayType.CIRCLE, titleResId) {
        if (totalData == 0L) {
            percentage = 1.0f
        } else {
            percentage = data.toFloat() / totalData
        }
    }
}
