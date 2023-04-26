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
import com.example.mylib_160420030.model.Authors
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class LeaderboardAuthorsAdapter (val authorsList: ArrayList<Authors>): RecyclerView.Adapter<LeaderboardAuthorsAdapter.AuthorListViewHolder>() {
    class AuthorListViewHolder(var view: View): RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.author_list_item, parent, false)
        return AuthorListViewHolder(view)
    }

    override fun onBindViewHolder(holder: AuthorListViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.txtGenreList).text = authorsList[position].name
        holder.view.findViewById<TextView>(R.id.txtAuthorBirthDate).text = authorsList[position].born
        holder.view.findViewById<TextView>(R.id.txtAuthorNationality).text = authorsList[position].nationality
        val authorWorks =  authorsList[position].notableWorks?.joinToString(", ")
        holder.view.findViewById<TextView>(R.id.txtAuthorWorks).text = authorWorks.toString()

        Log.d("ini id author",  authorsList[position].id.toString())

        var imageView = holder.view.findViewById<ImageView>(R.id.imgAuthor)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBarAuthorList)
        val imgURL = authorsList[position].url

        Picasso.get()
            .load(imgURL)
            .resize(350, 450)
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

        val authorId = authorsList[position].id
        holder.view.findViewById<Button>(R.id.btnAuthorDetail).setOnClickListener{
            val action = LeaderboardAuthorsFragmentDirections.actionAuthorDetails(authorId)
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateBooksList(newAuthors:ArrayList<Authors>){
        authorsList.clear()
        authorsList.addAll(newAuthors)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return authorsList.size
    }

}