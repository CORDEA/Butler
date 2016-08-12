package jp.cordea.butler.model

import jp.cordea.butler.model.detail.MonitorData

/**
 * Created by Yoshihiro Tanaka on 2016/07/12.
 */
data class ComputerDetail(val displayName: String,
                          val idle: Boolean,
                          val monitorData: MonitorData,
                          val numExecutors: Int,
                          val executors: List<Executor>,
                          val loadStatistics: Statistics,
                          val offline: Boolean,
                          val offlineCauseReason: String)
