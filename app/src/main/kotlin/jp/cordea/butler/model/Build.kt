package jp.cordea.butler.model

import android.content.Context
import com.squareup.moshi.Json
import jp.cordea.butler.R
import jp.cordea.butler.Result
import org.joda.time.DateTime
import org.joda.time.Duration

/**
 * Created by Yoshihiro Tanaka on 2016/07/10.
 */

data class Build(val number: Int, val description: String?, val duration: Long, @Json(name = "result") private val r: String?, val building: Boolean, val timestamp: Long, val buildOn: String?) {

    @Transient
    var result: Result = Result.UNKNOWN
        private set
        get() {
            return valueOf()
        }

    private fun valueOf(): Result {
        r?.let {
            return Result.valueOf(r.toUpperCase())
        }
        return Result.UNKNOWN
    }

    fun formattedTimestampText(context: Context): String {
        val timestamp = Duration(DateTime(timestamp), DateTime())
        var formatTextResId = R.string.latest_list_timestamp_format_text_s
        var d = timestamp.standardSeconds
        if (d > 60) {
            d = timestamp.standardMinutes
            if (d > 60) {
                d = timestamp.standardHours
                if (d > 24) {
                    d = timestamp.standardDays
                    formatTextResId = R.string.latest_list_timestamp_format_text_d
                } else {
                    formatTextResId = R.string.latest_list_timestamp_format_text_h
                }
            } else {
                formatTextResId = R.string.latest_list_timestamp_format_text_m
            }
        }

        return String.format(context.resources.getString(formatTextResId), d)
    }

}

