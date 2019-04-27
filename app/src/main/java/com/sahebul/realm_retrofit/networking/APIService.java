package com.sahebul.realm_retrofit.networking;


import com.sahebul.realm_retrofit.models.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by Sahebul on 27/4/19.
 */
public interface APIService {
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("employees")
    Call<List<Employee>> getEmployeeList();

}
