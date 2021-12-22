package com.app.codesamples.network.domain.repository

import com.app.codesamples.network.domain.model.DataItem
import com.app.codesamples.network.domain.usecase.base.Result
import com.app.codesamples.network.domain.usecase.base.Error

interface DataRepository {
    suspend fun getData(page: Int): Result<DataItem, Error>
}