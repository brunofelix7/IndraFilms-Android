package com.indracompany.indrafilmsapp.di

import com.indracompany.indrafilmsapp.data.api.interceptor.AuthInterceptor
import com.indracompany.indrafilmsapp.data.api.repository.MovieRepository
import com.indracompany.indrafilmsapp.data.api.repository.UserRepository
import com.indracompany.indrafilmsapp.data.db.AppDatabase
import com.indracompany.indrafilmsapp.data.db.repository.UserDbRepository
import com.indracompany.indrafilmsapp.session.SessionManager
import com.indracompany.indrafilmsapp.viewmodel.LoginViewModel
import com.indracompany.indrafilmsapp.viewmodel.MainViewModel
import com.indracompany.indrafilmsapp.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    //  App database provider
    single { AppDatabase.getDatabase(context = get()) }

    //  UserDao provider
    fun provideUserDao(db: AppDatabase) = db.userDao()

    //  SessionManager provider
    single { SessionManager(context = get()) }

    //  AuthInterceptor provider
    single { AuthInterceptor() }

    //  SplashViewModel provider
    viewModel {
        SplashViewModel(
            repositoryDb = UserDbRepository(
                dao = provideUserDao(db = get())
            )
        )
    }

    //  MainViewModel provider
    viewModel {
        MainViewModel(
            repository = MovieRepository()
        )
    }

    //  LoginViewModel provider
    viewModel {
        LoginViewModel(
            repository = UserRepository(),
            repositoryDb = UserDbRepository(
                dao = provideUserDao(db = get())
            )
        )
    }
}
