package com.hskris.newsapp.domain

import com.hskris.newsapp.data.repository.NewsRepository
import com.hskris.newsapp.data.repository.remote.models.Articles
import com.hskris.newsapp.data.repository.remote.models.NewsResponse
import com.hskris.newsapp.data.repository.remote.models.Source
import com.hskris.newsapp.domain.models.News
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

class GetHeadlineUseCaseTest : Spek({
    given("get headline use case") {
        val scheduler = TrampolineSchedulerProvider()
        val repo : NewsRepository = mock()
        val usecase = GetHeadlineUseCase(repo, scheduler)

        on("getting by category") {

            val articles = listOf(
                Articles(Source("1", ""), "hans", "", "", "", "", "2019-06-20T23:07:41Z", ""),
                Articles(Source("2", ""), "rico", "", "", "", "", "2019-06-20T23:07:41Z", ""))

            val newsResponse = NewsResponse("", 0, articles)
            val newsSingle = Single.just(newsResponse)

            doReturn(newsSingle).`when`(repo).fetchHeadlines(any())

            val result = usecase.getByCategory("some category")

            it("should call fetch headline on repo") {
                verify(repo).fetchHeadlines(any())
            }

            it("should return correct news model single") {
                result.test()
                    .assertNoErrors()
                    .assertValue {
                        it.size == 2 && it[0].author == "hans"
                    }
            }
        }
    }
})