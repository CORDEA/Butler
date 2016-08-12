package jp.cordea.butler.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.MenuItem
import jp.cordea.butler.MonitorDataListItemDecoration
import jp.cordea.butler.R
import jp.cordea.butler.databinding.ActivityComputerDetailBinding
import jp.cordea.butler.viewmodel.ComputerDetailViewModel

class ComputerDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityComputerDetailBinding>(this, R.layout.activity_computer_detail)
        val viewModel = ComputerDetailViewModel(this)
        binding.vm = viewModel

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerView.addItemDecoration(MonitorDataListItemDecoration(this))

        val name = intent.getStringExtra(NameKey)
        viewModel.refreshComputerDetail(with(name) {
            if (equals("master")) {
                return@with "(master)"
            }
            this
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private val NameKey = "NameKey"

        fun createIntent(context: Context, name: String): Intent {
            return Intent(context, ComputerDetailActivity::class.java).apply {
                putExtra(NameKey, name)
            }
        }
    }

}
