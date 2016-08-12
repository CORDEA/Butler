package jp.cordea.butler.viewmodel

import android.content.Context
import android.databinding.BaseObservable
import jp.cordea.butler.R
import jp.cordea.butler.TimeFormatter
import jp.cordea.butler.adapter.MonitorDataListAdapter
import jp.cordea.butler.api.JenkinsClient
import jp.cordea.butler.model.ComputerDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Yoshihiro Tanaka on 2016/07/19.
 */
class ComputerDetailViewModel(private val context: Context) : BaseObservable() {

    val adapter = MonitorDataListAdapter(context)

    fun refreshComputerDetail(name: String) {
        JenkinsClient.getJenkinsService(context)
                .getComputerDetail(name, query)
                .enqueue(object : Callback<ComputerDetail> {
                    override fun onFailure(call: Call<ComputerDetail>?, t: Throwable?) {
                        t?.printStackTrace()
                    }

                    override fun onResponse(call: Call<ComputerDetail>?, response: Response<ComputerDetail>?) {
                        response?.let {
                            if (it.isSuccessful) {
                                val isOffline = it.body().offline

                                val items: MutableList<MonitorDataListItemViewModel> = mutableListOf()

                                it.body().run {
                                    var description = if (isOffline) context.resources.getString(R.string.computer_list_item_offline) else context.resources.getString(R.string.computer_list_item_online)
                                    if (offlineCauseReason.isNotBlank()) {
                                        description += context.resources.getString(R.string.computer_detail_description_format_text).format(offlineCauseReason)
                                    }
                                    items.add(MonitorDataHeaderListItemViewModel(context, displayName, description))

                                    monitorData.run {
                                        respTimeMonitor?.let {
                                            items.add(MonitorDataTextListItemViewModel(context, R.string.computer_detail_response_time, TimeFormatter.formattedDurationText(context, it.average)))
                                        }
                                        clockMonitor?.let {
                                            items.add(MonitorDataTextListItemViewModel(context, R.string.computer_detail_clock_time, TimeFormatter.formattedDiffText(context, it.diff)))
                                        }
                                        diskSpaceMonitor?.let {
                                            items.add(MonitorDataCircleListItemViewModel(context, R.string.computer_detail_disk_space, it.size, 0))
                                        }
                                        swapSpaceMonitor?.let {
                                            items.add(MonitorDataCircleListItemViewModel(context, R.string.computer_detail_memory_space, it.availablePhysicalMemory, it.totalPhysicalMemory))
                                            items.add(MonitorDataCircleListItemViewModel(context, R.string.computer_detail_swap_space, it.availableSwapSpace, it.totalSwapSpace))
                                        }
                                        tempSpaceMonitor?.let {
                                            items.add(MonitorDataCircleListItemViewModel(context, R.string.computer_detail_temp_space, it.size, 0))
                                        }
                                    }

                                    loadStatistics.run {
                                        items.add(MonitorDataLineListItemViewModel(context, R.string.computer_detail_busy_exe, busyExecutors.min.latest, busyExecutors.min.history))
                                        items.add(MonitorDataLineListItemViewModel(context, R.string.computer_detail_total_exe, totalExecutors.min.latest, totalExecutors.min.history))
                                        items.add(MonitorDataLineListItemViewModel(context, R.string.computer_detail_queue_exe, queueLength.min.latest, queueLength.min.history))
                                    }

                                    items.add(MonitorDataFooterListItemViewModel(context, R.string.computer_detail_executors, it.body().executors.map { ExecutorListItemViewModel(context, it) }))
                                }
                                adapter.refreshItems(items)
                                notifyChange()
                            }
                        }
                    }
                })

    }

    companion object {
        private val query = "displayName,idle,monitorData[*],numExecutors,executors[currentExecutable[building,estimatedDuration,fullDisplayName,number]" +
                ",number,progress],loadStatistics[busyExecutors[min[history,latest]],queueLength[min[history,latest]],totalExecutors[min[history,latest]]]" +
                ",offline,offlineCauseReason"
    }

}