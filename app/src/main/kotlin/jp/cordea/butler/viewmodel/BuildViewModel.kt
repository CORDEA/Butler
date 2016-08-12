package jp.cordea.butler.viewmodel

import android.content.Context
import android.view.View
import jp.cordea.butler.activity.ProjectDetailActivity
import jp.cordea.butler.R
import jp.cordea.butler.adapter.BindingListAdapter
import jp.cordea.butler.api.JenkinsClient
import jp.cordea.butler.model.overview.JobDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Yoshihiro Tanaka on 2016/07/14.
 */
class BuildViewModel(private val context: Context, private val name: String) : ProgressViewModel() {

    override fun retry() {
        refreshBuilds()
    }

    val adapter = BindingListAdapter<BuildListItemViewModel>(context, R.layout.list_item_build)

    val buttonOnClick = View.OnClickListener {
        context.startActivity(ProjectDetailActivity.createIntent(context, name))
    }

    fun refreshBuilds() {
        JenkinsClient.getJenkinsService(context)
                .getBuilds(name, query)
                .enqueue(object : Callback<JobDetail> {
                    override fun onFailure(call: Call<JobDetail>?, t: Throwable?) {
                        t?.printStackTrace()
                        showError()
                    }

                    override fun onResponse(call: Call<JobDetail>?, response: Response<JobDetail>?) {
                        response?.let {
                            if (it.isSuccessful) {
                                hideProgress()
                                adapter.refreshItems(it.body().builds.map { BuildListItemViewModel(context, it) })
                            } else {
                                showError()
                            }
                        }
                    }

                })
    }

    companion object {
        private val query = "builds[number,description,duration,result,building,timestamp,buildOn]"
    }

}
