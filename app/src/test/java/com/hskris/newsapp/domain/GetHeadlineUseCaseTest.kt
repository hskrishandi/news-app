package com.hskris.newsapp.domain

import com.hskris.newsapp.data.repository.NewsRepository
import com.hskris.newsapp.data.repository.remote.models.NewsResponse
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert.assertEquals

class GetHeadlineUseCaseTest : Spek({
    given("get headline use case") {
        val scheduler = TrampolineSchedulerProvider()
        val repo : NewsRepository = mock()
        val usecase = GetHeadlineUseCase(repo, scheduler)

        on("getting by category") {
            val newsResponse = NewsResponse("", 0, emptyList())
            val newsSingle = Single.just(newsResponse)
            doReturn(newsSingle).`when`(repo).fetchHeadlines(any())

            val result = usecase.getByCategory("some category")

            it("should call fetch headline on repo") {
                verify(repo).fetchHeadlines(any())
            }

            it("should return correct news model single") {
                result.subscribe{ news ->
                    assertEquals(newsResponse.articles.size, news.size)
                }
            }
        }
    }
})