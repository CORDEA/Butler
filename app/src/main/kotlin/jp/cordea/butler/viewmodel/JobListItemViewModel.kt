package jp.cordea.butler.viewmodel

import android.content.Context
import android.view.View
import jp.cordea.butler.activity.BuildActivity
import jp.cordea.butler.activity.ProjectDetailActivity
import jp.cordea.butler.R
import jp.cordea.butler.model.Job
import kotlin.reflect.KProperty

/**
 * Created by Yoshihiro Tanaka on 2016/07/12.
 */
class JobListItemViewModel(private val job: Job, private val context: Context) {

    val title = job.displayName

    val color = job.color

    val timestamp: String by bindTimestamp()

    val score: Int by bindScore()

    val detailButtonOnClick = View.OnClickListener {
        context.startActivity(ProjectDetailActivity.createIntent(context, job.name))
    }

    val buildButtonOnClick = View.OnClickListener {
        context.startActivity(BuildActivity.createIntent(context, job.name))
    }

    private class bindScore {
        operator fun getValue(thisRef: JobListItemViewModel, property: KProperty<*>): Int {
            with(thisRef.job.healthReport) {
                if (size > 0) {
                    return get(0).score
                }
                return 0
            }
        }
    }

    private class bindTimestamp {
        operator fun getValue(thisRef: JobListItemViewModel, property: KProperty<*>): String {
            thisRef.job.lastBuild?.let {
                return thisRef.context.resources.getString(R.string.project_timestamp_description).format(it.formattedTimestampText(thisRef.context))
            }
            return thisRef.context.resources.getString(R.string.project_timestamp_nan_description)
        }
    }

}
