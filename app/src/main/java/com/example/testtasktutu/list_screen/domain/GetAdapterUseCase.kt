package com.example.testtasktutu.list_screen.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testtasktutu.list_screen.data.data_custom_exceptions.NoDataException
import com.example.testtasktutu.list_screen.data.models.RepositoryInfoData
import com.example.testtasktutu.list_screen.domain.domain_custom_exceptions.NoAdapterException
import com.example.testtasktutu.list_screen.domain.mappers.RepositoryInfoMapper
import com.example.testtasktutu.list_screen.presentation.interfaces.DataManager

class GetAdapterUseCase(private val dataManager: DataManager) {
    private var lambdaItemClick: ((RepositoryInfoDomain) -> Unit)? = null
    private var _adapter: MutableLiveData<CustomRecyclerAdapter> = MutableLiveData()
    var adapter: LiveData<CustomRecyclerAdapter> = _adapter



    private fun callbackList(list: List<RepositoryInfoDomain>){
        Log.d("TAG123321", "GetAdapterUseCase callbackList")
        _adapter.value = lambdaItemClick?.let { CustomRecyclerAdapter(list, it) }

    }

    fun start(query: String, lambdaItemClick: (RepositoryInfoDomain) -> Unit) {
        Log.d("TAG123321", "GetAdapterUseCase start")
        this.lambdaItemClick = lambdaItemClick

        try {
            dataManager.getData(query, ::callbackList)
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
