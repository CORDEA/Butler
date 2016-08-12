package jp.cordea.butler.model

import android.content.Context
import jp.cordea.butler.R

/**
 * Created by Yoshihiro Tanaka on 2016/07/12.
 */
data class DiskSpaceMonitor(val timestamp: Long, val path: String, val size: Long) {

    fun formattedSizeText(context: Context): String {
        var unit = context.resources.getString(R.string.computer_detail_memory_format_m)
        var memory = size / 1024.0f / 1024.0f
        if (memory > 999) {
            unit = context.resources.getString(R.string.computer_detail_memory_format_g)
            memory /= 1024.0f
        }

        return unit.format(Math.round(memory).toInt())
    }

}