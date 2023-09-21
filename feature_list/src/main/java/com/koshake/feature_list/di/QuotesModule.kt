package com.koshake.feature_list.di

import com.koshake.feature_list.data.repository.QuotesListRepositoryImpl
import com.koshake.feature_list.domain.repository.QuotesListRepository
import com.koshake.feature_list.ui.quotes.QuotesListViewModel
import com.koshake.viewmodel_base.viewmodel.ViewModelFactoryKey
import com.koshake.viewmodel_base.viewmodel.ViewModelQualifier
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface QuotesModule {

    @Binds
    @IntoMap
    @ViewModelQualifier
    @ViewModelFactoryKey(QuotesListViewModel.Factory::class)
    fun bindQuotesListViewModelFactory(factory: QuotesListViewModel.Factory): Any

    @Binds
    @Singleton
    fun bindQuotesRepository(impl: QuotesListRepositoryImpl): QuotesListRepository
}
