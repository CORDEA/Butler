package jp.cordea.butler.viewmodel

import android.content.Context
import jp.cordea.butler.DisplayType

/**
 * Created by Yoshihiro Tanaka on 2016/08/05.
 */
class MonitorDataHeaderListItemViewModel(context: Context, title: String, val description: String) : MonitorDataListItemViewModel(context, DisplayType.HEADER, 0, title)