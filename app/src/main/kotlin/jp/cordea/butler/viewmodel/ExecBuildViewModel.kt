package jp.cordea.butler.viewmodel

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.support.v7.app.AlertDialog
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import jp.cordea.butler.BR
import jp.cordea.butler.ParamType
import jp.cordea.butler.R
import jp.cordea.butler.adapter.BindingListAdapter
import jp.cordea.butler.api.JenkinsClient
import jp.cordea.butler.model.detail.Action
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Yoshihiro Tanaka on 2016/08/04.
 */
class ExecBuildViewModel : BaseObservable {

    private val context: Context

    val adapter: BindingListAdapter<BuildParamListItemViewModel>

    @Bindable
    var imageResource: Int = R.drawable.ic_build_white_24px
        private set

    @Bindable
    var buttonOnClick: View.OnClickListener?
        private set

    val onItemClickListener: AdapterView.OnItemClickListener

    constructor(context: Context, action: Action, name: String) {
        this.context = context

        val items: MutableList<BuildParamListItemViewModel> = arrayListOf()
        for (item in action.parameterDefinitions!!) {
            items.add(BuildParamListItemViewModel(item))
        }

        adapter = BindingListAdapter(context, R.layout.list_item_build_param, items)
        onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val item = items[i]
            if (item.type == ParamType.BOOLEAN) {
                return@OnItemClickListener
            }

            val layout = LayoutInflater.from(context).inflate(R.layout.dialog_edit_text, null)
            val editText = layout.findViewById(R.id.edit_text) as EditText
            editText.text = SpannableStringBuilder(item.stringValue)
            AlertDialog
                    .Builder(context)
                    .setTitle((item.name as CharSequence))
                    .setView(layout)
                    .setPositiveButton(R.string.exec_build_list_item_dialog_positive_button, { d, w ->
                        item.stringValue = editText.text.toString()
                        item.notifyPropertyChanged(BR.description)
                    })
                    .show()
        }

        buttonOnClick = View.OnClickListener {
            val params: MutableMap<String, String> = mutableMapOf()
            for (item in items) {
                if (item.type == ParamType.BOOLEAN) {
                    params.put(item.name, item.booleanValue.toString())
                } else {
                    params.put(item.name, item.stringValue)
                }
            }
            buildProject(name, params)
        }
    }

    fun buildProject(name: String, params: Map<String, String>) {
        JenkinsClient.getJenkinsService(context)
                .buildWithParamProject(name, params)
                .enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                        response?.let {
                            if (it.isSuccessful) {
                                imageResource = R.drawable.ic_done_white_24px
                                buttonOnClick = null
                                notifyPropertyChanged(BR.imageResource)
                                notifyPropertyChanged(BR.buttonOnClick)
                            }
                        }
                    }

                    override fun onFailure(call: Call<Void>?, t: Throwable?) {
                        t?.printStackTrace()
                    }
                })
    }

}