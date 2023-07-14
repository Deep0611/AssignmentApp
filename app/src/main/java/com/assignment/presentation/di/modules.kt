package com.assignment.presentation.di

import androidx.room.Room
import com.assignment.data.interactor.LocalUseCase
import com.assignment.data.interactor.RemoteUseCase
import com.assignment.data.interactor.SaveLocalUseCase
import com.assignment.data.repository.DataRepository
import com.assignment.data.repository.LocalDataSource
import com.assignment.data.repository.RemoteDataSource
import com.assignment.presentation.network.ApiInterface
import com.assignment.presentation.room.AppDatabase
import com.assignment.presentation.utils.Constants
import com.assignment.presentation.viewmodel.DataViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single<ApiInterface> {
        Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "assignment_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }


    factory { LocalDataSource(get()) }
    factory { RemoteDataSource(get()) }

    factory { DataRepository(get(), get()) }

    factory { LocalUseCase(get()) }
    factory { RemoteUseCase(get()) }
    factory { SaveLocalUseCase(get()) }

    viewModel { DataViewModel(get(), get(), get()) }
}

