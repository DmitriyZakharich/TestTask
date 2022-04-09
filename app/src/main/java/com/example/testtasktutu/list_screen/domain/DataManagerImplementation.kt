package com.example.testtasktutu.list_screen.domain

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testtasktutu.MyApp
import com.example.testtasktutu.list_screen.data.cache.CacheRepositoriesLoader
import com.example.testtasktutu.list_screen.data.network.RetrofitRepositoriesLoader
import com.example.testtasktutu.list_screen.domain.interfaces.RetrofitRepositoriesLoaderInterface
import com.example.testtasktutu.list_screen.presentation.interfaces.DataManagerInterface


/**Получает запрос на загрузку данных из ViewModel
 * Пытается загрузить данные из сети и обновляет данные в кеше
 * Если не получается, то загружает данные из кеша*/

/**Данные
 * вход: context
 * выход: List<RepositoryInfo>
 * */
class DataManagerImplementation : DataManagerInterface {

    private var _liveData: MutableLiveData<List<RepositoryInfo>>? = null
    override val liveData: LiveData<List<RepositoryInfo>>? = _liveData

    private val retrofitRepositoriesLoader: RetrofitRepositoriesLoaderInterface =
        RetrofitRepositoriesLoader()

    private var query: String? = null


    override fun getData(query: String){
        _liveData = MutableLiveData<List<RepositoryInfo>>()
        if (checkForInternet()) {
            retrofitRepositoriesLoader.liveData?.observeForever {
                if (it != null) {
                    _liveData?.value = it
                } else {                                           //null - из интернета ничего не загрузилось
                    CacheRepositoriesLoader(query)
                }
            }

            retrofitRepositoriesLoader.loadData(query)

        } else CacheRepositoriesLoader(query)
    }


    private fun checkForInternet(): Boolean {
        val context = MyApp().getAppContext()
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