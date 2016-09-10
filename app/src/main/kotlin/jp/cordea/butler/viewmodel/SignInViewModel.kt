package jp.cordea.butler.viewmodel

import android.app.AlertDialog
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.util.Patterns
import android.view.View
import android.widget.CompoundButton
import jp.cordea.butler.BR
import jp.cordea.butler.R
import jp.cordea.butler.activity.MainActivity
import jp.cordea.butler.activity.SignInActivity
import jp.cordea.butler.model.UserPreference

/**
 * Created by Yoshihiro Tanaka on 2016/07/19.
 */
class SignInViewModel(private val activity: SignInActivity) : BaseObservable() {

    private var isCheck = false

    var url = ""
    var username = ""
    var token = ""

    @Bindable
    var advancedVisibility = View.GONE

    fun isValidUrl(url: String): Boolean {
        return !url.isNullOrBlank() && Patterns.WEB_URL.matcher(url).matches() && (url.contains("http://") || url.contains("https://"))
    }

    val onCheckedChange = CompoundButton.OnCheckedChangeListener { compoundButton, b ->
        isCheck = b
        if (advancedVisibility == View.GONE) {
            advancedVisibility = View.VISIBLE
        } else {
            advancedVisibility = View.GONE
        }
        notifyPropertyChanged(BR.advancedVisibility)
    }

    val onClick = View.OnClickListener {
        if (isCheck) {
            if (url.isNullOrBlank() || username.isNullOrBlank() || token.isNullOrBlank() || !isValidUrl(url)) {
                showAlertDialog(R.string.sign_in_failure_dialog_message_advanced)
            } else {
                UserPreference.save(activity, url, username, token)
                activity.startActivity(MainActivity.createIntent(activity))
                activity.finish()
            }
        } else {
            if (url.isNullOrBlank() || !isValidUrl(url)) {
                showAlertDialog(R.string.sign_in_failure_dialog_message)
            } else {
                UserPreference.save(activity, url)
                activity.startActivity(MainActivity.createIntent(activity))
                activity.finish()
            }
        }
    }

    private fun showAlertDialog(description: Int) {
        AlertDialog
                .Builder(activity)
                .setTitle(R.string.sign_in_failure_dialog_title)
                .setMessage(description)
                .setPositiveButton(R.string.dialog_button_close, null)
                .show()
    }
}