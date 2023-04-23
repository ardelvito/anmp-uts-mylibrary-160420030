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
import com.example.mylib_160420030.viewmodel.AuthorsListViewModel
import com.example.mylib_160420030.viewmodel.BooksListViewModel


class LeaderboardAuthorsFragment : Fragment() {
    private lateinit var viewModel: AuthorsListViewModel
    private val authorsListAdapter = LeaderboardAuthorsAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leaderboard_authors, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(AuthorsListViewModel::class.java)
        viewModel.refresh()

        val recView = view.findViewById<RecyclerView>(R.id.recViewCreators)
        recView?.layoutManager = LinearLayoutManager(context)
        recView?.adapter = authorsListAdapter

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.authorsLiveData.observe(viewLifecycleOwner){
            authorsListAdapter.updateBooksList(it)
        }
    }

}