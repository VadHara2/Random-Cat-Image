package com.example.randomcatimage

import com.example.randomcatimage.api.CatImgJson
import com.example.randomcatimage.api.CatImgJsonItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiRequests {
    @Headers(
        "X-Api-Key: 707d4a14-c49f-40fb-bae3-2e52e6c1763e"
    )
    @GET("/v1/images/search")
    fun getData(): Call<CatImgJson>
}