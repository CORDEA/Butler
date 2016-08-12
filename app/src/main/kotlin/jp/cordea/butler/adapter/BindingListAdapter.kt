package jp.cordea.butler.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import jp.cordea.butler.BR

/**
 * Created by Yoshihiro Tanaka on 2016/07/18.
 */
class BindingListAdapter<T>(private val context: Context, private val layout: Int, private var items: List<T> = emptyList<T>()) : BaseAdapter() {

    override fun getItem(position: Int): T {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val binding: ViewDataBinding
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(context), layout, parent, false)
            view = binding.root
        } else {
            view = convertView
            binding = DataBindingUtil.getBinding(view)
        }

        binding.setVariable(BR.vm, getItem(position))

        return view
    }

    fun refreshItems(items: List<T>) {
        this.items = items
        notifyDataSetChanged()
    }

}