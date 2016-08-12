package jp.cordea.butler.model

import com.squareup.moshi.Json
import jp.cordea.butler.Color

/**
 * Created by Yoshihiro Tanaka on 2016/07/12.
 */
data class Project(val name: String, val displayName: String, val description: String?, val url: String, @Json(name = "color") private val c: String?) {

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
