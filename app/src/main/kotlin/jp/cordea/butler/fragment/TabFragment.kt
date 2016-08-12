package jp.cordea.butler.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.cordea.butler.R
import jp.cordea.butler.activity.JobActivity
import jp.cordea.butler.databinding.FragmentTabBinding
import jp.cordea.butler.viewmodel.TabViewModel

/**
 * Created by Yoshihiro Tanaka on 2016/07/13.
 */
class TabFragment : Fragment() {

    val viewModel by lazy {
        TabViewModel(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentTabBinding>(inflater, R.layout.fragment_tab, container, false)
        binding.vm = viewModel

        binding.listView.setOnItemClickListener { adapterView, view, i, l ->
            val tab = viewModel.adapter.getItem(i)
            startActivity(JobActivity.createIntent(context, tab.title))
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.refreshTabs()
    }

    companion object {
        fun newInstance(): TabFragment {
            return TabFragment()
        }
    }
}