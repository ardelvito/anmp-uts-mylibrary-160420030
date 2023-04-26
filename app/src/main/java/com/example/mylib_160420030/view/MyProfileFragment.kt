package com.example.mylib_160420030.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.mylib_160420030.R
import com.example.mylib_160420030.viewmodel.ProfileDetailsViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_my_profile.*
import org.w3c.dom.Text
import java.text.NumberFormat
import java.util.*


class MyProfileFragment : Fragment() {
    private lateinit var viewModel: ProfileDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProfileDetailsViewModel::class.java)
        viewModel.readProfileDetails()
        observeStudentData()
    }

    private fun observeStudentData() {
            viewModel.profileLiveData.observe(viewLifecycleOwner){
                val imgProfile = view?.findViewById<ImageView>(R.id.imgUser)
                val userFN = view?.findViewById<TextView>(R.id.txtUserFullName)
                val userEmail = view?.findViewById<TextView>(R.id.txtUserEmail)
                val userBalance = view?.findViewById<TextView>(R.id.txtUserBalance)
                val userPhone = view?.findViewById<TextView>(R.id.txtUserPhone)
                val progressBarUserImg = view?.findViewById<ProgressBar>(R.id.progressBarImgUser)


                userFN?.text = it.name.toString()
                userEmail?.text = it.email.toString()
                val localeID = Locale("in", "ID")
                val numberFormat = NumberFormat.getCurrencyInstance(localeID)
                val priceFormatted = numberFormat.format(it.accountBalance)
                userBalance?.text = priceFormatted.toString()
                userPhone?.text = it.phone.toString()

                val imgURL = it.url.toString()
                Picasso.get()
                    .load(imgURL)
                    .resize(200, 200)
                    .centerCrop()
                    .into(imgProfile, object : Callback {
                        override fun onSuccess() {
                            // Image loaded successfully
                            progressBarUserImg?.visibility = View.GONE
                            Log.d("Success Load", "IMG Successfully Loaded")

                        }

                        override fun onError(e: Exception?) {
                            // Handle error
                            progressBarUserImg?.visibility = View.GONE
                            Log.d("Error Load", e.toString())
                        }
                    })

                view?.findViewById<Button>(R.id.btnRentHistory)?.setOnClickListener{
                    val action = MyProfileFragmentDirections.actionRentHistory()
                    Navigation.findNavController(it).navigate(action)
                }

                view?.findViewById<Button>(R.id.btnWishlist)?.setOnClickListener{
                    val action = MyProfileFragmentDirections.actionWishlist()
                    Navigation.findNavController(it).navigate(action)
                }

            }

    }

}