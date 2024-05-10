package com.example.domain.usecases

import com.example.domain.models.UserDetails
import com.example.domain.repository.RepositoryManager

class GetDetailsUseCase(private val repositoryManager: RepositoryManager) {

    suspend fun execute(login: String): UserDetails {
        return repositoryManager.getUserDetails(login = login)
    }
}