package com.example.james_code_challenge.domain.di

import com.example.james_code_challenge.data.repository.ProcedureRepository
import com.example.james_code_challenge.data.repository.ProcedureRepositoryImpl
import com.example.james_code_challenge.domain.usecase.ProcedureUsecase
import com.example.james_code_challenge.domain.usecase.ProcedureUsecaseImpl
import com.example.james_code_challenge.services.api.ProcedureApi
import com.example.james_code_challenge.services.api.ProcedureService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideProcedureApi(): ProcedureService {
        return ProcedureApi()
    }

    @Provides
    @Singleton
    fun provideCharacterRepository(
        procedureApi: ProcedureApi
    ): ProcedureRepository {
        return ProcedureRepositoryImpl(procedureApi)
    }

    @Provides
    @Singleton
    fun provideProcedureUsecase(
        procedureRepository: ProcedureRepository
    ): ProcedureUsecase {
        return ProcedureUsecaseImpl(procedureRepository)
    }

}