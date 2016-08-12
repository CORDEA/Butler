package jp.cordea.butler.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import jp.cordea.butler.R
import jp.cordea.butler.databinding.ActivitySignInBinding
import jp.cordea.butler.model.UserPreference
import jp.cordea.butler.viewmodel.SignInViewModel

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivitySignInBinding>(this, R.layout.activity_sign_in)
        val viewModel = SignInViewModel(this)
        binding.vm = viewModel

        setSupportActionBar(binding.toolbar)

        UserPreference.load(this).jenkinsUrl?.let {
            if (!viewModel.isValidUrl(it)) {
                return
            }
            startActivity(MainActivity.createIntent(this))
            finish()
        }

    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, SignInActivity::class.java)
        }
    }

}
