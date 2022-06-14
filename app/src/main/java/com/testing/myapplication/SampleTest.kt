package com.testing.myapplication

import com.testing.myapplication.apiResponseModal.GitResponseModal
import com.testing.myapplication.apiServices.getTrendingResporitoriesApi
import retrofit2.Response

class ApiTesting {

    fun test(): String {
        val response: Response<GitResponseModal> =
            getTrendingResporitoriesApi.retrofitService.getGitdata().execute()
        var returnString = ""
        when (response.code()) {
            200 -> returnString = "Successful"
            404 -> returnString = "Error in url"
            500 -> returnString = "Server Error"
        }
        return returnString
    }
}