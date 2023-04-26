package com.example.mylib_160420030.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.mylib_160420030.R
import com.example.mylib_160420030.R.id.txtReturnDateRent
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
        holder.view.findViewById<TextView>(R.id.txtId).text = booksList[position].id.toString()
        holder.view.findViewById<TextView>(R.id.txtBookTitleRent).text = booksList[position].title
        holder.view.findViewById<TextView>(R.id.txtBookAuthorRent).text = booksList[position].author
        holder.view.findViewById<TextView>(R.id.txtBookRating).text = booksList[position].rating.toString()
        val genres =  booksList[position].genres?.joinToString(", ")
        holder.view.findViewById<TextView>(txtReturnDateRent).text = genres.toString()

        var imageView = holder.view.findViewById<ImageView>(R.id.imgBookCover)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBarBooksDetails)
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

        var bookId = booksList[position].id
        holder.view.findViewById<Button>(R.id.btnBookDetails).setOnClickListener{
            val action = BooksListFragmentDirections.actionBookDetails(bookId)
            Navigation.findNavController(it).navigate(action)
        }

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