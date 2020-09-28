package com.azuka.base.di.module

import androidx.lifecycle.ViewModel
import com.azuka.base.di.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class ViewModelModule {
    @Provides
    fun viewModelFactory(
        providerMap: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
    ): ViewModelFactory {
        return ViewModelFactory(providerMap)
    }
}
