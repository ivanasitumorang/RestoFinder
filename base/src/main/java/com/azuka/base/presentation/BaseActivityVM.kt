package com.azuka.base.presentation

import androidx.lifecycle.ViewModel
import com.azuka.base.di.viewmodel.ViewModelFactory
import javax.inject.Inject


/**
 * Created by ivanaazuka on 28/09/20.
 * Android Engineer
 */
 
abstract class BaseActivityVM <T : ViewModel> : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    abstract fun getVM(): T?
}