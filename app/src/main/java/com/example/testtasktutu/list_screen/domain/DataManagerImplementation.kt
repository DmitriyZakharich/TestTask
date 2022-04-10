package com.example.testtasktutu.list_screen.domain

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testtasktutu.MyApp
//import com.example.testtasktutu.list_screen.data.cache.CacheRepositoriesLoader
import com.example.testtasktutu.list_screen.data.network.RetrofitRepositoriesLoader
import com.example.testtasktutu.list_screen.data.data_custom_exceptions.NetworkExceptions
import com.example.testtasktutu.list_screen.data.data_custom_exceptions.NoDataException
import com.example.testtasktutu.list_screen.data.data_custom_exceptions.NoDataInDatabaseException
import com.example.testtasktutu.list_screen.data.database.AppDatabase
import com.example.testtasktutu.list_screen.domain.interfaces.RetrofitRepositoriesLoaderInterface
import com.example.testtasktutu.list_screen.domain.mappers.RepositoryInfoMapper
import com.example.testtasktutu.list_screen.presentation.interfaces.DataManagerInterface

/**Получает запрос на загрузку данных из GetAdapterUseCase
 * Пытается загрузить данные из сети и обновляет данные в кеше
 * Если не получается, то загружает данные из кеша*/

class DataManagerImplementation : DataManagerInterface {

    private val _liveData: MutableLiveData<List<RepositoryInfoDomain>> = MutableLiveData<List<RepositoryInfoDomain>>()
    override val liveData: LiveData<List<RepositoryInfoDomain>> = _liveData

    private val retrofitRepositoriesLoader: RetrofitRepositoriesLoaderInterface =
        RetrofitRepositoriesLoader()
    private val appDatabase = AppDatabase()

    override fun getData(userName: String){
        if (checkForInternet()) {
            Log.d("TAG123321", "getData first userName = $userName")

            retrofitRepositoriesLoader.liveData?.observeForever {

                appDatabase.updateData(userName, RepositoryInfoMapper.modelListDomainToData(userName, it))
                _liveData.value = it
            }

            try {
                retrofitRepositoriesLoader.loadData(userName)
            } catch (e: NetworkExceptions) {
                try {
//                    Log.d("TAG123321", "не загрузилось ")
                    appDatabase.liveData.observeForever{


                        _liveData.value = RepositoryInfoMapper.modelListDataToDomain(it)


                    }

                    appDatabase.loadData(userName)

                } catch (e: NoDataInDatabaseException){
                    throw NoDataException()
                }

            }
        } else
            try {
//                Log.d("TAG123321", "нет интернета")
//                Log.d("TAG123321", userName)
                appDatabase.liveData.observeForever{
                    Log.d("TAG123321", "getData observeForever userName = $userName")
                    _liveData.value = RepositoryInfoMapper.modelListDataToDomain(it)
                    Log.d("TAG123321", "getData last observeForever userName = $userName")

                }
                Log.d("TAG123321", "try userName = $userName")
                appDatabase.loadData(userName)
                Log.d("TAG123321", "after try userName = $userName")
            } catch (e: NoDataInDatabaseException){
                throw NoDataException()
            }
    }

    private fun checkForInternet(): Boolean {
        val context = MyApp.applicationContext()
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION") return networkInfo.isConnected
        }
    }
}