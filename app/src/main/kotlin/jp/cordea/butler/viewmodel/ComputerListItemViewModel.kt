package jp.cordea.butler.viewmodel

import android.content.Context
import jp.cordea.butler.R
import jp.cordea.butler.TimeFormatter
import jp.cordea.butler.model.Computer
import kotlin.reflect.KProperty

/**
 * Created by Yoshihiro Tanaka on 2016/07/18.
 */
class ComputerListItemViewModel(private val context: Context, private val computer: Computer) {

    val title = computer.displayName

    val diff = TimeFormatter.formattedDiffText(context, computer.monitorData.clockMonitor?.diff)

    val description = computer.monitorData.architectureMonitor

    val status by bindStatus()

    private class bindStatus {
        operator fun getValue(thisRef: ComputerListItemViewModel, property: KProperty<*>): String {
            if (thisRef.computer.offline) {
                return thisRef.context.resources.getString(R.string.computer_list_item_offline)
            } else {
                return thisRef.context.resources.getString(R.string.computer_list_item_online)
            }
        }
    }

}
