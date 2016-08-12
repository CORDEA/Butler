package jp.cordea.butler.model.detail

import com.squareup.moshi.Json
import jp.cordea.butler.Color
import jp.cordea.butler.model.Build
import jp.cordea.butler.model.HealthReport
import jp.cordea.butler.model.Project

/**
 * Created by Yoshihiro Tanaka on 2016/07/12.
 */

class JobDetail(val description: String,
                val actions: List<Action>?,
                val displayName: String,
                val url: String,
                val buildable: Boolean,
                @Json(name = "color") private val c: String?,
                val healthReport: List<HealthReport>,
                val inQueue: Boolean,
                val lastBuild: Build?,
                val downstreamProjects: List<Project>,
                val upstreamProjects: List<Project>) {

    @Transient
    var color: Color = Color.UNKNOWN
        private set
        get() {
            return valueOf()
        }

    private fun valueOf(): Color {
        c?.let {
            return Color.valueOf(c.toUpperCase())
        }
        return Color.UNKNOWN
    }

}
