package jp.cordea.butler.viewmodel

import android.content.Context
import jp.cordea.butler.R
import jp.cordea.butler.TimeFormatter
import jp.cordea.butler.model.Build
import kotlin.reflect.KProperty

/**
 * Created by Yoshihiro Tanaka on 2016/07/13.
 */
class BuildListItemViewModel(private val context: Context, private val build: Build) {

    val title = build.number.toString()

    val description by bindDescription()

    val duration = build.formattedTimestampText(context)

    val result = build.result

    private class bindDescription {
        operator fun getValue(thisRef: BuildListItemViewModel, property: KProperty<*>): String {
            val buildOn = thisRef.build.buildOn
            val desc = thisRef.build.description
            val context = thisRef.context

            val duration = TimeFormatter.formattedDurationText(context, thisRef.build.duration)
            if (desc != null && buildOn != null) {
                return context.resources.getString(R.string.build_list_description_format_text).format(
                        duration, context.resources.getString(R.string.build_list_build_on_format_text).format(buildOn, desc))
            }
            if (desc != null) {
                return context.resources.getString(R.string.build_list_description_format_text_short).format(duration, desc)

            }
            if (buildOn != null) {
                return context.resources.getString(R.string.build_list_description_format_text_short).format(duration, buildOn)

            }
            return duration
        }
    }

}
