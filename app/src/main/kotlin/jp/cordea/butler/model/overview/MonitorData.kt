package jp.cordea.butler.model.overview

import com.squareup.moshi.Json
import jp.cordea.butler.model.ClockMonitor
import jp.cordea.butler.model.RespTimeMonitor

/**
 * Created by Yoshihiro Tanaka on 2016/07/12.
 */
data class MonitorData(@Json(name = "hudson.node_monitors.ArchitectureMonitor") val architectureMonitor: String?,
                       @Json(name = "hudson.node_monitors.ResponseTimeMonitor") val respTimeMonitor: RespTimeMonitor?,
                       @Json(name = "hudson.node_monitors.ClockMonitor") val clockMonitor: ClockMonitor?)
