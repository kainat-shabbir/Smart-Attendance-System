package com.iiuifyp.location.base.attendance.service;

import com.iiuifyp.location.base.attendance.model.Employee;
import com.iiuifyp.location.base.attendance.url.EndPoint;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface EmployeeLoginService {

    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(EndPoint.EMPLOYEE_LOGIN_URL)
    Call<Employee> employee_login(@Field("e_email") String e_email, @Field("e_password") String e_password);

}