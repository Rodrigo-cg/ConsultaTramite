package com.example.consultatramite

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface apiService {


    @GET
    suspend fun getValidUser(@Url url:String) : Response<dataTramite>


}

