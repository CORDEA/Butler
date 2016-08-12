package jp.cordea.butler.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import jp.cordea.butler.*
import jp.cordea.butler.databinding.ActivityMainBinding
import jp.cordea.butler.fragment.ComputerFragment
import jp.cordea.butler.fragment.SettingFragment
import jp.cordea.butler.fragment.TabFragment
import jp.cordea.butler.fragment.UserFragment
import jp.cordea.butler.model.UserPreference

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        setSupportActionBar(binding.appBar.toolbar)

        val toggle = ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.appBar.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener(this)
        binding.navView.menu.findItem(R.id.nav_user).isVisible = UserPreference.load(this).isUserListVisible

        supportFragmentManager.beginTransaction().replace(R.id.container, TabFragment.newInstance()).commit()
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        val transaction = supportFragmentManager.beginTransaction()
        when (id) {
            R.id.nav_view -> {
                val fragment = TabFragment.newInstance()
                transaction.replace(R.id.container, fragment)
            }
            R.id.nav_computer -> {
                val fragment = ComputerFragment.newInstance()
                transaction.replace(R.id.container, fragment)
            }
            R.id.nav_user -> {
                val fragment = UserFragment.newInstance()
                transaction.replace(R.id.container, fragment)
            }
            R.id.nav_settings -> {
                val fragment = SettingFragment.newInstance()
                transaction.replace(R.id.container, fragment)
            }
            R.id.nav_sign_out -> {
                UserPreference.clear(this)
                startActivity(SignInActivity.createIntent(this))
                finish()
            }
        }
        transaction.commit()

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}
