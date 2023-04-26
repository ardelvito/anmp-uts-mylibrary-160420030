package com.example.mylib_160420030.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mylib_160420030.model.Books
import com.example.mylib_160420030.model.Profile
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class RentHistoryViewModel(application: Application): AndroidViewModel(application) {
    private var TAG = "volleyTag"
    private var queue: RequestQueue? = null
    val rentLiveData = MutableLiveData<ArrayList<Books>>()

    fun readRentHistory () {
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://gist.githubusercontent.com/ardelvito/6a1b782271e4983c1e89464093cbed4f/raw/8fa088962fa6f762d7246aa38137e78486674498/anmp-myprofile.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<JSONObject>() {}.type
                Log.d("Type Object is", sType.toString())
                val result = Gson().fromJson<JSONObject>(it, sType)
//                Log.d("testing", it)
                val jsonObj = JSONObject(it)
                if(jsonObj.has("rentHistory")){
                    Log.d("200found", jsonObj.toString())
                    val rentHistoryArr = jsonObj.getJSONArray("rentHistory")
//                    Log.d("Array", booksArr.toString())
                    val sType = object:TypeToken<List<Books>>(){}.type
                    val result = Gson().fromJson<List<Books>>(rentHistoryArr.toString(), sType)
                    Log.d("result rent history", result.toString())
                    Log.d("Debug rent history", rentHistoryArr.toString())

                    val booksList = mutableListOf<Books>()
                    for (rent in result) {
                        val book = Books(
                            id = rent.id,
                            title = rent.title,
                            author = rent.author,
                            publishedDate = rent.publishedDate,
                            description = rent.description,
                            coverImage = rent.coverImage,
                            genres = rent.genres,
                            rating = rent.rating
                        )
                        booksList.add(book)
                    }

                    rentLiveData.value = booksList as ArrayList<Books>

                    Log.d("showvoley", it)

                }
                else{
                    Log.d("404notfound", "not found")
                }
                Log.d("Log result is", result.toString())


            },
            {
                Log.d("showvoley", it.toString())

            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

}