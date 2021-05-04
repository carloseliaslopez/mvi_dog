package com.example.mvi_dog.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvi_dog.intent.Intent
import com.example.mvi_dog.repository.DogRepository
import com.example.mvi_dog.utils.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainViewModel
@ViewModelInject
constructor(
    private val dogRepository: DogRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val userIntent = Channel<Intent>(Channel.UNLIMITED)

    private val _dataState= MutableStateFlow<DataState>(DataState.Idle)

    val dataState: StateFlow<DataState>
        get() = _dataState
    init {
        setStateEvent()
    }
    fun setStateEvent() {
        viewModelScope.launch {
          userIntent.consumeAsFlow().collect { intnt ->
              when (intnt){
                 is Intent.GetDogEvent -> {
                     dogRepository.getDogs()
                         .onEach {
                             _dataState.value = it
                         }
                         .launchIn(viewModelScope)
                 }
                  Intent.None -> {

                  }
              }

          }
            /*
            userIntent.consumeAsFlow().collect { intnt ->
                when (intnt) {
                    is Intent.GetDogEvent -> {
                        dogRepository.getDogs()
                            .onEach {
                                _dataState.value = it
                            }
                            .launchIn(viewModelScope)
                    }
                    Intent.None -> {
                        // who cares
                    }
                }

            }
            */
        }
    }
}