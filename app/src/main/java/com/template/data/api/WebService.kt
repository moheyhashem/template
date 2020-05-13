package com.template.data.api

import com.template.data.entities.SyndicateData
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

/**
 * REST API access points
 */
interface WebService {

    @GET("api/v2/syndicates/All")
    fun getAllSyndicates(): Single<ApiResponse<List<SyndicateData>>>
}
