package jp.cordea.butler.viewmodel

import android.content.Context
import jp.cordea.butler.DisplayType
import jp.cordea.butler.R
import jp.cordea.butler.adapter.BindingListAdapter

/**
 * Created by Yoshihiro Tanaka on 2016/08/05.
 */
class MonitorDataFooterListItemViewModel(context: Context, titleResId: Int, items: List<ExecutorListItemViewModel>) : MonitorDataListItemViewModel(context, DisplayType.FOOTER, titleResId) {

    val adapter = BindingListAdapter(context, R.layout.list_item_executor, items)

}
