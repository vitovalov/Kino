package com.vitovalov.kino.ui.more_screen

import android.os.Bundle
import com.vitovalov.kino.R
import com.vitovalov.kino.ui.BaseNavigationActivity

class MoreActivity : BaseNavigationActivity() {
    override fun onInit(savedInstanceState: Bundle?) {

    }

    override val navigationItemId: Int
        get() = R.id.navigation_more

    override val rootLayoutId: Int
        get() = R.layout.activity_more
}
