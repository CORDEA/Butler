package jp.cordea.butler

import android.content.Context
import org.joda.time.Duration

/**
 * Created by CORDEA on 2016/08/11.
 */
class TimeFormatter {
    companion object {

        fun formattedDiffText(context: Context, timestamp: Long?): String {
            timestamp?.let {
                val diff = formattedDurationText(context, it, true)
                if (diff.isEmpty()) {
                    return context.resources.getString(R.string.computer_list_diff_equal_format_text)
                }
                if (it > 0L) {
                    return context.resources.getString(R.string.computer_list_diff_delay_format_text).format(diff)
                }
                return context.resources.getString(R.string.computer_list_diff_haste_format_text).format(diff)
            }
            return context.resources.getString(R.string.computer_list_diff_nan_format_text)
        }

        fun formattedDurationText(context: Context, timestamp: Long, isIgnoreMs: Boolean = false): String {
            val duration = Duration(Math.abs(timestamp))
            var formatTextResId = R.string.latest_list_duration_format_text_ms
            var d = Math.abs(timestamp)
            if (d > 999) {
                d = duration.standardSeconds
                if (d == 0L && isIgnoreMs) {
                    return ""
                }
                if (d > 60) {
                    d = duration.standardMinutes
                    if (d > 60) {
                        d = duration.standardHours
                        if (d > 24) {
                            d = duration.standardDays
                            formatTextResId = R.string.latest_list_duration_format_text_d
                        } else {
                            formatTextResId = R.string.latest_list_duration_format_text_h
                        }
                    } else {
                        formatTextResId = R.string.latest_list_duration_format_text_m
                    }
                } else {
                    formatTextResId = R.string.latest_list_duration_format_text_s
                }
            } else {
                if (isIgnoreMs) {
                    return ""
                }
            }

            return context.resources.getString(formatTextResId).format(d)
        }

    }
}