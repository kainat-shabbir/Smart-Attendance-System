package com.iiuifyp.location.base.attendance.service;

import com.iiuifyp.location.base.attendance.model.Admin;
import com.iiuifyp.location.base.attendance.url.EndPoint;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AdminLoginService {

    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(EndPoint.ADMIN_LOGIN_URL)
    Call<Admin> admin_login(@Field("a_email") String a_email, @Field("a_password") String a_password);

}
