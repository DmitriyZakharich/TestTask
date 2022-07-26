package com.example.testtasktutu.app_repository

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.testtasktutu.MyApp
import com.example.testtasktutu.R
import com.example.testtasktutu.app_repository.managers_interfaces.AppDatabase
import com.example.testtasktutu.app_repository.managers_interfaces.GithubReposInfoLoader
import com.example.testtasktutu.app_repository.models.ParcelGitHubReposInfo
import com.example.testtasktutu.app_repository.models.GithubRepoBriefInfoData
import com.example.testtasktutu.app_repository.models.GithubRepoInfoData
import com.example.testtasktutu.app_repository.models.ParcelDetailsInfo
import com.example.testtasktutu.screens.common_interfaces.RepositoryManager
import com.example.testtasktutu.screens.details_screen.domain.mappers.RepositoriesInfoMapper
import com.example.testtasktutu.screens.details_screen.domain.model.GithubDetailRepoInfoDomain
import com.example.testtasktutu.screens.list_screen.domain.mappers.InfoFromBriefToFull
import com.example.testtasktutu.screens.list_screen.domain.mappers.GithubRepoInfoMapper
import com.example.testtasktutu.screens.list_screen.domain.models.ParcelGithubRepoBriefInfo
import com.example.testtasktutu.utils.checkForInternet

class RepositoryManagerImpl(private val githubReposInfoLoader: GithubReposInfoLoader,
        private val appDatabase: AppDatabase) : RepositoryManager {

    //List Repos
    private val _listRepos = MutableLiveData<ParcelGithubRepoBriefInfo>()
    override val listRepos: LiveData<ParcelGithubRepoBriefInfo> = _listRepos

    //Details Repo
    private val _detailRepo = MutableLiveData<GithubDetailRepoInfoDomain>()
    override val detailRepo: LiveData<GithubDetailRepoInfoDomain> = _detailRepo

    init {
        githubReposInfoLoader.parcelRepositoryInfo.observeForever(observerNetwork())
        appDatabase.repositoriesList.observeForever(observerDatabase())

        githubReposInfoLoader.parcelDetailsInfo.observeForever(observerDetailsRepoInfoNetwork())
        appDatabase.repositoryInfo.observeForever(observerDetailsRepoInfoDB())
    }

    //Callback for livedata
    private fun observerNetwork() = Observer<ParcelGitHubReposInfo> { parcel ->
        if (parcel.isSuccess && !parcel.listRepositoriesInfo.isNullOrEmpty()) {
            parcel.listRepositoriesInfo.forEach { list ->
                list?.login = parcel.login
            }

            _listRepos.value = ParcelGithubRepoBriefInfo(parcel.isSuccess,
                GithubRepoInfoMapper.modelListDataToDomain(
                    parcel.listRepositoriesInfo as List<GithubRepoBriefInfoData>))

            appDatabase.updateData(login = parcel.login,
                listInsert = InfoFromBriefToFull.convert(parcel.listRepositoriesInfo))

        } else {
            appDatabase.loadGithubRepositoriesList(parcel.login)
        }
    }

    //Callback for livedata
    private fun observerDatabase() = Observer<List<GithubRepoBriefInfoData>?> { repositoriesList ->
        if (!repositoriesList.isNullOrEmpty()) {
            _listRepos.value = ParcelGithubRepoBriefInfo(true,
                GithubRepoInfoMapper.modelListDataToDomain(repositoriesList))

        } else Toast.makeText(MyApp.applicationContext(),
            MyApp.applicationContext().getString(R.string.no_data_available), Toast.LENGTH_LONG)
            .show()
    }


    //Callback Details RepoInfo loader
    private fun observerDetailsRepoInfoNetwork() = Observer<ParcelDetailsInfo> {
        if (it.isSuccess && it.detailsInfoData != null) {
            _detailRepo.value = RepositoriesInfoMapper.modelRepositoriesInfoToDomain(it.detailsInfoData)

            it.detailsInfoData.login = it.login
            appDatabase.updateData(detailsInfoData = it.detailsInfoData)

        } else {
            appDatabase.loadRepositoryInfo(login = it.login, name = it.name)
        }
    }
    //Callback Details RepoInfo loader
    private fun observerDetailsRepoInfoDB() = Observer<GithubRepoInfoData?> {
        _detailRepo.value = RepositoriesInfoMapper.modelRepositoriesInfoToDomain(it)
    }


    override fun getListData(login: String) {
        if (checkForInternet()) {
            githubReposInfoLoader.loadGithubReposList(login)
        } else {
            appDatabase.loadGithubRepositoriesList(login)
        }
    }

    override fun getDetailsData(login: String, name: String) {
        if (checkForInternet()) {
            githubReposInfoLoader.loadGithubRepoInfo(login = login, name = name)
        } else {
            appDatabase.loadRepositoryInfo(login = login, name = name)
        }
    }
}