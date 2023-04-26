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

class RentHistoryAdapter(val rentHistoryList: ArrayList<Books>): RecyclerView.Adapter<RentHistoryAdapter.RentHistoryViewHolder>() {
    class RentHistoryViewHolder(var view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RentHistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.wishlist_item, parent, false)
        return RentHistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RentHistoryViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.txtIdWishlist).text = rentHistoryList[position].id.toString()
        holder.view.findViewById<TextView>(R.id.txtTitleWishlist).text = rentHistoryList[position].title.toString()
        holder.view.findViewById<TextView>(R.id.txtRatingWishlist).text = rentHistoryList[position].rating.toString()
        holder.view.findViewById<TextView>(R.id.txtDescriptionWishlist).text = rentHistoryList[position].description.toString()
        holder.view.findViewById<TextView>(R.id.txtAuthorWishlist).text = rentHistoryList[position].author.toString()
        holder.view.findViewById<TextView>(R.id.txtPublishedDateWishlist).text = rentHistoryList[position].publishedDate.toString()
        var genres =  rentHistoryList[position].genres?.joinToString(", ").toString()
        holder.view.findViewById<TextView>(R.id.txtGenresWishlist).text = genres
        var imageView = holder.view.findViewById<ImageView>(R.id.imgViewCoverImageWishlist)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBarWishlist)


        val imgURL = rentHistoryList[position].coverImage
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

        Log.d("onbind", rentHistoryList.toString())



    }

    override fun getItemCount(): Int {
        return rentHistoryList.size
    }

    fun updateRentHistory(newRentHistory:ArrayList<Books>){
        rentHistoryList.clear()
        rentHistoryList.addAll(newRentHistory)
        notifyDataSetChanged()
    }
}