package com.vitovalov.kino.ui.showlist_screen

import com.vitovalov.kino.domain.model.Show

interface ShowListContract {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun showList(items: List<Show>)
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
