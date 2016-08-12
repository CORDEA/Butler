package jp.cordea.butler.model.detail

import java.io.Serializable

/**
 * Created by Yoshihiro Tanaka on 2016/08/04.
 */

data class ParameterDefinition(val defaultParameterValue: ParameterValue, val description: String, val name: String, val type: String) : Serializable

