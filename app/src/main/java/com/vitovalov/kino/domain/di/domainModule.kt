package com.vitovalov.kino.domain.di

import com.vitovalov.kino.domain.ShowListRepository
import com.vitovalov.kino.domain.usecase.GetShowList
import org.koin.dsl.module

val domainModule = module {
    factory { GetShowList(get()) }

    single { ShowListRepository(get()) }
}
