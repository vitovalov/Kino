package com.vitovalov.kino.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

abstract class BaseActivity : AppCompatActivity() {

    protected abstract val rootLayoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(rootLayoutId)
    }
    protected fun finalize() {
        Timber.d("Finishing activity %s", this)
    }
}
