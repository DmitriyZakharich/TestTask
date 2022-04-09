package com.example.testtasktutu.list_screen.domain

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testtasktutu.MyApp
//import com.example.testtasktutu.list_screen.data.cache.CacheRepositoriesLoader
import com.example.testtasktutu.list_screen.data.network.RetrofitRepositoriesLoader
import com.example.testtasktutu.list_screen.data.data_custom_exceptions.NetworkExceptions
import com.example.testtasktutu.list_screen.data.data_custom_exceptions.NoDataException
import com.example.testtasktutu.list_screen.data.data_custom_exceptions.NoDataInDatabaseException
import com.example.testtasktutu.list_screen.domain.interfaces.RetrofitRepositoriesLoaderInterface
import com.example.testtasktutu.list_screen.presentation.interfaces.DataManagerInterface


/**Получает запрос на загрузку данных из GetAdapterUseCase
 * Пытается загрузить данные из сети и обновляет данные в кеше
 * Если не получается, то загружает данные из кеша*/

/**Данные
 * вход: context
 * выход: List<RepositoryInfo>
 * */
class DataManagerImplementation : DataManagerInterface {

    private var _liveData: MutableLiveData<List<RepositoryInfo>> = MutableLiveData<List<RepositoryInfo>>()
    override val liveData: LiveData<List<RepositoryInfo>> = _liveData

    private val retrofitRepositoriesLoader: RetrofitRepositoriesLoaderInterface =
        RetrofitRepositoriesLoader()

    override fun getData(query: String){
        if (checkForInternet()) {
            retrofitRepositoriesLoader.liveData?.observeForever {
                    _liveData.value = it
            }

            try {
                retrofitRepositoriesLoader.loadData(query)
            } catch (e: NetworkExceptions) {
                try {
//                    CacheRepositoriesLoader(query)
                } catch (e: NoDataInDatabaseException){
                    throw NoDataException()
                }

            }

        } else
            try {
//                CacheRepositoriesLoader(query)
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