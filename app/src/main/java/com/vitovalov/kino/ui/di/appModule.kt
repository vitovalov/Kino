package com.vitovalov.kino.ui.di

import com.vitovalov.kino.ui.showlist_screen.ShowListContract
import com.vitovalov.kino.ui.showlist_screen.ShowListPresenter
import org.koin.dsl.module

val appModule = module {
    factory<ShowListContract.Presenter> { ShowListPresenter(get()) }
}
