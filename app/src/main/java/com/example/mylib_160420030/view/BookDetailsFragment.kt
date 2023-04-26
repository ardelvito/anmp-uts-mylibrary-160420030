package com.example.mylib_160420030.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mylib_160420030.R
import com.example.mylib_160420030.viewmodel.BookDetailsViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class BookDetailsFragment: Fragment() {
    private lateinit var viewModel: BookDetailsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(BookDetailsViewModel::class.java)
//        viewModel.fetch()
        val idBook = BookDetailsFragmentArgs.fromBundle(requireArguments()).idBook
        viewModel.readDetails(idBook)
        observeBookData()

    }

    private fun observeBookData() {
        viewModel.bookLiveData.observe(viewLifecycleOwner){
            val bookCover = view?.findViewById<ImageView>(R.id.imgCoverBookDetails)
            val bookTitle = view?.findViewById<TextView>(R.id.txtBookTitleDetails)
            val progressBar= view?.findViewById<ProgressBar>(R.id.progressBarBookDetails)
            val imgURL = it.coverImage.toString()
            val bookAuthor = view?.findViewById<TextView>(R.id.txtBookAuthorDetails)
            val bookRating = view?.findViewById<TextView>(R.id.txtBookRatingDetails)
            val bookPublish = view?.findViewById<TextView>(R.id.txtBookPublishDetails)
            val bookGenres = view?.findViewById<TextView>(R.id.txtBookGenresDetail)
            val bookDesc = view?.findViewById<TextView>(R.id.txtBookDescDetails)

            Picasso.get()
                .load(imgURL)
                .resize(200, 200)
                .centerCrop()
                .into(bookCover, object : Callback {
                    override fun onSuccess() {
                        // Image loaded successfully
                        progressBar?.visibility = View.GONE
                        Log.d("Success Load", "IMG Successfully Loaded")

                    }

                    override fun onError(e: Exception?) {
                        // Handle error
                        progressBar?.visibility = View.GONE
                        Log.d("Error Load", e.toString())
                    }
                })

            bookTitle?.text = it.title.toString()
            bookAuthor?.text = it.author.toString()
            bookRating?.text = it.rating.toString()
            bookPublish?.text = it.publishedDate.toString()
            bookGenres?.text =  it.genres?.joinToString(", ")
            bookDesc?.text = it.description.toString()
            Log.d("Genres", it.genres.toString())




        }
    }

}