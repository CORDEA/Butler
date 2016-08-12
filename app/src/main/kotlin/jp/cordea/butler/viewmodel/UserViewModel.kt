package jp.cordea.butler.viewmodel

import android.content.Context
import android.support.v7.app.AlertDialog
import jp.cordea.butler.R
import jp.cordea.butler.adapter.BindingListAdapter
import jp.cordea.butler.api.JenkinsClient
import jp.cordea.butler.model.UserPreference
import jp.cordea.butler.model.Users
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException

/**
 * Created by Yoshihiro Tanaka on 2016/07/18.
 */
class UserViewModel(private val context: Context) : ProgressViewModel() {

    override fun retry() {
        refreshUsers()
    }

    val adapter = BindingListAdapter<UserListItemViewModel>(context, R.layout.list_item_user)

    fun refreshUsers() {
        JenkinsClient.getJenkinsService(context)
                .getUsers()
                .enqueue(object : Callback<Users> {
                    override fun onResponse(call: Call<Users>?, response: Response<Users>?) {
                        response?.let {
                            if (it.isSuccessful) {
                                hideProgress()
                                adapter.refreshItems(it.body().users.map { UserListItemViewModel(it) })
                            } else {
                                showError()
                            }
                        }
                    }

                    override fun onFailure(call: Call<Users>?, t: Throwable?) {
                        (t as? SocketTimeoutException)?.let {
                            UserPreference.isUserListVisible(context, false)
                            AlertDialog.Builder(context)
                                    .setTitle(R.string.user_list_failure_dialog_title)
                                    .setMessage(R.string.user_list_failure_dialog_message)
                                    .setPositiveButton(R.string.dialog_button_close, null)
                                    .show()
                        }
                        t?.printStackTrace()
                        showError()
                    }
                })
    }

}