package com.example.testtasktutu.list_screen.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testtasktutu.list_screen.data.data_custom_exceptions.NoDataException
import com.example.testtasktutu.list_screen.domain.domain_custom_exceptions.NoAdapterException
import com.example.testtasktutu.list_screen.presentation.interfaces.DataManagerInterface

class GetAdapterUseCase {
    private var dataManager: DataManagerInterface = DataManagerImplementation()

    //    private var lambdaItemClick: ((RepositoryInfo) -> Unit)? = null
    private var _adapter: MutableLiveData<CustomRecyclerAdapter> = MutableLiveData()
    var adapter: LiveData<CustomRecyclerAdapter> = _adapter

    /**1) Принять данные
     * 2) Сделать запрос в дату
     * 3) Сделать запрос адаптера
     * 4) Передать адаптер*/


    //    private fun setLambda(repositories: List<RepositoryInfo?>?) {
    //        if (!repositories.isNullOrEmpty() && repositories.filterNotNull().isNotEmpty())
    //
    //            lambdaItemClick?.let {
    //                CustomRecyclerAdapter(
    //                    repositories.filterNotNull(), it
    //                )
    //            }
    //    }

    fun start(query: String, lambdaItemClick: (RepositoryInfoDomain) -> Unit) {


        //        this.lambdaItemClick = lambdaItemClick


        dataManager.liveData.observeForever {
            _adapter.value = CustomRecyclerAdapter(it, lambdaItemClick)
        }

        try {
            dataManager.getData(query)
        } catch (e: NoDataException) {
            throw NoAdapterException()
        } //        this.callbackAdapter = callbackAdapter


        //        dataManager?.liveData?.observeForever { data ->
        //            _adapter.value = CustomRecyclerAdapter(data, clickListener!!)
        //        }
        //
        //        dataManager?.getData()

    }
}
