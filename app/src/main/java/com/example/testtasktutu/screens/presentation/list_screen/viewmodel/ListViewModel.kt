package com.example.testtasktutu.screens.presentation.list_screen.viewmodel

import android.os.Bundle
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.UserShort
import com.example.domain.usecases.GetListUseCase
import com.example.testtasktutu.screens.presentation.list_screen.intent.ListIntent
import com.example.testtasktutu.screens.presentation.list_screen.viewstate.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val getListUseCase: GetListUseCase) : ViewModel() {

    private var list: List<UserShort> = listOf()
    private var _state = SingleLiveData<ListState>()
    val state: LiveData<ListState> = _state

    fun handleIntent(intent: ListIntent) {
        when (intent) {
            is ListIntent.FetchList -> getList()
            is ListIntent.Navigate -> navigate(intent.bundle)
            is ListIntent.RestoreState -> restoreState()
        }
    }

    private fun getList() {
        viewModelScope.launch(Dispatchers.Main) {
            _state.postValue(ListState.Loading)
            _state.postValue(try {
                list = getListUseCase.execute(Dispatchers.IO)
                if (list.isNotEmpty()) {
                    ListState.Repos(list)
                } else {
                    ListState.Idle
                }
            } catch (e: Exception) {
                ListState.Error(e.localizedMessage)
            })
        }
    }

    private fun navigate(bundle: Bundle) {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = ListState.Navigate(bundle)
        }
    }

    private fun restoreState() {
        if (list.isNotEmpty()) {
            viewModelScope.launch(Dispatchers.Main) {
                _state.value = ListState.Repos(list)
            }
        }
    }
}

class SingleLiveData<T> : MutableLiveData<T>() {
    private val pending = AtomicBoolean()

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner) { t ->
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        }
    }

    @MainThread
    override fun setValue(value: T?) {
        pending.set(true)
        super.setValue(value)
    }
}