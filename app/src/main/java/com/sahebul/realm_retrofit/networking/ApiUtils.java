package com.sahebul.realm_retrofit.networking;
/**
 * Created by Sahebul on 27/4/19.
 */
public class ApiUtils {


    private static final String BASE_URL = "http://dummy.restapiexample.com/api/v1/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
