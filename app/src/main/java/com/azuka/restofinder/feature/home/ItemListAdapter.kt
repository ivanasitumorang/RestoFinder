package com.azuka.restofinder.feature.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.azuka.restofinder.R
import com.azuka.restofinder.domain.model.Restaurant
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_restaurant_list.view.*


/**
 * Created by ivanaazuka on 30/09/20.
 * Android Engineer
 */


class ItemListAdapter :
    ListAdapter<Restaurant, ItemListAdapter.ViewHolder>(ItemListDiffCallback()) {

    private var clickListener: ((Restaurant) -> Unit)? = null

    fun setOnItemClickListener(clickListener: ((Restaurant) -> Unit)) {
        this.clickListener = clickListener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(restaurant: Restaurant, clickListener: ((Restaurant) -> Unit)?) {
            itemView.apply {
                if (restaurant.featuredImage.isEmpty()) {
                    ivItemImage.setImageResource(R.drawable.ic_no_image)
                } else {
                    Picasso.get()
                        .load(restaurant.featuredImage)
                        .fit()
                        .error(R.drawable.ic_no_image)
                        .into(ivItemImage)
                }
                tvItemName.text = restaurant.name
                tvItemCuisines.text = restaurant.cuisines
                ratingBar.rating = restaurant.userRating.rating.toFloat()
                tvItemVotes.text = context.getString(
                    R.string.home_item_search_vote_format,
                    restaurant.userRating.votes
                )
                tvItemPriceRange.text = context.getString(
                    R.string.home_item_search_price_range_format,
                    restaurant.priceRange
                )
                itemList.setOnClickListener { clickListener?.invoke(restaurant) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_restaurant_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}

class ItemListDiffCallback : DiffUtil.ItemCallback<Restaurant>() {
    override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
        return oldItem == newItem
    }

}