package com.example.testtasktutu.list_screen.viewmodel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtasktutu.MyApp
import com.example.testtasktutu.list_screen.domain.CustomRecyclerAdapter
import com.example.testtasktutu.list_screen.domain.GetAdapterUseCase
import com.example.testtasktutu.list_screen.domain.RepositoryInfo
import com.example.testtasktutu.list_screen.domain.domain_custom_exceptions.NoAdapterException
import com.example.testtasktutu.list_screen.presentation.interfaces.DataManagerInterface
import com.example.testtasktutu.list_screen.presentation.interfaces.ListViewModelInterface

/**Запрос данных из DataManager*/
class ListFragmentViewModel : ViewModel(), ListViewModelInterface {

    private var _adapter = MutableLiveData<CustomRecyclerAdapter>()
    override val adapterliveData: LiveData<CustomRecyclerAdapter> = _adapter

//    private var dataManager: DataManagerInterface? = null
    private var getAdapterUseCase = GetAdapterUseCase()
//    private var clickListener: ((RepositoryInfo) -> Unit)? = null


//    override fun setDataManager(dataManager: DataManagerInterface) {
//        this.dataManager = dataManager
//    }
    //Listener for item RecyclerView
//    override fun setLambdaItemOnClick(lambdaItemClick: (RepositoryInfo) -> Unit)   {
//         this.clickListener = lambdaItemClick
//    }

//    private fun setAdapter(customRecyclerAdapter: CustomRecyclerAdapter) {
//        _adapter.value = customRecyclerAdapter
//    }


    override fun getAdapter(query: String, lambdaItemClick: (RepositoryInfo) -> Unit) {
        getAdapterUseCase.adapter.observeForever{
            _adapter.value = it
        }
        try {
            getAdapterUseCase.start(query, lambdaItemClick)
        }catch (e: NoAdapterException){
            Toast.makeText(MyApp.applicationContext(), "Ошибка при получении данных", Toast.LENGTH_LONG).show()
        }

//        getAdapterUseCase?.start(query){it ->
//            _adapter.value = it
//        }


//        dataManager?.setQuery(query)
//        dataManager?.liveData?.observeForever { data ->
//            _adapter.value = CustomRecyclerAdapter(data, clickListener!!)
//        }
//
//        dataManager?.getData()

}

}