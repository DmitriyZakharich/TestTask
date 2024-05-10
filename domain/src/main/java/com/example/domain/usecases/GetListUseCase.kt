package com.example.domain.usecases

import com.example.domain.models.UserShort
import com.example.domain.repository.RepositoryManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetListUseCase(private val repositoryManager: RepositoryManager) {

     suspend fun execute(scope: CoroutineDispatcher): List<UserShort> =
         withContext(scope){
             return@withContext repositoryManager.getUsersList()
         }
}