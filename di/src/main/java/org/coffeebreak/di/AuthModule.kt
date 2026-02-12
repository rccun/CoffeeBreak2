package org.coffeebreak.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.coffeebreak.data.repository.AuthRepositoryImpl
import org.coffeebreak.data.utils.EmailValidatorImpl
import org.coffeebreak.domain.repository.AuthRepository
import org.coffeebreak.domain.repository.SessionRepository
import org.coffeebreak.domain.usecases.auth.EmailUseCase
import org.coffeebreak.domain.usecases.auth.PasswordUseCase
import org.coffeebreak.domain.usecases.auth.SetPasswordUseCase
import org.coffeebreak.domain.usecases.auth.SignInUseCase
import org.coffeebreak.domain.usecases.auth.SignUpUseCase
import org.coffeebreak.domain.usecases.auth.ValidateUseCase
import org.coffeebreak.domain.utils.EmailValidator
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    @Singleton
    fun provideAuthRepository(sessionRepository: SessionRepository): AuthRepository = AuthRepositoryImpl(sessionRepository)

    @Provides
    @Singleton
    fun provideSignInUseCase(repo: AuthRepository) = SignInUseCase(repo)

    @Provides
    @Singleton
    fun provideSignUpUseCase(repo: AuthRepository) = SignUpUseCase(repo)

    @Provides
    @Singleton
    fun provideEmailValidator(): EmailValidator = EmailValidatorImpl()

    @Provides
    @Singleton
    fun provideEmailUseCase(repo: EmailValidator) = EmailUseCase(repo)

    @Provides
    @Singleton
    fun provideValidateUseCase(emailUseCase: EmailUseCase, passwordUseCase: PasswordUseCase) =
        ValidateUseCase(emailUseCase, passwordUseCase)

    @Provides
    @Singleton
    fun providePasswordUseCase() = PasswordUseCase()

    @Provides
    @Singleton
    fun provideSetPasswordUseCase(repo: AuthRepository) = SetPasswordUseCase(repo)
}