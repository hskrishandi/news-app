package com.hskris.newsapp.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hskris.newsapp.domain.GetEverythingUseCase
import com.hskris.newsapp.domain.GetHeadlineUseCase
import com.hskris.newsapp.domain.models.News
import io.reactivex.disposables.CompositeDisposable

class NewsViewModel(
    private val getHeadlineUseCase: GetHeadlineUseCase,
    private val getEverythingUseCase: GetEverythingUseCase
) : ViewModel() {

    private val disposables = CompositeDisposable()

    val carousell by lazy {
        MutableLiveData<List<News>>()
    }

    val news by lazy {
        MutableLiveData<List<News>>()
    }

    val interests = "football"

    fun getNews(category: String){
        val subscription = getHeadlineUseCase.getByCategory(category).subscribe({
            news.value = it
        }, {
            Log.d("NewsViewModel", "Error: $it")
        })
    }

    fun getCarousell(){
        val subscription = getEverythingUseCase.getByKeywords(interests).subscribe({
            carousell.value = it
            Log.d("NewsViewModel", it.toString())
        },{
            Log.d("NewsViewModel", "Error: $it")
        })

        disposables.add(subscription)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}