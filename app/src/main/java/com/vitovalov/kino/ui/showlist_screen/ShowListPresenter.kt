package com.vitovalov.kino.ui.showlist_screen

import com.vitovalov.kino.domain.Failure
import com.vitovalov.kino.domain.model.ShowBo
import com.vitovalov.kino.domain.usecase.GetShowList
import com.vitovalov.kino.ui.BasePresenter
import com.vitovalov.kino.ui.mapper.ShowListUoMapper
import kotlinx.coroutines.cancel
import java.net.UnknownHostException

class ShowListPresenter(
    private val getShowList: GetShowList,
    private val showListUoMapper: ShowListUoMapper
) : BasePresenter(), ShowListContract.Presenter {

    private var isLoading: Boolean = false
    private var page: Int = 1
    private var view: ShowListContract.View? = null

    internal fun handleSuccess(items: List<ShowBo>) {
        view?.hideProgress()
        page++
        val sortedItems = items.sortedBy { it.voteAverage }
        view?.showList(showListUoMapper.toUo(sortedItems.reversed()))
        isLoading = false
    }

    internal fun handleError(failure: Failure) {
        if ((failure as Failure.Error).value is UnknownHostException) {
            view?.showOfflineError()
        } else {
            view?.showError()
        }
        isLoading = false
        view?.hideProgress()
    }

    //region presenter contract
    override fun onViewReady(view: ShowListContract.View) {
        this.view = view
        loadPage()
    }

    private fun loadPage() {
        if (!isLoading) {
            isLoading = true
            view?.showProgress()
            getShowList.invoke(this, GetShowList.Params(page))
            {
                it.fold(::handleError, ::handleSuccess)
            }
        }
    }

    override fun onPageEnd() {
        loadPage()
    }

    override fun onDestroy() {
        view = null
        coroutineContext.cancel()
    }
    //endregion

    //region base presenter
    override fun onGenericError(throwable: Throwable) {
        view?.showError()
    }
    //endregion
}