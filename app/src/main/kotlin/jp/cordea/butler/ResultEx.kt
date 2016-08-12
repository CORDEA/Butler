package jp.cordea.butler

/**
 * Created by Yoshihiro Tanaka on 2016/07/16.
 */
fun Result.resId(): Int {
    val colorSet: Map<Result, Int> = mapOf(
            Pair(Result.SUCCESS, R.color.statusSuccess),
            Pair(Result.FAILURE, R.color.statusFailure),
            Pair(Result.NOT_BUILT, R.color.statusUnknown),
            Pair(Result.UNSTABLE, R.color.statusUnknown),
            Pair(Result.ABORTED, R.color.statusUnknown),
            Pair(Result.UNKNOWN, R.color.statusUnknown)
    )

    if (colorSet.containsKey(this)) {
        return colorSet[this] as Int
    }
    return R.color.statusUnknown
}
