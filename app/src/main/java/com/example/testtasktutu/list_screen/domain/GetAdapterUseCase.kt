package com.example.testtasktutu.list_screen.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testtasktutu.list_screen.presentation.interfaces.DataManagerInterface

class GetAdapterUseCase {

    private var dataManager: DataManagerInterface? = null
    private var lambdaItemClick: ((RepositoryInfo) -> Unit)? = null

    private var _adapter: MutableLiveData<CustomRecyclerAdapter>? = null
    var adapter: LiveData<CustomRecyclerAdapter>? = _adapter
//    private var callbackAdapter: ((CustomRecyclerAdapter) -> Unit)? = null

    private fun setLambda(repositories: List<RepositoryInfo?>?) {

        if (!repositories.isNullOrEmpty() && repositories.filterNotNull().isNotEmpty())

            lambdaItemClick?.let {
                CustomRecyclerAdapter(
                    repositories.filterNotNull(), it
                )
            }

    }


    fun start(
        query: String,
        lambdaItemClick: (RepositoryInfo) -> Unit
    ) {
        dataManager?.liveData?.observeForever{
            _adapter?.value = CustomRecyclerAdapter(it, lambdaItemClick)
        }

        dataManager?.setQuery(query)
//        this.callbackAdapter = callbackAdapter


//        dataManager?.liveData?.observeForever { data ->
//            _adapter.value = CustomRecyclerAdapter(data, clickListener!!)
//        }
//
//        dataManager?.getData()

    }
}
