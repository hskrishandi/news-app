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

    companion object {
        private const val INTERESTS = "football"
    }

    private val disposables = CompositeDisposable()

    val newsState by lazy {
        MutableLiveData<NewsState>()
    }

    val carousellState by lazy {
        MutableLiveData<CarousellState>()
    }

    fun getNews(category: String){
        newsState.value = NewsState.Loading
        val subscription = getHeadlineUseCase.getByCategory(category).subscribe({
            Log.d("NewsVideoModel", it.toString())
            newsState.value = NewsState.Data(it)
        }, {
            newsState.value = NewsState.Error
            Log.e("NewsViewModel", "Error: $it")
        })
        disposables.add(subscription)
    }

    fun getCarousell(){
        carousellState.value = CarousellState.Loading
        val subscription = getEverythingUseCase.getByKeywords(INTERESTS).subscribe({
            carousellState.value = CarousellState.Data(it)
        },{
            carousellState.value = CarousellState.Error
            Log.e("NewsViewModel", "Error: $it")
        })

        disposables.add(subscription)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    sealed class CarousellState {
        object Loading : CarousellState()
        data class Data(val news: List<News>) : CarousellState()
        object Error : CarousellState()
    }

    sealed class NewsState {
        object Loading : NewsState()
        data class Data(val news: List<News>) : NewsState()
        object Error : NewsState()
    }
}