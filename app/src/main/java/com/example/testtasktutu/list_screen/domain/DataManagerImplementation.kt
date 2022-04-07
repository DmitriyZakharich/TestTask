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
class DataManagerImplementation(
//    private val context: Context,
//    private val retrofitRepositoriesLoader: RetrofitRepositoriesLoaderInterface
) : DataManagerInterface {

    private var _liveData: MutableLiveData<List<RepositoryInfo>>? = null /*by lazy { getData() }*/
    override val liveData: LiveData<List<RepositoryInfo>>? = _liveData

    private val retrofitRepositoriesLoader: RetrofitRepositoriesLoaderInterface = RetrofitRepositoriesLoader()

    private var callbackData: ((repositories: List<RepositoryInfo?>?) -> Unit)? = null
    private var query: String? = null

    override fun setQuery(query: String, callbackList: (repositories: List<RepositoryInfo?>?) -> Unit) {
        this.query = query
        this.callbackData = callbackList
        getData()
    }

    private fun getData()/*: MutableLiveData<List<RepositoryInfo>>?*/ {

        var localData: MutableLiveData<List<RepositoryInfo>>? = MutableLiveData()

        if (checkForInternet()) {
            val data = retrofitRepositoriesLoader.liveData?.observeForever{

                if (it != null) {
                    localData?.value = it
                } else {                                           //null - из интернета ничего не загрузилось
                    CacheRepositoriesLoader(query!!)
                }
            }

            val loader = retrofitRepositoriesLoader.loadData(query!!)


        } else {
            CacheRepositoriesLoader(query!!)
        }
//        return localData
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
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }
}