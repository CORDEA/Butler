package jp.cordea.butler.viewmodel

import jp.cordea.butler.model.User

/**
 * Created by Yoshihiro Tanaka on 2016/07/12.
 */
class UserListItemViewModel(user: User) {

    val title: String = user.user.fullName

    val description: String = user.user.description

}