package jp.cordea.butler.viewmodel

import android.content.Context
import android.widget.CompoundButton
import jp.cordea.butler.model.UserPreference

/**
 * Created by Yoshihiro Tanaka on 2016/08/08.
 */
class SettingViewModel(context: Context) {

    val isUserListVisible = UserPreference.load(context).isUserListVisible

    val onCheckedChangeListener = CompoundButton.OnCheckedChangeListener { compoundButton, b ->
        UserPreference.isUserListVisible(context, b)
    }

}