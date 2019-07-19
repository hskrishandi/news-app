package com.hskris.newsapp.domain

import com.hskris.newsapp.data.repository.NewsRepository
import com.hskris.newsapp.data.repository.remote.models.NewsResponse
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class GetEverythingUseCaseTest : Spek({
    given("get everything use case") {
        val scheduler = TrampolineSchedulerProvider()
        val repo : NewsRepository = mock()
        val usecase = GetEverythingUseCase(repo, scheduler)

        on("getting by category") {
            val newsResponse = NewsResponse("", 0, emptyList())
            val newsSingle = Single.just(newsResponse)
            doReturn(newsSingle).`when`(repo).fetchEverything(any())

            usecase.getByKeywords("some keywords")

            it("should call fetch headline on repo") {
                verify(repo).fetchEverything(any())
            }
        }
    }
})