package com.hskris.newsapp.presentation

import com.hskris.newsapp.domain.GetEverythingUseCase
import com.hskris.newsapp.domain.GetHeadlineUseCase
import com.hskris.newsapp.domain.models.News
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert.assertEquals
import java.util.*

class NewsViewModelTest : Spek({
    given("news view model"){
        val getHeadlineUseCase: GetHeadlineUseCase = mock()
        val getEverythingUseCase: GetEverythingUseCase = mock()

        val newsList = listOf(News("", "", Date(), "", "", ""))
        doReturn(Single.just(newsList)).`when`(getEverythingUseCase).getByKeywords(any())

        val viewModel = NewsViewModel(getHeadlineUseCase, getEverythingUseCase)

        on("getting front news"){
            viewModel.getFrontNews()

            it("should call get everything use case get by keywords"){
                verify(getEverythingUseCase).getByKeywords(any())
            }
        }
    }
})