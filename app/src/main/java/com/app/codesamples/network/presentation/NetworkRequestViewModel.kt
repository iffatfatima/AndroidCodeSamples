package com.app.codesamples.network.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.codesamples.network.domain.model.DataItem
import com.app.codesamples.network.domain.usecase.FetchDataUC
import com.app.codesamples.network.domain.usecase.base.Error
import com.app.codesamples.network.domain.usecase.base.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.ReceiveChannel
import javax.inject.Inject

@HiltViewModel
class NetworkRequestViewModel @Inject constructor(
    private val  fetchDataUseCase: FetchDataUC
) : BaseViewModel() {

    private val _items = MutableLiveData<DataItem>().apply { value = DataItem() }
    val items: LiveData<DataItem> = _items

    private val _dataLoading = MutableLiveData<Update>()
    val dataLoading: LiveData<Update> = _dataLoading

    override val receiveChannel: ReceiveChannel<Result<Any, Error>>
        get() = fetchDataUseCase.receiveChannel

    override fun resolve(value: Result<Any, Error>) {
        value.handleResult(::handleSuccess, ::handleFailure, ::handleState)
    }

    init {
        fetchDataUseCase(1)
    }

    fun handleSuccess(data: Any) {
        _items.postValue(data as DataItem)
        _dataLoading.postValue(Update(STATE.SUCCESS, ""))
    }

    fun handleFailure(error: Error) {
        _dataLoading.postValue(Update(STATE.ERROR, error.toString()))
    }

    fun handleState(state: Result.State) {
       if (state is Result.State.Loading ){
           _dataLoading.postValue(Update(STATE.LOADING, "Loading data"))
       }
    }
}