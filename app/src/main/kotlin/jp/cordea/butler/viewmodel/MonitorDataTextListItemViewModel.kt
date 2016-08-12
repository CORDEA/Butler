package jp.cordea.butler.viewmodel

import android.content.Context
import jp.cordea.butler.DisplayType

/**
 * Created by Yoshihiro Tanaka on 2016/07/21.
 */
class MonitorDataTextListItemViewModel(context: Context, titleResId: Int, val data: String) : MonitorDataListItemViewModel(context, DisplayType.TEXT, titleResId)
