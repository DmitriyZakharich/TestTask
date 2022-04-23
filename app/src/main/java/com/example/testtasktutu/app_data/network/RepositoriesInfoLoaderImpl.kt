package com.example.testtasktutu.app_data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testtasktutu.app_data.models.ParcelDetailsInfo
import com.example.testtasktutu.app_data.models.ParcelRepositoriesInfo
import com.example.testtasktutu.app_data.models.RepositoriesInfoData
import com.example.testtasktutu.app_data.models.RepositoryBriefInfoData
import com.example.testtasktutu.list_screen.domain.interfaces.RepositoriesInfoLoader
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoriesInfoLoaderImpl : RepositoriesInfoLoader {

    private val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    private val _parcelDetailsInfo = MutableLiveData<ParcelDetailsInfo>()
    override val parcelDetailsInfo: LiveData<ParcelDetailsInfo> = _parcelDetailsInfo

    private val _parcelRepositoryInfo = MutableLiveData<ParcelRepositoriesInfo>()
    override val parcelRepositoryInfo: LiveData<ParcelRepositoriesInfo> = _parcelRepositoryInfo

    override fun loadRepositoriesList(query: String) {
        val requestApiRepositories = retrofit.create(RequestApiRepositories::class.java)
        val call = requestApiRepositories.getRequest(query)

        call.enqueue(object : Callback<List<RepositoryBriefInfoData>> {
            override fun onFailure(call: Call<List<RepositoryBriefInfoData>>, t: Throwable) {}

            override fun onResponse(call: Call<List<RepositoryBriefInfoData>>,
                    response: Response<List<RepositoryBriefInfoData>>) {
                _parcelRepositoryInfo.value =
                    ParcelRepositoriesInfo(isSuccess = response.isSuccessful, query,
                        response.body())
            }
        })
    }

    override fun loadRepositoryInfo(login: String, name: String) {
        val requestApiRepositories = retrofit.create(RequestApiRepositoryDetailsInfo::class.java)
        val call = requestApiRepositories.getRequest(login, name)

        call.enqueue(object : Callback<RepositoriesInfoData> {
            override fun onFailure(call: Call<RepositoriesInfoData>, t: Throwable) {}

            override fun onResponse(call: Call<RepositoriesInfoData>,
                    response: Response<RepositoriesInfoData>) {
                _parcelDetailsInfo.value =
                    ParcelDetailsInfo(isSuccess = response.isSuccessful, login = login, name = name,
                        detailsInfoData = response.body())
            }
        })
    }
}