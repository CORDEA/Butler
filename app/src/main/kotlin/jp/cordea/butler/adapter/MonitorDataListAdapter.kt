package jp.cordea.butler.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import jp.cordea.butler.BR
import jp.cordea.butler.DisplayType
import jp.cordea.butler.R
import jp.cordea.butler.databinding.*
import jp.cordea.butler.viewmodel.MonitorDataListItemViewModel

/**
 * Created by Yoshihiro Tanaka on 2016/07/20.
 */
class MonitorDataListAdapter(private val context: Context, private var items: List<MonitorDataListItemViewModel> = listOf()) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val item = items[position]
        val lp = holder?.itemView?.layoutParams as StaggeredGridLayoutManager.LayoutParams
        when (item.type) {
            DisplayType.HEADER -> {
                lp.isFullSpan = true
                bindCustomViewHolder<HeaderViewHolder>(holder, position)
            }
            DisplayType.CIRCLE -> {
                bindCustomViewHolder<CircleViewHolder>(holder, position)
            }
            DisplayType.LINE -> bindCustomViewHolder<LineViewHolder>(holder, position)
            DisplayType.TEXT -> bindCustomViewHolder<TextViewHolder>(holder, position)
            DisplayType.FOOTER -> {
                lp.isFullSpan = true
                bindCustomViewHolder<FooterViewHolder>(holder, position)
            }
            else -> throw IllegalArgumentException()
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : ViewHolder> bindCustomViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as? T)?.let {
            it.binding.setVariable(BR.vm, items[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        when (DisplayType.values()[viewType]) {
            DisplayType.CIRCLE ->
                return CircleViewHolder(DataBindingUtil.inflate<ListItemMonitorDataCircleBinding>(inflater, R.layout.list_item_monitor_data_circle, parent, false))
            DisplayType.LINE ->
                return LineViewHolder(DataBindingUtil.inflate<ListItemMonitorDataLineBinding>(inflater, R.layout.list_item_monitor_data_line, parent, false))
            DisplayType.TEXT ->
                return TextViewHolder(DataBindingUtil.inflate<ListItemMonitorDataTextBinding>(inflater, R.layout.list_item_monitor_data_text, parent, false))
            DisplayType.HEADER ->
                return HeaderViewHolder(DataBindingUtil.inflate<ListItemMonitorDataHeaderBinding>(inflater, R.layout.list_item_monitor_data_header, parent, false))
            DisplayType.FOOTER ->
                return FooterViewHolder(DataBindingUtil.inflate<ListItemMonitorDataFooterBinding>(inflater, R.layout.list_item_monitor_data_footer, parent, false))
            else ->
                throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].type.ordinal
    }

    fun refreshItems(items: List<MonitorDataListItemViewModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    private class CircleViewHolder(override val binding: ListItemMonitorDataCircleBinding) : ViewHolder(binding)

    private class TextViewHolder(override val binding: ListItemMonitorDataTextBinding) : ViewHolder(binding)

    private class LineViewHolder(override val binding: ListItemMonitorDataLineBinding) : ViewHolder(binding)

    private class HeaderViewHolder(override val binding: ListItemMonitorDataHeaderBinding) : ViewHolder(binding)

    private class FooterViewHolder(override val binding: ListItemMonitorDataFooterBinding) : ViewHolder(binding)

    private open class ViewHolder(open val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

}