package com.hskris.newsapp.data.repository.remote

import com.hskris.newsapp.data.repository.NewsRepository
import com.hskris.newsapp.data.repository.remote.api.ApiService
import com.nhaarman.mockitokotlin2.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert.assertEquals

class NewsRemoteRepositoryTest : Spek({
    given("news remote repository") {
        val api : ApiService = mock()
        val repo = NewsRemoteRepository(api)
        val newsQuery = NewsRemoteRepository.NewsRemoteQuery()

        on("calling fetch headline") {
            repo.fetchHeadlines(newsQuery)

            it("should call fetch headline on api service") {
                verify(api).getHeadline(newsQuery.options)
            }
        }
    }

    given("news remote repository news query"){
        val query = NewsRemoteRepository.NewsRemoteQuery()
        val key = "country"
        val value = "id"

        on("adding key value pair"){
            query.add(key, value)

            it("should put into options map"){
                assertEquals(query.options[key], value)
            }
        }

        on("adding map"){
            val map = hashMapOf("a" to "b")
            query.add(map)

            it("should put into options map"){
                assertEquals(query.options["a"], "b")
            }
        }

    }
})