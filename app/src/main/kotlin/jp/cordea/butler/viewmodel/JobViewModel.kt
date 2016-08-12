package jp.cordea.butler.viewmodel

import android.content.Context
import android.util.Log
import jp.cordea.butler.adapter.JobListAdapter
import jp.cordea.butler.api.JenkinsClient
import jp.cordea.butler.model.Jobs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Yoshihiro Tanaka on 2016/07/13.
 */
class JobViewModel(private val context: Context) {

    val adapter = JobListAdapter(context)

    fun refreshJobs(tab: String) {
        JenkinsClient.getJenkinsService(context)
                .getJobs(tab, query)
                .enqueue(object : Callback<Jobs> {
                    override fun onResponse(call: Call<Jobs>?, response: Response<Jobs>?) {
                        response?.let {
                            if (it.isSuccessful) {
                                adapter.refreshItems(it.body().jobs.map { JobListItemViewModel(it, context) })
                            } else {
                                Log.i("xxxx", it.message())
                            }
                        }
                    }

                    override fun onFailure(call: Call<Jobs>?, t: Throwable?) {
                        t?.printStackTrace()
                    }

                })
    }

    companion object {
        private val query = "jobs[displayName,name,url,color,healthReport[score],lastBuild[number,description,timestamp,result,building,duration]]"
    }
}
