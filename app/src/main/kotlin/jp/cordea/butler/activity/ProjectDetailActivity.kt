package jp.cordea.butler.activity

import android.R
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import jp.cordea.butler.databinding.ActivityProjectDetailBinding
import jp.cordea.butler.viewmodel.ProjectDetailViewModel

class ProjectDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityProjectDetailBinding>(this, jp.cordea.butler.R.layout.activity_project_detail)
        val viewModel = ProjectDetailViewModel(this, intent.getStringExtra(NameKey))
        binding.vm = viewModel

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val context = this
        binding.content.downstreamListView.setOnItemClickListener { adapterView, view, i, l ->
            viewModel.downstreamAdapter.run {
                if (count > 0) {
                    val item = getItem(i)
                    startActivity(createIntent(context, item.name))
                }
            }
        }

        binding.content.upstreamListView.setOnItemClickListener { adapterView, view, i, l ->
            viewModel.upstreamAdapter.run {
                if (count > 0) {
                    val item = getItem(i)
                    startActivity(createIntent(context, item.name))
                }
            }
        }

        viewModel.refreshBuilds()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private val NameKey = "NameKey"

        fun createIntent(context: Context, name: String): Intent {
            return Intent(context, ProjectDetailActivity::class.java).apply {
                putExtra(NameKey, name)
            }
        }
    }

}
