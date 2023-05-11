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
import com.example.mylib_160420030.model.Genres
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenresListViewModel(application: Application): AndroidViewModel(application)  {
    val genresLiveData = MutableLiveData<ArrayList<Genres>>()
    private var queue: RequestQueue? = null
    val TAG = "volleyTag"

    fun refresh(){

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://gist.githubusercontent.com/ardelvito/3b140af8a53a5033c69939b62b5f20ae/raw/74f3ad4c899a1ab5d22a5d9a38b41423cf6b73a2/anmp-genreslist.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Genres>>() { }.type
                val result = Gson().fromJson<List<Genres>>(it, sType)
                Log.d("result of student", result.toString())
                genresLiveData.value = result as ArrayList<Genres> /* = java.util.ArrayList<com.example.anmp_w4.model.Student> */

                Log.d("showvoley", it)

            },
            {
                Log.d("showvoley", it.toString())

            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }
}