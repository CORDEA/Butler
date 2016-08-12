package jp.cordea.butler.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.cordea.butler.R
import jp.cordea.butler.databinding.FragmentSettingBinding
import jp.cordea.butler.viewmodel.SettingViewModel

/**
 * Created by Yoshihiro Tanaka on 2016/08/08.
 */
class SettingFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentSettingBinding>(inflater, R.layout.fragment_setting, container, false)
        binding.vm = SettingViewModel(context)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    companion object {
        fun newInstance(): SettingFragment {
            return SettingFragment()
        }
    }

}