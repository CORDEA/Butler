package jp.cordea.butler.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import jp.cordea.butler.R
import jp.cordea.butler.databinding.ActivityBuildBinding
import jp.cordea.butler.viewmodel.BuildViewModel

class BuildActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityBuildBinding>(this, R.layout.activity_build)

        val name = intent.getStringExtra(NameKey)
        val viewModel = BuildViewModel(this, name)

        binding.vm = viewModel

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.refreshBuilds()
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
            return Intent(context, BuildActivity::class.java).apply {
                putExtra(NameKey, name)
            }
        }
    }

}
