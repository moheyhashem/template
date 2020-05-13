package com.template

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import android.view.WindowManager
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.template.presentation.util.Config
import com.template.presentation.util.PreferencesHelper
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.main_activity.*
import java.util.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            customiseStatusbar()
        setContentView(R.layout.main_activity)

        setSupportActionBar(toolbar)
        startActivities()
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    private fun startActivities() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.main)
        val navController = Navigation.findNavController(this, R.id.container)

        if (PreferencesHelper(this).isSyndicateChosen()) // TODO
            graph.startDestination = R.id.homeFragment
        else
            graph.startDestination = R.id.syndicatesFragment

        navHostFragment.navController.graph = graph
        supportFragmentManager.beginTransaction().setPrimaryNavigationFragment(navHostFragment).commit()
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)
        nav_view.setupWithNavController(navController)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.syndicatesFragment), drawer_layout)
        toolbar.setupWithNavController(navController, appBarConfiguration)
        // ////////////////////////////////
        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        nav_view.setNavigationItemSelectedListener {
            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            when (it.itemId) {
                R.id.home_fragment -> {
                    drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                }
                R.id.logout_fragment -> {
                    PreferencesHelper(this).isRegistered = false
                    invalidateOptionsMenu()
                } // TODO
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }
    }

    //region//

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.container), drawer_layout)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            var currentFragment = (supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment).childFragmentManager.fragments[0]
            super.onBackPressed()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun customiseStatusbar() {
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.setBackgroundDrawableResource(R.mipmap.full_bg)
    }

    override fun attachBaseContext(newBase: Context) {
        val context = Config.ContextWrapper.wrap(newBase, Locale(Config.LANGUAGE.toLowerCase()))
        super.attachBaseContext(context)
    }
    //endregion//
}