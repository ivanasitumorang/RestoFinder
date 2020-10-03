package com.azuka.restofinder.favorite.feature.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.azuka.base.di.component.Component
import com.azuka.base.presentation.BaseActivityVM
import com.azuka.base.utils.openBrowser
import com.azuka.base.utils.showToast
import com.azuka.restofinder.appComponent
import com.azuka.restofinder.domain.model.Restaurant
import com.azuka.restofinder.favorite.R
import com.azuka.restofinder.favorite.feature.FavoriteViewModel
import com.azuka.restofinder.favorite.feature.detail.di.DaggerDetailComponent
import com.azuka.restofinder.favorite.feature.detail.di.DetailComponent
import com.azuka.restofinder.favorite.utils.formatPriceToSymbol
import com.azuka.restofinder.utils.AppConstant
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import com.azuka.restofinder.R as appR

class DetailActivity : BaseActivityVM<FavoriteViewModel>() {

    private val viewModel: FavoriteViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(FavoriteViewModel::class.java)
    }

    private val restaurant by lazy {
        intent.extras?.get(AppConstant.Home.TAG_RESTAURANT) as? Restaurant
    }

    private lateinit var component: DetailComponent
    private lateinit var btnFavorite: MenuItem

    private var isRestaurantFavorite = false

    override fun getLayoutId(): Int = R.layout.activity_detail

    override fun onActivityReady(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (restaurant == null) {
            showToast(getString(R.string.detail_restaurant_not_available))
        } else {
            setupUI()
            setupClickListener()
            viewModel.checkIfFavoriteRestaurant(restaurant?.id)
        }
    }

    private fun observeFavorite() {
        viewModel.isFavorite.observe(this, Observer { isFavorite ->
            isRestaurantFavorite = isFavorite
            if (::btnFavorite.isInitialized) {
                setFavoriteIcon(isFavorite)
            }
        })
    }

    private fun setupClickListener() {
        btnRestaurantToDetails.setOnClickListener {
            restaurant?.let {
                openBrowser(it.url)
            }
        }
    }

    private fun setupUI() {
        restaurant?.let {
            setupToolbarTitle(it.name)

            tvRestaurantName.text = it.name
            tvRestaurantPriceForTwo.text =
                getString(R.string.detail_price_for_two_format, it.currency, it.averageCostForTwo)

            val priceRangeSymbol = it.priceRange.formatPriceToSymbol()
            tvRestaurantPriceRangeHighlight.text = priceRangeSymbol.first
            tvRestaurantPriceRangeDim.text = priceRangeSymbol.second
            tvRestaurantRatingText.text = it.userRating.ratingText
            tvRestaurantVotes.text =
                getString(R.string.detail_vote_total_format, it.userRating.votes)
            tvRestaurantCuisines.text = it.cuisines
            ratingBar.rating = it.userRating.rating.toFloat()
            if (it.featuredImage.isEmpty()) {
                ivRestaurantImage.setImageResource(R.drawable.ic_no_image)
            } else {
                Picasso.get()
                    .load(it.featuredImage)
                    .fit()
                    .error(R.drawable.ic_no_image)
                    .into(ivRestaurantImage)
            }
        }
    }

    private fun setupToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun toggleFavoriteIcon() {
        restaurant?.let {
            if (isRestaurantFavorite) {
                viewModel.removeFromFavorite(it)
            } else {
                viewModel.saveToFavorite(it)
            }
        }
    }

    private fun setFavoriteIcon(state: Boolean) {
        if (state) {
            btnFavorite.icon = ContextCompat.getDrawable(this, appR.drawable.ic_love_full)
        } else {
            btnFavorite.icon = ContextCompat.getDrawable(this, appR.drawable.ic_love_outline)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        btnFavorite = menu?.findItem(R.id.menuFavorite) as MenuItem
        observeFavorite()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menuFavorite -> {
            toggleFavoriteIcon()
            true
        }
        android.R.id.home -> {
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun createComponent(): Component {
        component = DaggerDetailComponent.builder()
            .appComponent(appComponent())
            .build()
        return component
    }

    override fun initDependencyInjection() {
        component.inject(this)
    }

    override fun getVM(): FavoriteViewModel? = viewModel
}