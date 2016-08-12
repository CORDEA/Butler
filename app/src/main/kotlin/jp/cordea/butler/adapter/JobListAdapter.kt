package jp.cordea.butler.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import jp.cordea.butler.R
import jp.cordea.butler.databinding.ListItemJobBinding
import jp.cordea.butler.viewmodel.JobListItemViewModel

/**
 * Created by Yoshihiro Tanaka on 2016/07/12.
 */
class JobListAdapter(val context: Context, private var items: MutableList<JobListItemViewModel> = mutableListOf()) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ListItemJobBinding>(LayoutInflater.from(context), R.layout.list_item_job, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as? ViewHolder)?.let {
            it.binding.vm = items[position]
        }
    }

    fun refreshItems(items: List<JobListItemViewModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    private class ViewHolder(val binding: ListItemJobBinding) : RecyclerView.ViewHolder(binding.root)

}