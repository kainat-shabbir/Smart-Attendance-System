package com.iiuifyp.location.base.attendance.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Query {

    @SerializedName("q_id")
    @Expose
    private int q_id;

    @SerializedName("q_employee_id")
    @Expose
    private int q_employee_id;

    @SerializedName("e_name")
    @Expose
    private String e_name;

    @SerializedName("q_title")
    @Expose
    private String q_title;

    @SerializedName("q_description")
    @Expose
    private String q_description;

    @SerializedName("q_status")
    @Expose
    private String q_status;

    @SerializedName("q_response")
    @Expose
    private String q_response;

    @SerializedName("q_created_DateTime")
    @Expose
    private String q_created_DateTime;


    @SerializedName("q_updated_DateTime")
    @Expose
    private String q_updated_DateTime;
    @SerializedName("code")
    @Expose
    private int code;
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("message")
    @Expose
    private String message;

    public Query() {
    }

    public Query(int q_id,  String q_title, String q_description, String q_created_DateTime,String e_name) {
        this.q_id = q_id;
        this.e_name = e_name;
        this.q_title = q_title;
        this.q_description = q_description;
        this.q_created_DateTime = q_created_DateTime;
    }

    public int getQ_id() {
        return q_id;
    }

    public void setQ_id(int q_id) {
        this.q_id = q_id;
    }

    public int getQ_employee_id() {
        return q_employee_id;
    }

    public void setQ_employee_id(int q_employee_id) {
        this.q_employee_id = q_employee_id;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public String getQ_title() {
        return q_title;
    }

    public void setQ_title(String q_title) {
        this.q_title = q_title;
    }

    public String getQ_description() {
        return q_description;
    }

    public void setQ_description(String q_description) {
        this.q_description = q_description;
    }

    public String getQ_status() {
        return q_status;
    }

    public void setQ_status(String q_status) {
        this.q_status = q_status;
    }

    public String getQ_response() {
        return q_response;
    }

    public void setQ_response(String q_response) {
        this.q_response = q_response;
    }

    public String getQ_created_DateTime() {
        return q_created_DateTime;
    }

    public void setQ_created_DateTime(String q_created_DateTime) {
        this.q_created_DateTime = q_created_DateTime;
    }

    public String getQ_updated_DateTime() {
        return q_updated_DateTime;
    }

    public void setQ_updated_DateTime(String q_updated_DateTime) {
        this.q_updated_DateTime = q_updated_DateTime;
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
