package com.example.mylib_160420030.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mylib_160420030.R
import com.example.mylib_160420030.model.Books
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class WishlistAdapter(val wishlistList: ArrayList<Books>): RecyclerView.Adapter<WishlistAdapter.WishlistViewAdapter>() {
    class WishlistViewAdapter(var view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistViewAdapter {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.wishlist_item, parent, false)
        return WishlistViewAdapter(view)
    }

    override fun onBindViewHolder(holder: WishlistViewAdapter, position: Int) {
        holder.view.findViewById<TextView>(R.id.txtIdWishlist).text = wishlistList[position].id.toString()
        holder.view.findViewById<TextView>(R.id.txtTitleWishlist).text = wishlistList[position].title.toString()
        holder.view.findViewById<TextView>(R.id.txtRatingWishlist).text = wishlistList[position].rating.toString()
        holder.view.findViewById<TextView>(R.id.txtDescriptionWishlist).text = wishlistList[position].description.toString()
        holder.view.findViewById<TextView>(R.id.txtAuthorWishlist).text = wishlistList[position].author.toString()
        holder.view.findViewById<TextView>(R.id.txtPublishedDateWishlist).text = wishlistList[position].publishedDate.toString()
        var genres =  wishlistList[position].genres?.joinToString(", ").toString()
        holder.view.findViewById<TextView>(R.id.txtGenresWishlist).text = genres
        var imageView = holder.view.findViewById<ImageView>(R.id.imgViewCoverImageWishlist)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBarWishlist)


        val imgURL = wishlistList[position].coverImage
        Picasso.get()
            .load(imgURL)
            .resize(200, 200)
            .centerCrop()
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    // Image loaded successfully
                    progressBar.visibility = View.GONE
                    Log.d("Success Load", "IMG Successfully Loaded")

                }

                override fun onError(e: Exception?) {
                    // Handle error
                    progressBar.visibility = View.GONE
                    Log.d("Error Load", e.toString())
                }

            })
        Log.d("Test", imgURL.toString())
        Log.d("onbind wishlist", wishlistList.toString())

    }

    override fun getItemCount(): Int {
        return wishlistList.size
    }

    fun updateWishlist(newWishlist:ArrayList<Books>){
        wishlistList.clear()
        wishlistList.addAll(newWishlist)
        notifyDataSetChanged()
    }
}