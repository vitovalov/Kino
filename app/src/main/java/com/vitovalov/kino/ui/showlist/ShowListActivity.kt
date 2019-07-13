package com.vitovalov.kino.ui.showlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vitovalov.kino.R
import com.vitovalov.kino.ui.model.ShowUo

class ShowListActivity : AppCompatActivity(), ShowListContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //region view contract
    override fun showProgress() {
        TODO("not implemented")
    }

    override fun hideProgress() {
        TODO("not implemented")
    }

    override fun showList(items: List<ShowUo>) {
        TODO("not implemented")
    }

    override fun showError() {
        TODO("not implemented")
    }

    override fun hideError() {
        TODO("not implemented")
    }
    //endregion
}
