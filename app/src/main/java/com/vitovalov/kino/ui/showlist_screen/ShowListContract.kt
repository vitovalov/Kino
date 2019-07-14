package com.vitovalov.kino.ui.showlist_screen

import com.vitovalov.kino.ui.model.ShowUo

interface ShowListContract {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun showList(items: List<ShowUo>)
        fun showError()
        fun hideError()
        fun showOfflineError()
    }

    interface Presenter {
        fun onViewReady(view: View)
        fun onPageEnd()
        fun onDestroy()
    }
}
