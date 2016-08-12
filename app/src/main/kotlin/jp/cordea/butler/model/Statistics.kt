package jp.cordea.butler.model

/**
 * Created by Yoshihiro Tanaka on 2016/07/19.
 */
data class Statistics(val busyExecutors: MinStatistics, val queueLength: MinStatistics, val totalExecutors: MinStatistics) {

}
