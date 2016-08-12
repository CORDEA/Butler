package jp.cordea.butler.viewmodel

import android.content.Context
import android.view.View
import jp.cordea.butler.TimeFormatter
import jp.cordea.butler.model.Executor

/**
 * Created by Yoshihiro Tanaka on 2016/07/20.
 */
class ExecutorListItemViewModel {

    var score: Int = 0
        private set
    var name: String? = null
        private set
    var duration: String? = null
        private set

    var contentVisibility = View.GONE
        private set
    var emptyVisibility = View.VISIBLE
        private set

    constructor(context: Context, executor: Executor) {
        if (executor.progress > 0) {
            score = executor.progress
        }
        executor.currentExecutable?.let {
            name = it.fullDisplayName
            duration = TimeFormatter.formattedDurationText(context, it.estimatedDuration)
            contentVisibility = View.VISIBLE
            emptyVisibility = View.GONE
        }
    }

}
