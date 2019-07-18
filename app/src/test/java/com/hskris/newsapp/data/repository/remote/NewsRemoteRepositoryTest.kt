package com.hskris.newsapp.data.repository.remote

import com.hskris.newsapp.data.repository.remote.api.ApiService
import com.nhaarman.mockitokotlin2.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert.assertEquals

class NewsRemoteRepositoryTest : Spek({
    given("NewsRepository") {
        val api : ApiService = mock()
        val repo = NewsRemoteRepository(api)

        on("calling fetch headline") {
            repo.fetchHeadline("")

            it("should call fetch headline on api service") {
                verify(api).getHeadline("")
            }
        }
    }
})