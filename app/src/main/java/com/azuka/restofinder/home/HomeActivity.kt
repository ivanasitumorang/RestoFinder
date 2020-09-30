package com.azuka.restofinder.home

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.azuka.base.di.component.Component
import com.azuka.base.presentation.BaseActivityVM
import com.azuka.base.presentation.widget.LoadingDialog
import com.azuka.restofinder.R
import com.azuka.restofinder.appComponent
import com.azuka.restofinder.home.di.DaggerHomeComponent
import com.azuka.restofinder.home.di.HomeComponent
import com.azuka.restofinder.home.di.HomeModule
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : BaseActivityVM<HomeViewModel>() {

    @Inject
    lateinit var loadingDialog: LoadingDialog

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    private val adapter: ItemListAdapter by lazy {
        ItemListAdapter()
    }

    private lateinit var component: HomeComponent

    private lateinit var searchView: SearchView

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
        searchView = menu?.findItem(R.id.menuSearch)?.actionView as SearchView
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
        searchView.setOnCloseListener {
            clearSearchResult()
            false
        }
        return super.onCreateOptionsMenu(menu)
    }

    private fun clearSearchResult() {
        adapter.submitList(emptyList())
    }

    override fun onBackPressed() {
        if (!searchView.isIconified) {
            searchView.onActionViewCollapsed()
            clearSearchResult()
        } else {
            super.onBackPressed()
        }
    }

    override fun showLoading() {
        super.showLoading()
        loadingDialog.show(supportFragmentManager)
    }

    override fun hideLoading() {
        super.hideLoading()
        loadingDialog.hide()
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