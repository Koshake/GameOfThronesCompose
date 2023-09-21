package com.koshake.feature_list.di

import androidx.lifecycle.ViewModel
import com.koshake.feature_list.data.api.IListApi
import com.koshake.feature_list.data.api.ListApi
import com.koshake.feature_list.data.repository.HousesListRepositoryImpl
import com.koshake.feature_list.domain.repository.HousesListRepository
import com.koshake.feature_list.ui.HousesListScreenViewModel
import com.koshake.viewmodel_base.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface HousesListModule {

    @Binds
    @Singleton
    fun bindsHousesListRepository(homeRepository: HousesListRepositoryImpl): HousesListRepository

    @Binds
    @Singleton
    fun bindsListApi(listApi: ListApi): IListApi

    @Binds
    @IntoMap
    @ViewModelKey(HousesListScreenViewModel::class)
    fun bindHomeViewModel(impl: HousesListScreenViewModel): ViewModel
}
