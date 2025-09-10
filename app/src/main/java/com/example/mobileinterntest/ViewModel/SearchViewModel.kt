package com.example.mobileinterntest.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileinterntest.data.model.Place
import com.example.mobileinterntest.data.repository.SearchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch


class SearchViewModel () : ViewModel() {
    private val _results = MutableLiveData<List<Place>>()
    val results: LiveData<List<Place>> = _results
    private val repository = SearchRepository()

    private val queryFlow = MutableStateFlow("")

    init {
        viewModelScope.launch {
            queryFlow
                .debounce (1000)
                .distinctUntilChanged()
                .flatMapLatest { query ->
                    if (query.isBlank()) {
                        flow { emit(emptyList()) }
                    } else {
                        flow {
                            try {
                                val apiKey = "pk.b3377b3fe05053069e1ba6244df93ea3"
                                val places = repository.searchPlaces(apiKey, query)
                                emit(places)
                            } catch (e: Exception) {
                                emit(emptyList())
                            }
                        }
                    }
                }

            .collect{ places ->
                _results.postValue(places)
            }


        }

    }

    fun setQuery(query: String) {
        queryFlow.value = query
    }

}