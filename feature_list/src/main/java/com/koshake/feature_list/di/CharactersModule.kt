package com.koshake.feature_list.di

import com.koshake.feature_list.data.repository.CharactersListRepositoryImpl
import com.koshake.feature_list.domain.repository.CharactersListRepository
import com.koshake.feature_list.ui.characters.CharactersViewModel
import com.koshake.viewmodel_base.viewmodel.ViewModelFactoryKey
import com.koshake.viewmodel_base.viewmodel.ViewModelQualifier
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface CharactersModule {

    @Binds
    @IntoMap
    @ViewModelQualifier
    @ViewModelFactoryKey(CharactersViewModel.Factory::class)
    fun bindCharactersViewModelFactory(factory: CharactersViewModel.Factory): Any

    @Binds
    @Singleton
    fun bindCharactersRepository(impl: CharactersListRepositoryImpl): CharactersListRepository
}
