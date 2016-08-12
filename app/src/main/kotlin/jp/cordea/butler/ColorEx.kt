package jp.cordea.butler

/**
 * Created by Yoshihiro Tanaka on 2016/07/15.
 */
fun Color.isAnim(): Boolean {
    return this.name.endsWith("ANIM")
}

fun Color.first(): Char {
    return this.name[0]
}

fun Color.resId(): Int {
    val colorSet: Map<Color, Int> = mapOf(
            Pair(Color.ABORTED, R.color.statusAborted),
            Pair(Color.ABORTED_ANIME, R.color.statusAborted),
            Pair(Color.BLUE, R.color.statusBlue),
            Pair(Color.BLUE_ANIME, R.color.statusBlue),
            Pair(Color.DISABLED, R.color.statusDisabled),
            Pair(Color.DISABLED_ANIME, R.color.statusDisabled),
            Pair(Color.GREY, R.color.statusGrey),
            Pair(Color.GREY_ANIME, R.color.statusGrey),
            Pair(Color.NOTBUILT, R.color.statusNotbuilt),
            Pair(Color.NOTBUILT_ANIME, R.color.statusNotbuilt),
            Pair(Color.RED, R.color.statusRed),
            Pair(Color.RED_ANIME, R.color.statusRed),
            Pair(Color.YELLOW, R.color.statusYellow),
            Pair(Color.YELLOW_ANIME, R.color.statusYellow),
            Pair(Color.UNKNOWN, R.color.statusUnknown)
    )

    if (colorSet.containsKey(this)) {
        return colorSet[this] as Int
    }
    return R.color.statusUnknown
}
