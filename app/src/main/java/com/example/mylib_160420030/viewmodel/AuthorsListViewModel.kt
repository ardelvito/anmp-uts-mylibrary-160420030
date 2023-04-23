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
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class AuthorsListViewModel(application: Application): AndroidViewModel(application) {
    val authorsLiveData = MutableLiveData<ArrayList<Authors>>()
    private var queue: RequestQueue? = null
    val TAG = "volleyTag"

    fun refresh(){

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://pastebin.com/raw/NxxM0PxE"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Authors>>() { }.type
                val result = Gson().fromJson<List<Authors>>(it, sType)
                Log.d("result of student", result.toString())
                authorsLiveData.value = result as ArrayList<Authors> /* = java.util.ArrayList<com.example.anmp_w4.model.Student> */

                Log.d("showvoley", it)

            },
            {
                Log.d("showvoley", it.toString())

            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }
}