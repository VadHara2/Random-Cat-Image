package com.example.randomcatimage.api

import retrofit2.http.Path
import retrofit2.http.Query

data class CatImgJsonItem(
    val api_key: String ,
    val breeds: List<Breed>,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)