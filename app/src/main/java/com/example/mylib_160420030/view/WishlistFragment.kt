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
import com.example.mylib_160420030.viewmodel.WishlistViewModel

class WishlistFragment:Fragment() {
    private lateinit var viewModel: WishlistViewModel
    private val WishlistAdapter = WishlistAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wishlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(WishlistViewModel::class.java)
        viewModel.readWishlist()

        val recView = view.findViewById<RecyclerView>(R.id.recViewWishlist)
        recView?.layoutManager = LinearLayoutManager(context)
        recView?.adapter = WishlistAdapter

        observeWishlistData()
    }

    private fun observeWishlistData() {
        viewModel.wishlistLiveData.observe(viewLifecycleOwner){
            WishlistAdapter.updateWishlist(it)
        }
    }


}