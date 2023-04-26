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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class WishlistViewModel(application: Application): AndroidViewModel(application) {
    private var TAG = "volleyTag"
    private var queue: RequestQueue? = null
    val wishlistLiveData = MutableLiveData<ArrayList<Books>>()

    fun readWishlist(){
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
                if(jsonObj.has("wishlist")){
                    Log.d("200found", jsonObj.toString())
                    val wishlistArr = jsonObj.getJSONArray("wishlist")
//                    Log.d("Array", booksArr.toString())
                    val sType = object: TypeToken<List<Books>>(){}.type
                    val result = Gson().fromJson<List<Books>>(wishlistArr.toString(), sType)
                    Log.d("result wishlist", result.toString())
                    Log.d("Debug wishlist", wishlistArr.toString())

                    val booksList = mutableListOf<Books>()
                    for (eachWishlist in result) {
                        val book = Books(
                            id = eachWishlist.id,
                            title = eachWishlist.title,
                            author = eachWishlist.author,
                            publishedDate = eachWishlist.publishedDate,
                            description = eachWishlist.description,
                            coverImage = eachWishlist.coverImage,
                            genres = eachWishlist.genres,
                            rating = eachWishlist.rating
                        )
                        booksList.add(book)
                    }

                    wishlistLiveData.value = booksList as ArrayList<Books>

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