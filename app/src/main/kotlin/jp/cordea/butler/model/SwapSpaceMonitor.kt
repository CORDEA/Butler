package jp.cordea.butler.model

/**
 * Created by Yoshihiro Tanaka on 2016/07/12.
 */
data class SwapSpaceMonitor(val availablePhysicalMemory: Long, val availableSwapSpace: Long, val totalPhysicalMemory: Long, val totalSwapSpace: Long)
