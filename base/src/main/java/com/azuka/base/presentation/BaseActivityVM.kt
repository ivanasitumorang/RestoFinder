package com.azuka.base.presentation

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.azuka.base.di.viewmodel.ViewModelFactory
import javax.inject.Inject


/**
 * Created by ivanaazuka on 28/09/20.
 * Android Engineer
 */

abstract class BaseActivityVM<T : ViewModel> : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    abstract fun getVM(): T?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObserver(getVM() as? BaseViewModel)
    }

    private fun setupObserver(viewModel: BaseViewModel?) {
        viewModel?.loadingHandler?.observe(this, { loading ->
            if (loading) showLoading()
            else hideLoading()
        })
    }

    protected open fun showLoading() {
        loadingDialog.get()?.show(supportFragmentManager)
    }

    protected open fun hideLoading() {
        loadingDialog.get()?.hide()
    }
}