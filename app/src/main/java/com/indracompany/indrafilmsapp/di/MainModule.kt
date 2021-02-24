package com.indracompany.indrafilmsapp.di

import com.indracompany.indrafilmsapp.data.api.MyApi
import com.indracompany.indrafilmsapp.data.api.repository.MovieRepository
import com.indracompany.indrafilmsapp.data.api.repository.UserRepository
import com.indracompany.indrafilmsapp.ui.details.DetailsViewModel
import com.indracompany.indrafilmsapp.ui.login.LoginViewModel
import com.indracompany.indrafilmsapp.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    single {
        MyApi()
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
            application = get(),
            UserRepository(
                api = get()
            )
        )
    }

    viewModel {
        DetailsViewModel()
    }

}