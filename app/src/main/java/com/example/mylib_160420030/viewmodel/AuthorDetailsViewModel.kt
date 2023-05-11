package com.example.mylib_160420030.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.mylib_160420030.model.Authors
import com.example.mylib_160420030.model.Books
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject

class AuthorDetailsViewModel(application: Application): AndroidViewModel(application) {
    private var TAG = "volleyTag"
    private var queue: RequestQueue? = null
    val authorLiveData = MutableLiveData<Authors>()

    fun readDetails (bookId: Int) {
        queue = Volley.newRequestQueue(getApplication())
        var url = "https://gist.githubusercontent.com/ardelvito/2e7e8d1c936771319409b97b561b13ec/raw/4319c989f7820d6663f86a97db7d3b7b212830ae/anmp-authorlist.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val jsonArray = JSONArray(response)
                Log.d("json ARR", jsonArray.toString())
                for (i in 0 until jsonArray.length()) {
                    val eachJsonObj = jsonArray.getJSONObject(i)
                    if(eachJsonObj.getInt("id")==bookId){
                        val id = eachJsonObj.getInt("id")
                        val name = eachJsonObj.getString("name")
                        val born = eachJsonObj.getString("born")
                        val nationality = eachJsonObj.getString("nationality")
                        val url = eachJsonObj.getString("url")
                        val authorWorksArr =  eachJsonObj.getJSONArray("notableWorks")
                        val authorWorksList = ArrayList<String>()
                        for (i in 0 until authorWorksArr.length()){
                            val eachWork = authorWorksArr.getString(i)
                            authorWorksList.add(eachWork)
                        }
                        val bio = eachJsonObj.getString("bio")

                        val author = Authors(id,name,born,nationality,url,authorWorksList,bio)
                        Log.d("Specific", author.toString())
                        authorLiveData.value = author
                    }
                }

            },
            {
                Log.d("showvolley", it.toString())
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}