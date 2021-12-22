package com.app.codesamples.network.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.codesamples.network.domain.usecase.base.Error
import com.app.codesamples.network.domain.usecase.base.Result
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope{

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    protected abstract val receiveChannel: ReceiveChannel<Result<Any, Error>>
    abstract fun resolve(value: Result<Any, Error>)

    init {
        launch {
            receiveChannel.consumeEach {
                resolve(it)
            }
        }
    }

    override fun onCleared() {
        receiveChannel.cancel()
        coroutineContext.cancel()
        super.onCleared()
    }
}
