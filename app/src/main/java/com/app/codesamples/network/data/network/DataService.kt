package com.app.codesamples.network.data.network

import com.app.codesamples.network.data.response.DataResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface DataService {
    @POST(Urls.GET_USER)
    suspend fun getData(@Query("id") id: Int): Response<DataResponse>
}