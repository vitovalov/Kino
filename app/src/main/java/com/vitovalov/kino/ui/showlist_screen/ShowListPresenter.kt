package com.vitovalov.kino.ui.showlist_screen

import com.vitovalov.kino.domain.Failure
import com.vitovalov.kino.domain.model.ShowBo
import com.vitovalov.kino.domain.usecase.GetShowList
import com.vitovalov.kino.ui.BasePresenter
import com.vitovalov.kino.ui.mapper.ShowListUoMapper
import kotlinx.coroutines.cancel

class ShowListPresenter(
    private val getShowList: GetShowList,
    private val showListUoMapper: ShowListUoMapper
) : BasePresenter(), ShowListContract.Presenter {

    private var isLoading: Boolean = false
    private var page: Int = 1
    private var view: ShowListContract.View? = null

    private fun handleSuccess(characters: List<ShowBo>) {
        view?.hideProgress()
        page++
        view?.showList(showListUoMapper.toUo(characters))
        isLoading = false
    }

    private fun handleError(failure: Failure) {
        view?.hideProgress()
        view?.showError()
        isLoading = false
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
                it.either(::handleError, ::handleSuccess)
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