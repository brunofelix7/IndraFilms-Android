package com.indracompany.indrafilmsapp.di

import com.indracompany.indrafilmsapp.data.api.ApiService
import com.indracompany.indrafilmsapp.data.api.repository.MovieRepository
import com.indracompany.indrafilmsapp.data.api.repository.UserRepository
import com.indracompany.indrafilmsapp.viewmodel.LoginViewModel
import com.indracompany.indrafilmsapp.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    single {
        ApiService(
            context = get()
        )
    }

    viewModel {
        MainViewModel(
            MovieRepository(
                api = get()
            )
        )
    }

    viewModel {
        LoginViewModel(
            UserRepository(
                api = get()
            )
        )
    }

}