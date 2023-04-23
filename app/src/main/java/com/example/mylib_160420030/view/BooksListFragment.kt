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


class BooksListFragment : Fragment() {
    private lateinit var viewModel: BooksListViewModel
    private val booksListAdapter = BooksListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_books_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(BooksListViewModel::class.java)
        viewModel.refresh()

        val recView = view.findViewById<RecyclerView>(R.id.recViewGenresList)
        recView?.layoutManager = LinearLayoutManager(context)
        recView?.adapter = booksListAdapter

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.booksLiveData.observe(viewLifecycleOwner){
            booksListAdapter.updateBooksList(it)
        }
    }


}