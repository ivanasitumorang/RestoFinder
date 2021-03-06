package com.azuka.restofinder.favorite.feature.favoritelist

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.azuka.base.di.component.Component
import com.azuka.base.presentation.BaseActivityVM
import com.azuka.base.utils.goToScreen
import com.azuka.restofinder.appComponent
import com.azuka.restofinder.favorite.R
import com.azuka.restofinder.favorite.feature.FavoriteViewModel
import com.azuka.restofinder.favorite.feature.favoritelist.di.DaggerFavoriteListComponent
import com.azuka.restofinder.favorite.feature.favoritelist.di.FavoriteListComponent
import com.azuka.restofinder.feature.home.ItemListAdapter
import com.azuka.restofinder.utils.AppConstant
import com.azuka.restofinder.utils.Screen
import kotlinx.android.synthetic.main.activity_favorite_list.*

class FavoriteListActivity : BaseActivityVM<FavoriteViewModel>() {

    private val viewModel: FavoriteViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(FavoriteViewModel::class.java)
    }

    private val adapter: ItemListAdapter by lazy {
        ItemListAdapter()
    }

    private lateinit var component: FavoriteListComponent

    override fun getLayoutId(): Int = R.layout.activity_favorite_list

    override fun onActivityReady(savedInstanceState: Bundle?) {
        setupUI()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.favoriteRestaurants.observe(this, { restaurants ->
            if (restaurants.isNullOrEmpty()) {
                displayBackgroundInfo(true)
            } else {
                displayBackgroundInfo(false)
                adapter.submitList(restaurants)
            }
        })
    }

    private fun setupUI() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.favorite_list_toolbar_title)
        }
        viewModel.getFavoriteRestaurants()
        adapter.setOnItemClickListener { restaurant ->
            goToScreen(Screen.detailRestaurant) {
                putExtra(AppConstant.Home.TAG_RESTAURANT, restaurant)
            }
        }
        rvFavoriteList.adapter = adapter
    }

    private fun displayBackgroundInfo(needToShow: Boolean) {
        if (needToShow) rlNoFavorite.visibility = View.VISIBLE
        else rlNoFavorite.visibility = View.GONE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun initDependencyInjection() {
        component.inject(this)
    }

    override fun createComponent(): Component {
        component = DaggerFavoriteListComponent.builder()
            .appComponent(appComponent())
            .build()
        return component
    }

    override fun getVM(): FavoriteViewModel? = viewModel
}