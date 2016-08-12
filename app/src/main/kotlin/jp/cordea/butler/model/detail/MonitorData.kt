package jp.cordea.butler.model.detail

import com.squareup.moshi.Json
import jp.cordea.butler.model.*

/**
 * Created by Yoshihiro Tanaka on 2016/07/12.
 */
data class MonitorData(@Json(name = "hudson.node_monitors.SwapSpaceMonitor") val swapSpaceMonitor: SwapSpaceMonitor?,
                       @Json(name = "hudson.node_monitors.TemporarySpaceMonitor") val tempSpaceMonitor: TempSpaceMonitor?,
                       @Json(name = "hudson.node_monitors.DiskSpaceMonitor") val diskSpaceMonitor: DiskSpaceMonitor?,
                       @Json(name = "hudson.node_monitors.ArchitectureMonitor") val architectureMonitor: String?,
                       @Json(name = "hudson.node_monitors.ResponseTimeMonitor") val respTimeMonitor: RespTimeMonitor?,
                       @Json(name = "hudson.node_monitors.ClockMonitor") val clockMonitor: ClockMonitor?

)
