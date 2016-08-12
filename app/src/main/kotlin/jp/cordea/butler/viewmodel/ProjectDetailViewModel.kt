package jp.cordea.butler.viewmodel

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.view.View
import jp.cordea.butler.*
import jp.cordea.butler.activity.BuildActivity
import jp.cordea.butler.activity.ExecBuildActivity
import jp.cordea.butler.adapter.BindingListAdapter
import jp.cordea.butler.api.JenkinsClient
import jp.cordea.butler.model.detail.Action
import jp.cordea.butler.model.detail.JobDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Yoshihiro Tanaka on 2016/07/14.
 */
class ProjectDetailViewModel(private val context: Context, private val name: String) : BaseObservable() {

    val downstreamAdapter = BindingListAdapter<StreamProjectListItemViewModel>(context, R.layout.list_item_stream_project)

    val upstreamAdapter = BindingListAdapter<StreamProjectListItemViewModel>(context, R.layout.list_item_stream_project)

    @Bindable
    var duration = ""
        private set

    @Bindable
    var timestamp = ""
        private set

    @Bindable
    var title = ""
        private set

    @Bindable
    var description = ""
        private set

    @Bindable
    var color = Color.UNKNOWN
        private set

    @Bindable
    var score = 0
        private set

    @Bindable
    var isVisibleBuildButton = false
        private set

    @Bindable
    var downstreamVisibility = View.GONE

    @Bindable
    var upstreamVisibility = View.GONE

    @Bindable
    var imageResource: Int = R.drawable.ic_build_white_24px
        private set

    @Bindable
    var execButtonOnClick: View.OnClickListener? = View.OnClickListener {
        if (action == null) {
            buildProject(name)
        } else {
            context.startActivity(ExecBuildActivity.createIntent(context, action!!, name))
        }
    }
        private set

    val buildButtonOnClick = View.OnClickListener {
        context.startActivity(BuildActivity.createIntent(context, name))
    }

    private var action: Action? = null

    fun buildProject(name: String) {
        JenkinsClient.getJenkinsService(context)
                .buildProject(name)
                .enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                        response?.let {
                            if (it.isSuccessful) {
                                imageResource = R.drawable.ic_done_white_24px
                                execButtonOnClick = null
                                notifyPropertyChanged(BR.imageResource)
                                notifyPropertyChanged(BR.execButtonOnClick)
                            }
                        }
                    }

                    override fun onFailure(call: Call<Void>?, t: Throwable?) {
                        t?.printStackTrace()
                    }
                })
    }

    fun refreshBuilds() {
        JenkinsClient.getJenkinsService(context)
                .getJobDetail(name, query)
                .enqueue(object : Callback<JobDetail> {
                    override fun onFailure(call: Call<JobDetail>?, t: Throwable?) {
                        t?.printStackTrace()
                    }

                    override fun onResponse(call: Call<JobDetail>?, response: Response<JobDetail>?) {
                        response?.let {
                            if (it.isSuccessful) {
                                val detail = it.body()
                                title = detail.displayName
                                description = detail.description

                                detail.actions?.let {
                                    action = it.filter { it.parameterDefinitions != null }.firstOrNull()
                                }

                                with(detail.healthReport) {
                                    if (size > 0) {
                                        score = get(0).score
                                    }
                                }

                                color = detail.color

                                isVisibleBuildButton = detail.buildable && !detail.inQueue

                                detail.lastBuild?.let {
                                    duration = context.resources.getString(R.string.project_detail_duration_description)
                                            .format(TimeFormatter.formattedDurationText(context, it.duration))
                                    timestamp = context.resources.getString(R.string.project_timestamp_description)
                                            .format(it.formattedTimestampText(context))
                                }

                                if (detail.upstreamProjects.size > 0) {
                                    upstreamAdapter.refreshItems(detail.upstreamProjects.map { StreamProjectListItemViewModel(it) })
                                    upstreamVisibility = View.VISIBLE
                                }
                                if (detail.downstreamProjects.size > 0) {
                                    downstreamAdapter.refreshItems(detail.downstreamProjects.map { StreamProjectListItemViewModel(it) })
                                    downstreamVisibility = View.VISIBLE
                                }

                                notifyChange()
                            }
                        }
                    }

                })
    }

    companion object {
        private val query = "description,displayName,url,buildable,color,actions[parameterDefinitions[defaultParameterValue[value],description,name,type]]" +
                ",healthReport[score],inQueue,lastBuild[number,description,duration,result,building,timestamp,buildOn]" +
                ",downstreamProjects[name,displayName,description,url,color],upstreamProjects[name,displayName,description,url,color]"
    }

}
