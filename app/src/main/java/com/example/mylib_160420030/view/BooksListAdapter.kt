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
import com.example.mylib_160420030.R.id.txtBookGenres
import com.example.mylib_160420030.model.Books
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class BooksListAdapter (val booksList: ArrayList<Books>): RecyclerView.Adapter<BooksListAdapter.BooksListViewHolder>() {
    class BooksListViewHolder(var view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.books_list_item, parent, false)
        return  BooksListViewHolder(view)
    }

    override fun onBindViewHolder(holder: BooksListViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.txtId).text = booksList[position].id
        holder.view.findViewById<TextView>(R.id.txtBookTitle).text = booksList[position].title
        holder.view.findViewById<TextView>(R.id.txtBookAuthor).text = booksList[position].author
        holder.view.findViewById<TextView>(R.id.txtBookRating).text = booksList[position].rating
        val genres =  booksList[position].genres?.joinToString(", ")
        holder.view.findViewById<TextView>(txtBookGenres).text = genres.toString()

        var imageView = holder.view.findViewById<ImageView>(R.id.imgBookCover)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBarBooksList)
        val imgURL = booksList[position].coverImage

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

    }

    fun updateBooksList(newBooks:ArrayList<Books>){
        booksList.clear()
        booksList.addAll(newBooks)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return booksList.size
    }

}