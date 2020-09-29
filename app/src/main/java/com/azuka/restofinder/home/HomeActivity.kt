package com.azuka.restofinder.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.azuka.base.di.component.Component
import com.azuka.base.di.viewmodel.ViewModelFactory
import com.azuka.base.presentation.BaseActivity
import com.azuka.base.presentation.BaseActivityVM
import com.azuka.restaurantfinder.data.Resource
import com.azuka.restofinder.R
import com.azuka.restofinder.appComponent
import javax.inject.Inject

class HomeActivity : BaseActivityVM<HomeViewModel>() {

//    @Inject
//    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    private lateinit var component: HomeComponent

    override fun getLayoutId() = R.layout.activity_home

    override fun initDependencyInjection() {
        component.inject(this)
    }

    override fun onActivityReady(savedInstanceState: Bundle?) {
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.restaurants.observe(this, Observer { restaurants ->
            if (restaurants != null) {
                when(restaurants) {
                    is Resource.Loading -> {
                        Log.i("Hasil", "loading")
                    }
                    is Resource.Success -> {
                        Log.i("Hasil", "success ${restaurants.data?.get(0)}")
                    }
                    is Resource.Error -> {
                        Log.i("Hasil", "error")
                    }
                }
            }
        })
    }

    override fun createComponent(): Component {
        component = DaggerHomeComponent.builder()
            .appComponent(appComponent())
            .homeModule(HomeModule())
            .build()
        return component
    }

    override fun getVM(): HomeViewModel? = viewModel
}