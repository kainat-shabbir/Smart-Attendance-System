package com.iiuifyp.location.base.attendance.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Employee {
    @SerializedName("e_id ")
    @Expose
    private int e_id;

    @SerializedName("e_name")
    @Expose
    private String e_name;

    @SerializedName("e_email")
    @Expose
    private String e_email;

    @SerializedName("e_password")
    @Expose
    private String e_password;

    @SerializedName("e_phone")
    @Expose
    private String e_phone;

    @SerializedName("e_salary")
    @Expose
    private int e_salary;

    @SerializedName("e_designation")
    @Expose
    private String e_designation;

    @SerializedName("e_gender")
    @Expose
    private int e_gender;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("e_profile_img")
    @Expose
    private String e_profile_img;

    @SerializedName("e_status")
    @Expose
    private String e_status;

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("error")
    @Expose
    private boolean error;

    @SerializedName("message")
    @Expose
    private String message;

    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public String getE_email() {
        return e_email;
    }

    public void setE_email(String e_email) {
        this.e_email = e_email;
    }

    public String getE_password() {
        return e_password;
    }

    public void setE_password(String e_password) {
        this.e_password = e_password;
    }

    public String getE_phone() {
        return e_phone;
    }

    public void setE_phone(String e_phone) {
        this.e_phone = e_phone;
    }

    public int getE_salary() {
        return e_salary;
    }

    public void setE_salary(int e_salary) {
        this.e_salary = e_salary;
    }

    public String getE_designation() {
        return e_designation;
    }

    public void setE_designation(String e_designation) {
        this.e_designation = e_designation;
    }

    public int getE_gender() {
        return e_gender;
    }

    public void setE_gender(int e_gender) {
        this.e_gender = e_gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getE_profile_img() {
        return e_profile_img;
    }

    public void setE_profile_img(String e_profile_img) {
        this.e_profile_img = e_profile_img;
    }

    public String getE_status() {
        return e_status;
    }

    public void setE_status(String e_status) {
        this.e_status = e_status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
