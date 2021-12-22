package com.app.codesamples.network.domain.usecase

import com.app.codesamples.network.domain.repository.DataRepository
import com.app.codesamples.network.domain.usecase.base.BaseUseCase
import com.app.codesamples.network.domain.usecase.base.Result
import javax.inject.Inject


class FetchDataUC @Inject constructor(private val dataRepository: DataRepository) : BaseUseCase<Int>() {
    override suspend fun run(params: Int) {
        // Started loading
        resultChannel.send(Result.State.Loading)
        // Get person from persistence and send it, synchronous
        resultChannel.send(dataRepository.getData(params))
        resultChannel.send(Result.State.Loaded)
    }
}