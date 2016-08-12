package jp.cordea.butler.viewmodel

import jp.cordea.butler.model.Project

/**
 * Created by Yoshihiro Tanaka on 2016/07/17.
 */
class StreamProjectListItemViewModel(project: Project) {

    val name = project.name

    val title = project.displayName

    val description = project.description

    val color = project.color

}