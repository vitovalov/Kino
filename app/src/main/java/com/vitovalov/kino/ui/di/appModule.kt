package com.vitovalov.kino.ui.di

import com.vitovalov.kino.ui.showlist.ShowListContract
import com.vitovalov.kino.ui.showlist.ShowListPresenter
import org.koin.dsl.module

val appModule = module {
    factory<ShowListContract.Presenter> { ShowListPresenter(get(), get()) }
}
