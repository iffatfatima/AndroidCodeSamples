package com.app.codesamples.network.data.repository

import android.util.Log
import com.app.codesamples.network.data.network.DataService
import com.app.codesamples.network.data.response.DataResponse
import com.app.codesamples.network.data.response.toDataItem
import com.app.codesamples.network.domain.model.DataItem
import com.app.codesamples.network.domain.repository.DataRepository
import com.app.codesamples.network.domain.usecase.base.Result
import com.app.codesamples.network.domain.usecase.base.Error
import javax.inject.Inject


class DataRepositoryImpl @Inject constructor(val dataService: DataService) : DataRepository {
    override suspend fun getData(id: Int): Result<DataItem, Error> {
        try {
            val response = dataService.getData(id)

            if (response.isSuccessful && response.body() != null) {
                val res = (response.body() as DataResponse)
                return Result.Success(res.toDataItem())
            } else {
                Log.e("Error", response.errorBody().toString())
                return Result.Failure(Error.ResponseError)
            }
        } catch (error: Exception) {
            Log.e("Error", error.toString())
            return Result.Failure(Error.NetworkError)
        }
    }

}


