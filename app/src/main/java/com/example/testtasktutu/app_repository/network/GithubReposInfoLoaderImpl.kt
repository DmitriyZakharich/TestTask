package com.example.testtasktutu.app_repository.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testtasktutu.app_repository.managers_interfaces.GithubReposInfoLoader
import com.example.testtasktutu.app_repository.models.ParcelDetailsInfo
import com.example.testtasktutu.app_repository.models.ParcelGitHubReposInfo
import com.example.testtasktutu.app_repository.models.GithubRepoInfoData
import com.example.testtasktutu.app_repository.models.GithubRepoBriefInfoData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Network loader
 * */
class GithubReposInfoLoaderImpl : GithubReposInfoLoader {

    private val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    private val _parcelDetailsInfo = MutableLiveData<ParcelDetailsInfo>()
    override val parcelDetailsInfo: LiveData<ParcelDetailsInfo> = _parcelDetailsInfo

    private val _parcelRepositoryInfo = MutableLiveData<ParcelGitHubReposInfo>()
    override val parcelRepositoryInfo: LiveData<ParcelGitHubReposInfo> = _parcelRepositoryInfo

    override fun loadGithubReposList(query: String) {
        val requestApiGithubRepos = retrofit.create(RequestApiGithubRepos::class.java)
        val call = requestApiGithubRepos.getRequest(query)

        call.enqueue(object : Callback<List<GithubRepoBriefInfoData>> {
            override fun onFailure(call: Call<List<GithubRepoBriefInfoData>>, t: Throwable) {}

            override fun onResponse(call: Call<List<GithubRepoBriefInfoData>>,
                    response: Response<List<GithubRepoBriefInfoData>>) {
                _parcelRepositoryInfo.value =
                    ParcelGitHubReposInfo(isSuccess = response.isSuccessful, query,
                        response.body())
            }
        })
    }

    override fun loadGithubRepoInfo(login: String, name: String) {
        val requestApiRepositories = retrofit.create(RequestApiGithubRepoDetailsInfo::class.java)
        val call = requestApiRepositories.getRequest(login, name)

        call.enqueue(object : Callback<GithubRepoInfoData> {
            override fun onFailure(call: Call<GithubRepoInfoData>, t: Throwable) {}

            override fun onResponse(call: Call<GithubRepoInfoData>,
                    response: Response<GithubRepoInfoData>) {
                _parcelDetailsInfo.value =
                    ParcelDetailsInfo(isSuccess = response.isSuccessful, login = login, name = name,
                        detailsInfoData = response.body())
            }
        })
    }
}