package com.vitovalov.kino.ui.showlist

import com.vitovalov.kino.domain.usecase.GetShowList
import com.vitovalov.kino.ui.BasePresenter
import com.vitovalov.kino.ui.mapper.ShowListUoMapper

class ShowListPresenter(
    private val getShowList: GetShowList,
    private val showListUoMapper: ShowListUoMapper
): BasePresenter(), ShowListContract.Presenter {

    //region base presenter
    override fun onGenericError(throwable: Throwable) {
        TODO("not implemented")
    }
    //endregion

    //region presenter contract
    override fun onViewReady(view: ShowListContract.View) {
        TODO("not implemented")
    }

    override fun onBottomReached() {
        TODO("not implemented")
    }

    override fun onDestroy() {
        TODO("not implemented")
    }
    //endregion
}