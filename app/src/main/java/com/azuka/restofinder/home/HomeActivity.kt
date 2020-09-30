package com.azuka.restofinder.home

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.azuka.base.di.component.Component
import com.azuka.base.presentation.BaseActivityVM
import com.azuka.restofinder.R
import com.azuka.restofinder.appComponent
import com.azuka.restofinder.home.di.DaggerHomeComponent
import com.azuka.restofinder.home.di.HomeComponent
import com.azuka.restofinder.home.di.HomeModule
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivityVM<HomeViewModel>() {

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    private val adapter: ItemListAdapter by lazy {
        ItemListAdapter()
    }

    private lateinit var component: HomeComponent

    override fun getLayoutId() = R.layout.activity_home

    override fun initDependencyInjection() {
        component.inject(this)
    }

    override fun onActivityReady(savedInstanceState: Bundle?) {
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        rvItemList.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.searchResult.observe(this, Observer { restaurants ->
            if (restaurants != null) {
                adapter.submitList(restaurants)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        val searchView = menu?.findItem(R.id.menuSearch)?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (!p0.isNullOrEmpty()) {
                    viewModel.searchRestaurant(p0)
                    return true
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean = false

        })
        return super.onCreateOptionsMenu(menu)
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