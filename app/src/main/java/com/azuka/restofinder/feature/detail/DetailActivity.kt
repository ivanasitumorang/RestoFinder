package com.azuka.restofinder.feature.detail

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.azuka.base.di.component.Component
import com.azuka.base.presentation.BaseActivityVM
import com.azuka.restofinder.R
import com.azuka.restofinder.appComponent
import com.azuka.restofinder.domain.model.Restaurant
import com.azuka.restofinder.feature.HomeViewModel
import com.azuka.restofinder.feature.detail.di.DaggerDetailComponent
import com.azuka.restofinder.feature.detail.di.DetailComponent
import com.azuka.restofinder.utils.AppConstant

class DetailActivity : BaseActivityVM<HomeViewModel>() {

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    private val restaurant by lazy {
        intent.extras?.get(AppConstant.Home.TAG_RESTAURANT) as? Restaurant
    }

    private lateinit var component: DetailComponent

    override fun getLayoutId(): Int = R.layout.activity_detail

    override fun initDependencyInjection() {
        component.inject(this)
    }

    override fun onActivityReady(savedInstanceState: Bundle?) {
        restaurant?.let {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
        }
    }

    override fun createComponent(): Component {
        component = DaggerDetailComponent.builder()
            .appComponent(appComponent())
            .build()
        return component
    }

    override fun getVM(): HomeViewModel? = viewModel
}