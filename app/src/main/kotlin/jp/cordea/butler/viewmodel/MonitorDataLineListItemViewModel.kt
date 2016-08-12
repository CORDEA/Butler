package jp.cordea.butler.viewmodel

import android.content.Context
import jp.cordea.butler.DisplayType

/**
 * Created by Yoshihiro Tanaka on 2016/07/21.
 */
class MonitorDataLineListItemViewModel(context: Context, titleResId: Int, data: Float, val history: List<Float>) : MonitorDataListItemViewModel(context, DisplayType.LINE, titleResId) {

    val data: String = data.toString()

}
