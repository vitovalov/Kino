package com.vitovalov.kino.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vitovalov.kino.R
import com.vitovalov.kino.ui.more.MoreActivity
import com.vitovalov.kino.ui.settings.SettingsActivity
import com.vitovalov.kino.ui.showlist.ShowListActivity

abstract class BaseNavigationActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    protected lateinit var navigationView: BottomNavigationView

    protected abstract fun onInit(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigationView = findViewById(R.id.activity_home_navigation)
        navigationView.setOnNavigationItemSelectedListener(this)

        onInit(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        updateNavigationBarState()
    }

    // Remove inter-activity transition to avoid screen tossing on tapping bottom navigation items
    public override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

    override fun onNavigationItemSelected(menu: MenuItem): Boolean {
        navigateToTab(menu.itemId)
        return true
    }

    private fun navigateToTab(itemId: Int) {
        navigationView.postDelayed({
            when (itemId) {
                R.id.navigation_home -> {
                    if (this is ShowListActivity) return@postDelayed
                    startActivity(Intent(this, ShowListActivity::class.java))
                }
                R.id.navigation_more -> {
                    if (this is MoreActivity) return@postDelayed
                    startActivity(Intent(this, MoreActivity::class.java))
                }
                R.id.navigation_settings -> {
                    if (this is SettingsActivity) return@postDelayed
                    startActivity(Intent(this, SettingsActivity::class.java))
                }
            }
            finish()
        }, 30)

    }

    private fun updateNavigationBarState() {
        val actionId = navigationItemId
        selectBottomNavigationBarItem(actionId)
    }

    fun selectBottomNavigationBarItem(itemId: Int) {
        val item = navigationView.menu.findItem(itemId)
        item.isChecked = true
    }
}