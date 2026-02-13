package org.coffeebreak.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.coffeebreak.data.repository.UserRepositoryImpl
import org.coffeebreak.domain.repository.SessionRepository
import org.coffeebreak.domain.repository.UserRepository
import org.coffeebreak.domain.usecases.user.GetUserUseCase
import org.coffeebreak.domain.usecases.user.SetAddressUseCase
import org.coffeebreak.domain.usecases.user.UpdateUserUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {
    @Provides
    @Singleton
    fun provideUserRepository(sessionRepository: SessionRepository): UserRepository = UserRepositoryImpl(sessionRepository)
    @Provides
    @Singleton
    fun provideSetAddressUseCase(repo: UserRepository) = SetAddressUseCase(repo)
    @Provides
    @Singleton
    fun provideGetUserUseCase(repo: UserRepository) = GetUserUseCase(repo)
    @Provides
    @Singleton
    fun provideUpdateUserUseCase(repo: UserRepository) = UpdateUserUseCase(repo)
}