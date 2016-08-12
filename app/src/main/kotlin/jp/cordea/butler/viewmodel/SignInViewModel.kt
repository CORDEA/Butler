package jp.cordea.butler.viewmodel

import android.app.AlertDialog
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.CompoundButton
import jp.cordea.butler.BR
import jp.cordea.butler.activity.MainActivity
import jp.cordea.butler.R
import jp.cordea.butler.activity.SignInActivity
import jp.cordea.butler.model.UserPreference

/**
 * Created by Yoshihiro Tanaka on 2016/07/19.
 */
class SignInViewModel(private val activity: SignInActivity) : BaseObservable() {

    private var url = ""

    private var isCheck: Boolean = false
    private var username: String = ""
    private var token: String = ""

    @Bindable
    var advancedVisibility = View.GONE

    private fun isValidUrl(url: String): Boolean {
        return !url.isNullOrBlank() && Patterns.WEB_URL.matcher(url).matches()
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

    val urlOnTextChanged = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            p0?.let {
                url = it.toString()
            }
        }
    }

    val nameOnTextChanged = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            p0?.let {
                username = it.toString()
            }
        }
    }

    val tokenOnTextChanged = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            p0?.let {
                token = it.toString()
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