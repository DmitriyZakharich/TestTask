package com.example.domain.usecases

import com.example.domain.models.UserDetails
import com.example.domain.repository.RepositoryManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetDetailsUseCase(private val repositoryManager: RepositoryManager) {

    suspend fun execute(login: String, scope: CoroutineDispatcher = Dispatchers.IO): UserDetails? =
        withContext(scope) {
            return@withContext repositoryManager.getUserDetails(login = login)
        }
}