package com.iiuifyp.location.base.attendance.service;

import com.iiuifyp.location.base.attendance.model.Employee;
import com.iiuifyp.location.base.attendance.url.EndPoint;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface AddEmployeeService {

    @Headers({"Accept: application/json"})
    @Multipart
    @POST(EndPoint.ADD_EMPLOYEE_URL)
    Call<Employee> add_employee(
            @Part("e_name") RequestBody e_name,
            @Part("e_email") RequestBody e_email,
            @Part("e_password") RequestBody e_password,
            @Part("e_phone") RequestBody e_phone,
            @Part("e_salary") RequestBody e_salary,
            @Part("e_designation") RequestBody e_designation,
            @Part("e_gender") RequestBody e_gender,
            @Part MultipartBody.Part e_profile_img);
}
