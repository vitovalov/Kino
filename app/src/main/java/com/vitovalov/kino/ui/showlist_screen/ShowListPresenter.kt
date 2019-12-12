package com.vitovalov.kino.ui.showlist_screen

import com.vitovalov.kino.domain.Failure
import com.vitovalov.kino.domain.Result
import com.vitovalov.kino.domain.model.Show
import com.vitovalov.kino.domain.usecase.GetShowList
import com.vitovalov.kino.ui.BasePresenter
import kotlinx.coroutines.cancel
import java.net.UnknownHostException

class ShowListPresenter(
    private val getShowList: GetShowList
) : BasePresenter(), ShowListContract.Presenter {

    private var isLoading: Boolean = false
    private var page: Int = 1
    private var view: ShowListContract.View? = null

    internal fun handleSuccess(items: List<Show>) {
        view?.hideProgress()
        page++
        view?.showList(prepareListForUi(items))
        isLoading = false
    }

    private fun prepareListForUi(items: List<Show>): List<Show> {
        val sortedItems = items.sortedBy { it.voteAverage }
        return sortedItems.reversed()
    }

    internal fun handleError(failure: Failure?, data: List<Show>?) {
        data?.let { view?.showList(prepareListForUi(it)) }

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
                when (it.status) {
                    Result.Status.SUCCESS -> handleSuccess(it.data!!)
                    Result.Status.ERROR -> handleError(it.error, it.data)
                }
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