package com.example.testtasktutu.details_screen.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testtasktutu.details_screen.data.models.ParcelDetailsInfo
import com.example.testtasktutu.details_screen.data.models.DetailsInfoData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsInfoLoaderImpl {

    private val _parcelDetailsInfo = MutableLiveData<ParcelDetailsInfo>()
    val parcelDetailsInfo: LiveData<ParcelDetailsInfo> = _parcelDetailsInfo

    fun loadData(login: String, name: String) {

        val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val requestApiRepositories = retrofit.create(RequestApiRepositoryDetailsInfo::class.java)
        val call = requestApiRepositories.getRequest(login, name)
        Log.d("TAG1123331", "DetailsInfoLoaderImpl loadData")
        Log.d("TAG1123331", "login $login")
        Log.d("TAG1123331", "name $name")


        call.enqueue(object : Callback<DetailsInfoData> {

            override fun onFailure(call: Call<DetailsInfoData>, t: Throwable) {
                Log.d("TAG1123331", "onFailure: ${t.message}")
            }

            override fun onResponse(call: Call<DetailsInfoData>,
                    response: Response<DetailsInfoData>) {
                _parcelDetailsInfo.value =
                    ParcelDetailsInfo(isSuccess = response.isSuccessful, login = login, name = name,
                        detailsInfoData = response.body())
                Log.d("TAG1123331", "onResponse: ${response.body()}")
            }
        })

    }
}