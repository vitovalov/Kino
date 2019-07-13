package com.vitovalov.kino.ui.showlist

import com.vitovalov.kino.ui.model.ShowUo

interface ShowListContract {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun showList(items: List<ShowUo>)
        fun showError()
        fun hideError()
    }

    interface Presenter {
        fun onViewReady(view: View)
        fun onPageEnd()
        fun onDestroy()
    }
}