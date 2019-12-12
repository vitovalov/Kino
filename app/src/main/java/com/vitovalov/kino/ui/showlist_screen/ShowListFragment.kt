package com.vitovalov.kino.ui.showlist_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.vitovalov.kino.R
import com.vitovalov.kino.extensions.gone
import com.vitovalov.kino.extensions.visible
import com.vitovalov.kino.ui.model.ShowUo
import kotlinx.android.synthetic.main.fragment_show_list.*
import org.koin.android.ext.android.inject

class ShowListFragment : Fragment(), ShowListContract.View {

    private val presenter: ShowListContract.Presenter by inject()
    private val adapter: ShowListAdapter = ShowListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_show_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        Snackbar.make(container, getString(R.string.generic_error), Snackbar.LENGTH_LONG).show()
    }

    override fun hideError() {
        progressText.gone()
    }

    override fun showOfflineError() {
        Snackbar.make(container, getString(R.string.errors_no_network), Snackbar.LENGTH_LONG).show()
    }
    //endregion view contract
}
