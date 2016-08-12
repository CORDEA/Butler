package jp.cordea.butler.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.view.View
import android.widget.CompoundButton
import jp.cordea.butler.ParamType
import jp.cordea.butler.model.detail.ParameterDefinition

/**
 * Created by Yoshihiro Tanaka on 2016/08/04.
 */
class BuildParamListItemViewModel : BaseObservable {

    var type: ParamType = ParamType.UNKNOWN
        private set

    @Bindable
    var stringVisibility = View.GONE
        private set

    @Bindable
    var booleanVisibility = View.GONE
        private set

    @Bindable
    var stringValue: String = ""

    @Bindable
    var booleanValue: Boolean = false
        private set

    @Bindable
    var name: String = ""
        private set

    val onCheckedChangeListener = CompoundButton.OnCheckedChangeListener { compoundButton, b ->
        booleanValue = b
    }

    constructor(item: ParameterDefinition) {
        name = item.name

        when (item.type) {
            "StringParameterDefinition" -> {
                type = ParamType.STRING
                stringVisibility = View.VISIBLE
                stringValue = item.defaultParameterValue.value as String
            }
            "TextParameterDefinition" -> {
                type = ParamType.TEXT
                stringVisibility = View.VISIBLE
                stringValue = item.defaultParameterValue.value as String
            }
            "BooleanParameterDefinition" -> {
                type = ParamType.BOOLEAN
                booleanVisibility = View.VISIBLE
                booleanValue = item.defaultParameterValue.value as Boolean
            }
            else -> {
                throw IllegalArgumentException()
            }
        }

        notifyChange()
    }

}
