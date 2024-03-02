package com.iiuifyp.location.base.attendance.service;

import com.google.gson.JsonObject;
import com.iiuifyp.location.base.attendance.url.EndPoint;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GetQueryService {

    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(EndPoint.GET_QUERY_URL)
    Call<JsonObject> getQuery(
            @Field("q_status") String q_status);
}
