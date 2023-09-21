package com.koshake.feature_home.di

import androidx.lifecycle.ViewModel
import com.koshake.feature_home.data.api.HomeApi
import com.koshake.feature_home.data.api.IHomeApi
import com.koshake.feature_home.data.repository.HomeRepositoryImpl
import com.koshake.feature_home.domain.HomeRepository
import com.koshake.feature_home.ui.HomeViewModel
import com.koshake.viewmodel_base.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface HomeModule {

    @Binds
    @Singleton
    fun bindsHomeRepository(homeRepository: HomeRepositoryImpl): HomeRepository

    @Binds
    @Singleton
    fun bindsHomeApi(homeApi: HomeApi): IHomeApi

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(impl: HomeViewModel): ViewModel

}
