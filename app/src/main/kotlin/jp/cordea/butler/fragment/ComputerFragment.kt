package jp.cordea.butler.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.cordea.butler.R
import jp.cordea.butler.activity.ComputerDetailActivity
import jp.cordea.butler.databinding.FragmentComputerBinding
import jp.cordea.butler.viewmodel.ComputerViewModel

/**
 * Created by Yoshihiro Tanaka on 2016/07/18.
 */
class ComputerFragment : Fragment() {

    lateinit var binding: FragmentComputerBinding

    private val viewModel by lazy {
        ComputerViewModel(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate<FragmentComputerBinding>(inflater, R.layout.fragment_computer, container, false)
        binding.vm = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.listView.setOnItemClickListener { adapterView, view, i, l ->
            val vm = viewModel.adapter.getItem(i)
            startActivity(ComputerDetailActivity.createIntent(context, vm.title))
        }

        viewModel.refreshComputers()
    }

    companion object {
        fun newInstance(): ComputerFragment {
            return ComputerFragment()
        }
    }
}