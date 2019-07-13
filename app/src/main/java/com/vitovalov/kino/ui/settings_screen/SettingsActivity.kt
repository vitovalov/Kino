package com.vitovalov.kino.ui.settings_screen

import android.os.Bundle
import com.vitovalov.kino.R
import com.vitovalov.kino.ui.BaseNavigationActivity

class SettingsActivity : BaseNavigationActivity() {
    override fun onInit(savedInstanceState: Bundle?) {
    }

    override val navigationItemId: Int
        get() = R.id.navigation_settings

    override val rootLayoutId: Int
        get() = R.layout.activity_settings
}
