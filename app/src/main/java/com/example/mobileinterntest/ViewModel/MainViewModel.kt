package com.example.mobileinterntest.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val edtfind = MutableLiveData<String>()
    val text: LiveData<String> = edtfind





}