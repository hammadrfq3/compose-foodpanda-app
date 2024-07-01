package com.food.foodpanda.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainScreenViewModel : ViewModel() {


    private val _onFilePermissionGranted = MutableLiveData<Boolean>()
    val onFilePermissionGranted: LiveData<Boolean> = _onFilePermissionGranted

    fun setPermissionGranted(text: Boolean,context: Context) {
        _onFilePermissionGranted.value = text
    }




}