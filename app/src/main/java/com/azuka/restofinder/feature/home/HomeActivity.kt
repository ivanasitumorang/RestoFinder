package com.azuka.restofinder.feature.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.azuka.base.di.component.Component
import com.azuka.base.presentation.BaseActivityVM
import com.azuka.base.utils.goToScreen
import com.azuka.restofinder.R
import com.azuka.restofinder.appComponent
import com.azuka.restofinder.domain.model.InfoData
import com.azuka.restofinder.feature.HomeViewModel
import com.azuka.restofinder.feature.home.di.DaggerHomeComponent
import com.azuka.restofinder.feature.home.di.HomeComponent
import com.azuka.restofinder.utils.AppConstant
import com.azuka.restofinder.utils.Screen
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.layout_info.*

class HomeActivity : BaseActivityVM<HomeViewModel>() {

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    private val adapter: ItemListAdapter by lazy {
        ItemListAdapter()
    }

    private lateinit var component: HomeComponent

    private lateinit var searchView: SearchView

    override fun getLayoutId() = R.layout.activity_home

    override fun onActivityReady(savedInstanceState: Bundle?) {
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        adapter.setOnItemClickListener { restaurant ->
            goToScreen(Screen.detailRestaurant) {
                putExtra(AppConstant.Home.TAG_RESTAURANT, restaurant)
            }
        }
        rvItemList.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.searchResult.observe(this, Observer { restaurants ->
            if (restaurants.isNullOrEmpty()) {
                displayBackgroundInfo(needToShow = true)
            } else {
                adapter.submitList(restaurants)
                displayBackgroundInfo(needToShow = false)
            }
        })
    }

    private fun displayBackgroundInfo(needToShow: Boolean, data: InfoData? = null) {
        if (data != null) {
            ivImage.setImageResource(data.image)
            tvMessage.text = data.message
        } else {
            ivImage.setImageResource(R.drawable.ic_food_store)
            tvMessage.text = getString(R.string.home_welcoming_message)
        }
        if (needToShow) bgInfo.visibility = View.VISIBLE
        else bgInfo.visibility = View.GONE
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menuFavorite -> {
            goToScreen(Screen.favoriteList)
            true
        }

        else -> super.onOptionsItemSelected(item)
    }

    private fun clearSearchResult() {
        adapter.submitList(emptyList())
        displayBackgroundInfo(needToShow = true)
    }

    override fun onBackPressed() {
        if (!searchView.isIconified) {
            searchView.onActionViewCollapsed()
            clearSearchResult()
        } else {
            super.onBackPressed()
        }
    }

    override fun createComponent(): Component {
        component = DaggerHomeComponent.builder()
            .appComponent(appComponent())
            .build()
        return component
    }

    override fun initDependencyInjection() {
        component.inject(this)
    }

    override fun getVM(): HomeViewModel? = viewModel
}