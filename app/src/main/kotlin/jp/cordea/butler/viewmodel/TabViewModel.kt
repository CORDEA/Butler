package jp.cordea.butler.viewmodel

import android.content.Context
import jp.cordea.butler.R
import jp.cordea.butler.adapter.BindingListAdapter
import jp.cordea.butler.api.JenkinsClient
import jp.cordea.butler.model.Jenkins
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Yoshihiro Tanaka on 2016/07/13.
 */
class TabViewModel(private val context: Context) : ProgressViewModel() {

    override fun retry() {
        refreshTabs()
    }

    val adapter = BindingListAdapter<TabListItemViewModel>(context, R.layout.list_item_tab)

    fun refreshTabs() {
        JenkinsClient.getJenkinsService(context)
                .getJenkins(query)
                .enqueue(object : Callback<Jenkins> {
                    override fun onFailure(call: Call<Jenkins>?, t: Throwable?) {
                        t?.printStackTrace()
                        showError()
                    }

                    override fun onResponse(call: Call<Jenkins>?, response: Response<Jenkins>?) {
                        response?.let {
                            if (it.isSuccessful) {
                                hideProgress()
                                adapter.refreshItems(it.body().views.map { TabListItemViewModel(it) })
                            } else {
                                showError()
                            }
                        }
                    }

                })
    }

    companion object {
        private val query = "views[name,url]"
    }

}
