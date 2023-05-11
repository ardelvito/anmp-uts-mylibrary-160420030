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
import com.example.mylib_160420030.model.Genres
import com.google.gson.Gson
import org.json.JSONObject

class BookDetailsViewModel(application: Application): AndroidViewModel(application) {
    private var TAG = "volleyTag"
    private var queue: RequestQueue? = null
    val bookLiveData = MutableLiveData<Books>()

    fun readDetails (bookId: Int) {
        queue = Volley.newRequestQueue(getApplication())
        var url = "https://gist.githubusercontent.com/ardelvito/41495d8dfa7142f0765f5d0cea1ce14b/raw/06f71d69d59ada5ebad0bb05075a2798526f1bf7/anmp-bookslist.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
//                val result = Gson().fromJson<JSONObject>(response, Books::class.java)
                val jsonObj = JSONObject(response)
                if(jsonObj.has("books")){
                    val jsonArr = jsonObj.getJSONArray("books")
                    val booksDetailList = mutableListOf<Books>()
                    for (i in 0 until jsonArr.length()){
                        val bookObj = jsonArr.getJSONObject(i)
                        val eachBookId = bookObj.getInt("id")
                        if(eachBookId == bookId){
                            //found the book with specific ID
                            val title = bookObj.getString("title")
                            val author = bookObj.getString("author")
                            val publishedDate = bookObj.getString("publishedDate")
                            val description = bookObj.getString("description")
                            val coverImage = bookObj.getString("coverImage")
                            val genresArr = bookObj.getJSONArray("genres")
                            val genresList = ArrayList<String>()
                            for (i in 0 until genresArr.length()){
                                val eachGenre = genresArr.getString(i)
                                genresList.add(eachGenre)
                            }
                            val rating = bookObj.getDouble("rating")
                            val book = Books(bookId, title, author, publishedDate, description, coverImage, genresList, rating)
                            Log.d("Specific", book.toString())
                            bookLiveData.value = book
                            Log.d("showvolley", book.toString())
                        }
                    }
                }
                else{
                    Log.d("book not found", "not found")
                }

            },
            {
                Log.d("showvolley", it.toString())
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}