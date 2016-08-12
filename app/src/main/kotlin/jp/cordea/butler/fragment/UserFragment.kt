package jp.cordea.butler.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.cordea.butler.R
import jp.cordea.butler.databinding.FragmentUserBinding
import jp.cordea.butler.viewmodel.UserViewModel

class UserFragment : Fragment() {

    private val viewModel by lazy {
        UserViewModel(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentUserBinding>(inflater, R.layout.fragment_user, container, false)
        binding.vm = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.refreshUsers()
    }

    companion object {
        fun newInstance(): UserFragment {
            return UserFragment()
        }
    }
}
