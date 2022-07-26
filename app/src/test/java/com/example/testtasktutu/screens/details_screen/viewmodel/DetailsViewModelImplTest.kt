package com.example.testtasktutu.screens.details_screen.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.testtasktutu.screens.details_screen.domain.GetDataUseCaseImpl
import com.example.testtasktutu.screens.details_screen.domain.model.GithubDetailRepoInfoDomain
import org.junit.Assert
import org.junit.Rule
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock

@RunWith(MockitoJUnitRunner::class)
internal class DetailsViewModelImplTest {

    private val getDataUseCase = mock<GetDataUseCaseImpl>()
    private val viewModel = DetailsViewModelImpl(getDataUseCase)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule() //For LiveData

    @Test
    fun getDataTest() {
        val githubDetailRepoInfoDomain =
            GithubDetailRepoInfoDomain(login = "1", name = "1", description = "1", language = "1",
                stargazers_count = 1, updated_at = "1")



        val newLD = MutableLiveData(GithubDetailRepoInfoDomain(login = "1", name = "1", description = "1", language = "1",
            stargazers_count = 1, updated_at = "1"))
        Mockito.`when`(getDataUseCase.info).thenReturn(newLD)


        val newLDGet = getDataUseCase.info
        val newValue = newLDGet.value



        viewModel.info.observeForever {}
        viewModel.getData("", "")

        try {
            val value = viewModel.info.value
            Assert.assertEquals(value, newValue)
        } finally { //            vm.lv.removeObserver(observer)
        }


        //        getDataUseCase.info.observeForever() {
        //            val actual = it
        //            val expected =
        //                RepositoriesInfoDomain(login = "1", name = "1", description = "1", language = "1",
        //                    stargazers_count = 1, updated_at = "1")
        //
        //            Assertions.assertEquals(actual, expected)
        //        }


//        val actual = viewModel.info.value
//        val expected1 = true
//
//        val expected =
//            RepositoriesInfoDomain(login = "1", name = "1", description = "1", language = "1",
//                stargazers_count = 1, updated_at = "1")
//
//        Assertions.assertEquals(actual, expected1)

    }
}


