package jp.cordea.butler.model

import jp.cordea.butler.model.overview.MonitorData

/**
 * Created by Yoshihiro Tanaka on 2016/07/12.
 */
data class Computer(val displayName: String,
                    val monitorData: MonitorData,
                    val offline: Boolean)
