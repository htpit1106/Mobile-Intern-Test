package com.example.mobileinterntest.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileinterntest.data.model.Place
import com.example.mobileinterntest.data.repository.SearchRepository
import kotlinx.coroutines.launch


class SearchViewModel () : ViewModel() {
    private val _results = MutableLiveData<List<Place>>()
    val results: LiveData<List<Place>> = _results
    private val repository = SearchRepository()
    fun setQuery(query: String) {
        if (query.isBlank()) {
            _results.value = emptyList()
            return

        }
        // goi api
        viewModelScope.launch {
            try {
                val apiKey = "pk.b3377b3fe05053069e1ba6244df93ea3"
                val places = repository.searchPlaces(apiKey, query)
                _results.value = places
            } catch (e: Exception) {
                _results.value = emptyList()
            }

        }
    }

}