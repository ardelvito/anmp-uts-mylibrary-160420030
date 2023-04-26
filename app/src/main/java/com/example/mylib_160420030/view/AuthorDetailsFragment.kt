package com.example.mylib_160420030.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.mylib_160420030.R
import com.example.mylib_160420030.viewmodel.AuthorDetailsViewModel
import com.example.mylib_160420030.viewmodel.ProfileDetailsViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class AuthorDetailsFragment : Fragment() {
    private lateinit var viewModel: AuthorDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_author_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(AuthorDetailsViewModel::class.java)
        val idAuthor = AuthorDetailsFragmentArgs.fromBundle(requireArguments()).authorId
        viewModel.readDetails(idAuthor)
        observeAuthorDetails()
    }

    private fun observeAuthorDetails() {
        viewModel.authorLiveData.observe(viewLifecycleOwner){
            val authorImg = view?.findViewById<ImageView>(R.id.imgViewAuthorDetails)
            val imgURL = it.url.toString()
            val authorName = view?.findViewById<TextView>(R.id.txtNameAuthorDetails)
            val id = view?.findViewById<TextView>(R.id.txtIdAuthorDetails)
            val born = view?.findViewById<TextView>(R.id.txtBornAuthorDetails)
            val bio = view?.findViewById<TextView>(R.id.txtBioAuthorDetails)
            val works = view?.findViewById<TextView>(R.id.txtNotableWorksAuthorDetails)
            val nationality = view?.findViewById<TextView>(R.id.txtNationalityAuthorDetails)
            val progressBar = view?.findViewById<ProgressBar>(R.id.progressBarImgAuthorDetails)

            Picasso.get()
                .load(imgURL)
                .resize(200, 200)
                .centerCrop()
                .into(authorImg, object : Callback {
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

            authorName?.text = it.name.toString()
            id?.text = it.id.toString()
            born?.text = it.born.toString()
            bio?.text = it.bio.toString()
            val notableWorks = it.notableWorks?.joinToString(" , ")
            works?.text = notableWorks
            nationality?.text = it.nationality.toString()

        }
    }
}
