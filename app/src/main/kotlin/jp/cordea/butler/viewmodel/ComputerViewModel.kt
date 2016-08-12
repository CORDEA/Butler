package jp.cordea.butler.viewmodel

import android.content.Context
import jp.cordea.butler.R
import jp.cordea.butler.adapter.BindingListAdapter
import jp.cordea.butler.api.JenkinsClient
import jp.cordea.butler.model.Computers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Yoshihiro Tanaka on 2016/07/18.
 */
class ComputerViewModel(private val context: Context) : ProgressViewModel() {

    override fun retry() {
        refreshComputers()
    }

    val adapter = BindingListAdapter<ComputerListItemViewModel>(context, R.layout.list_item_computer)

    fun refreshComputers() {
        JenkinsClient.getJenkinsService(context)
                .getComputers(query)
                .enqueue(object : Callback<Computers> {
                    override fun onResponse(call: Call<Computers>?, response: Response<Computers>?) {
                        response?.let {
                            if (it.isSuccessful) {
                                hideProgress()
                                adapter.refreshItems(it.body().computer.map { ComputerListItemViewModel(context, it) })
                            } else {
                                showError()
                            }
                        }
                    }

                    override fun onFailure(call: Call<Computers>?, t: Throwable?) {
                        t?.printStackTrace()
                        showError()
                    }

                })
    }

    companion object {
        private val query = "busyExecutors,computer[displayName,offline,monitorData[*]]"
    }

}