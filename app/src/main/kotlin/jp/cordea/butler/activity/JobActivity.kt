package jp.cordea.butler.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import jp.cordea.butler.JobListItemDecoration
import jp.cordea.butler.R
import jp.cordea.butler.databinding.ActivityJobBinding
import jp.cordea.butler.viewmodel.JobViewModel

class JobActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityJobBinding>(this, R.layout.activity_job)
        val viewModel = JobViewModel(this)
        binding.vm = viewModel

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.addItemDecoration(JobListItemDecoration(this))

        val tab = intent.getStringExtra(TabKey)
        viewModel.refreshJobs(tab)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private val TabKey = "TabKey"

        fun createIntent(context: Context, tab: String): Intent {
            return Intent(context, JobActivity::class.java).apply {
                putExtra(TabKey, tab)
            }
        }
    }
}
