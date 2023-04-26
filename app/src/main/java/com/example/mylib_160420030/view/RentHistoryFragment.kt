package com.example.mylib_160420030.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mylib_160420030.R
import com.example.mylib_160420030.viewmodel.ProfileDetailsViewModel
import com.example.mylib_160420030.viewmodel.RentHistoryViewModel

class RentHistoryFragment: Fragment(){

    private lateinit var viewModel: RentHistoryViewModel
    private val RentHistoryListAdapter = RentHistoryAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rent_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(RentHistoryViewModel::class.java)
        viewModel.readRentHistory()

        val recView = view.findViewById<RecyclerView>(R.id.recViewRentHistory)
        recView?.layoutManager = LinearLayoutManager(context)
        recView?.adapter = RentHistoryListAdapter

        observeRentHistoryData()
    }

    private fun observeRentHistoryData() {
        viewModel.rentLiveData.observe(viewLifecycleOwner){
            RentHistoryListAdapter.updateRentHistory(it)
        }
    }

}