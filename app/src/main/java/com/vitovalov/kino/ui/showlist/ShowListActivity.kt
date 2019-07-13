package com.vitovalov.kino.ui.showlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vitovalov.kino.R
import com.vitovalov.kino.extensions.gone
import com.vitovalov.kino.extensions.visible
import com.vitovalov.kino.ui.BaseNavigationActivity
import com.vitovalov.kino.ui.model.ShowUo
import kotlinx.android.synthetic.main.activity_show_list.*
import org.koin.android.ext.android.inject

class ShowListActivity : BaseNavigationActivity(), ShowListContract.View {

    private val presenter: ShowListContract.Presenter by inject()
    private val adapter: ShowListAdapter = ShowListAdapter()

    override fun onInit(savedInstanceState: Bundle?) {
        initViews()
        presenter.onViewReady(this)
    }

    private fun initViews() {
        val gridLayoutManager =
            GridLayoutManager(this@ShowListActivity, 1)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (gridLayoutManager.findLastCompletelyVisibleItemPosition() == gridLayoutManager.itemCount - 1)
                    presenter.onPageEnd()
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }

    //region view contract
    override fun showProgress() {
        progressText.visible()
        progressText.text = "Loading..."
    }

    override fun hideProgress() {
        progressText.gone()
    }

    override fun showList(items: List<ShowUo>) {
        recyclerView.visible()
        adapter.addItems(items)
    }

    override fun showError() {
        progressText.visible()
        progressText.text = "Error"
    }

    override fun hideError() {
        progressText.gone()
    }

    //endregion
    override val navigationItemId: Int
        get() = R.id.navigation_home

    override val rootLayoutId: Int
        get() = R.layout.activity_show_list
    //endregion
}
