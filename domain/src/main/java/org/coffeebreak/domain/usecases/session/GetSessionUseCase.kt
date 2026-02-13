package org.coffeebreak.domain.usecases.session

import org.coffeebreak.domain.repository.SessionRepository

class GetSessionUseCase(private val repo: SessionRepository) {
    suspend fun execute() = repo.getSession()
}