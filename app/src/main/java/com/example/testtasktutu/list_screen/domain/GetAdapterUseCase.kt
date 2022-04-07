package com.example.testtasktutu.list_screen.domain

import com.example.testtasktutu.list_screen.presentation.interfaces.DataManagerInterface

class GetAdapterUseCase {

    private var dataManager: DataManagerInterface? = null
    private var repositories: List<RepositoryInfo?>? = null

    private fun setLambda(repositories: List<RepositoryInfo?>?) {
        this.repositories = repositories
    }

    fun start(query: String, f: (CustomRecyclerAdapter) -> Unit) {
        dataManager?.setQuery(query, ::setLambda)


        dataManager?.liveData?.observeForever { data ->
            _adapter.value = CustomRecyclerAdapter(data, clickListener!!)
        }

        dataManager?.getData()

    }
}
