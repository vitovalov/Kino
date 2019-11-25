package com.vitovalov.kino.ui.showlist_screen

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
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
        val gridColumns = 2
        val recyclerViewLayoutManager =
            StaggeredGridLayoutManager(gridColumns, StaggeredGridLayoutManager.VERTICAL)
        recyclerViewLayoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        recyclerView.adapter = adapter
        recyclerView.layoutManager = recyclerViewLayoutManager
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    presenter.onPageEnd()
                }
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }

    //region view contract
    override fun showProgress() {
        progressText.visible()
        progressText.text = getString(R.string.generic_loading)
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
        progressText.text = getString(R.string.generic_error)
    }

    override fun hideError() {
        progressText.gone()
    }

    override fun showOfflineError() {
        Toast.makeText(this, getString(R.string.errors_no_network), Toast.LENGTH_SHORT).show()
    }
    //endregion view contract

    //region BaseNavigationActivity
    override val navigationItemId: Int
        get() = R.id.navigation_home

    override val rootLayoutId: Int
        get() = R.layout.activity_show_list
    //endregion
}
