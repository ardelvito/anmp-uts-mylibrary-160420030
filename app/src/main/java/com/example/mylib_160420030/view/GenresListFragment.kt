package com.example.mylib_160420030.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mylib_160420030.R
import com.example.mylib_160420030.viewmodel.BooksListViewModel
import com.example.mylib_160420030.viewmodel.GenresListViewModel


class GenresListFragment : Fragment() {
    private lateinit var viewModel:GenresListViewModel
    private val genresListAdapter = GenresListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_genres_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(GenresListViewModel::class.java)
        viewModel.refresh()

        val recView = view.findViewById<RecyclerView>(R.id.recViewGenresList)
        recView?.layoutManager = LinearLayoutManager(context)
        recView?.adapter = genresListAdapter

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.genresLiveData.observe(viewLifecycleOwner){
            genresListAdapter.updateGenresList(it)
        }
    }

}