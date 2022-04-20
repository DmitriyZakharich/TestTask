package com.example.testtasktutu.details_screen.data.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.testtasktutu.MyApp
import com.example.testtasktutu.details_screen.data.models.DetailsInfoData
import com.example.testtasktutu.list_screen.data.database.RepositoriesDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsDatabase {

    private val db =
        Room.databaseBuilder(MyApp.applicationContext(), RepositoriesDatabase::class.java,
            "RepositoryInfoData").fallbackToDestructiveMigration().build()
    private val detailsInfo = db.getRepositoryInfoDao()

    private val _livedata = MutableLiveData<DetailsInfoData?>()
    val livedata: LiveData<DetailsInfoData?> = _livedata

    fun loadData(login: String, name: String) {
        CoroutineScope(Dispatchers.IO).launch {
//            _livedata.postValue(detailsInfo.getUser(login))

        }
    }

    fun updateData(login: String, detailsInfoData: DetailsInfoData) {

    }

}