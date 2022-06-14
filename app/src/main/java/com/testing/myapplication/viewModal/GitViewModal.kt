package com.testing.myapplication.viewModal

import android.content.ContentValues.TAG
import android.nfc.Tag
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.testing.myapplication.apiResponseModal.GitResponseModal
import com.testing.myapplication.apiServices.getTrendingResporitories
import com.testing.myapplication.apiServices.getTrendingResporitoriesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates

class GitViewModal:ViewModel() {
    var dataList= MutableLiveData<GitResponseModal>()
var loadingState = MutableLiveData<Int>()

    fun getData(){
       loadingState.value = 0
       getTrendingResporitoriesApi.retrofitService.getGitdata().enqueue(object : Callback<GitResponseModal> {
           override fun onResponse(
               call: Call<GitResponseModal>,
               response: Response<GitResponseModal?>
           ) {
               if(response.code() == 200){
                   loadingState.value = 1
                   dataList.value = response.body()
               }
               else{
  Log.e(TAG,"Error")
                }
           }

           override fun onFailure(call: Call<GitResponseModal?>, t: Throwable) {
               Log.e("TAG",t.message.toString())
               loadingState.value = 2

           }
       })
   }
}