package com.example.james_code_challenge.domain.di

import android.content.Context
import androidx.room.Room
import com.example.james_code_challenge.Constants.Companion.PROCEDURES_DATABASE
import com.example.james_code_challenge.data.database.AppDatabase
import com.example.james_code_challenge.data.database.dao.FavouriteItemDao
import com.example.james_code_challenge.data.repository.FavouritesRepository
import com.example.james_code_challenge.data.repository.FavouritesRepositoryImpl
import com.example.james_code_challenge.data.repository.ProcedureRepository
import com.example.james_code_challenge.data.repository.ProcedureRepositoryImpl
import com.example.james_code_challenge.domain.usecase.FavouritesUsecase
import com.example.james_code_challenge.domain.usecase.FavouritesUsecaseImpl
import com.example.james_code_challenge.domain.usecase.ProcedureUsecase
import com.example.james_code_challenge.domain.usecase.ProcedureUsecaseImpl
import com.example.james_code_challenge.services.RetrofitHelper
import com.example.james_code_challenge.services.api.ProcedureApi
import com.example.james_code_challenge.services.api.ProcedureService
import com.example.james_code_challenge.services.interceptors.OfflineInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun providesRetrofit(
        @ApplicationContext context: Context
    ): Retrofit {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(OfflineInterceptor(context))
        return RetrofitHelper.getInstance(builder)
    }

    @Provides
    @Singleton
    fun provideProcedureApi(retrofit: Retrofit): ProcedureService {
        return ProcedureApi(retrofit)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            PROCEDURES_DATABASE
        ).build()
    }

    @Singleton
    @Provides
    fun provideFavouriteItemDao(appDatabase: AppDatabase): FavouriteItemDao {
        return appDatabase.favoriteItemDao()
    }

    @Provides
    @Singleton
    fun provideFavouritesRepository(
        favouriteItemDao: FavouriteItemDao
    ): FavouritesRepository {
        return FavouritesRepositoryImpl(favouriteItemDao)
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

    @Provides
    @Singleton
    fun provideFavouritesUsecase(
        favouriteRepository: FavouritesRepository
    ): FavouritesUsecase {
        return FavouritesUsecaseImpl(favouriteRepository)
    }

}