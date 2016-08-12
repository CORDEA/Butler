package jp.cordea.butler.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import jp.cordea.butler.R
import jp.cordea.butler.databinding.ActivityExecBuildBinding
import jp.cordea.butler.model.detail.Action
import jp.cordea.butler.viewmodel.ExecBuildViewModel

class ExecBuildActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityExecBuildBinding>(this, R.layout.activity_exec_build)
        val action = intent.extras.getSerializable(ParamKey) as Action
        val name = intent.extras.getString(NameKey)

        val viewModel = ExecBuildViewModel(this, action, name)
        binding.vm = viewModel

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private val ParamKey = "ParamKey"
        private val NameKey = "NameKey"

        fun createIntent(context: Context, action: Action, name: String): Intent {
            return Intent(context, ExecBuildActivity::class.java).apply {
                putExtra(ParamKey, action)
                putExtra(NameKey, name)
            }
        }

    }

}
