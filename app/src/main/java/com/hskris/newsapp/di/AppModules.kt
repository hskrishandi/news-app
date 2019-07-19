package com.hskris.newsapp.di

import com.hskris.newsapp.data.repository.NewsRepository
import com.hskris.newsapp.data.repository.remote.NewsRemoteRepository
import com.hskris.newsapp.data.repository.remote.api.Api
import com.hskris.newsapp.domain.BaseSchedulerProvider
import com.hskris.newsapp.domain.GetEverythingUseCase
import com.hskris.newsapp.domain.GetHeadlineUseCase
import com.hskris.newsapp.domain.SchedulerProvider
import com.hskris.newsapp.presentation.NewsViewModel
import org.koin.dsl.module

val appModule = module {

    single<NewsRepository> { NewsRemoteRepository(Api.getInstance()) }

    factory<BaseSchedulerProvider> { SchedulerProvider() }

    factory { GetEverythingUseCase(get(), get()) }
    factory { GetHeadlineUseCase(get(), get()) }

    factory { NewsViewModel(get(), get()) }

}