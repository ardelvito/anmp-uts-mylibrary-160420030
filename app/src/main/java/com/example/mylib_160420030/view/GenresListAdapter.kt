package com.example.mylib_160420030.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mylib_160420030.R
import com.example.mylib_160420030.model.Books
import com.example.mylib_160420030.model.Genres

class GenresListAdapter(val genresList: ArrayList<Genres>):RecyclerView.Adapter<GenresListAdapter.GenresListViewHolder>() {
    class GenresListViewHolder(var view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.genres_list_item, parent, false)
        return GenresListAdapter.GenresListViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenresListViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.txtGenreList).text = genresList[position].name
    }

    fun updateGenresList(newGenres:ArrayList<Genres>){
        genresList.clear()
        genresList.addAll(newGenres)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return genresList.size
    }
}