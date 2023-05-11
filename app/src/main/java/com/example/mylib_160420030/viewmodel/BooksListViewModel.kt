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

class BooksListViewModel(application: Application): AndroidViewModel(application) {
    val booksLiveData = MutableLiveData<ArrayList<Books>>()
    private var queue: RequestQueue? = null
    val TAG = "volleyTag"

    fun refresh(){

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://gist.githubusercontent.com/ardelvito/41495d8dfa7142f0765f5d0cea1ce14b/raw/06f71d69d59ada5ebad0bb05075a2798526f1bf7/anmp-bookslist.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<JSONObject>() {}.type
                Log.d("Type Object is", sType.toString())
                val result = Gson().fromJson<JSONObject>(it, sType)
//                Log.d("testing", it)
                val jsonObj = JSONObject(it)
                if(jsonObj.has("books")){
//                    Log.d("200found", jsonObj.toString())
                    val booksArr = jsonObj.getJSONArray("books")
//                    Log.d("Array", booksArr.toString())
                    val sType = object:TypeToken<List<Books>>(){}.type
                    val result = Gson().fromJson<List<Books>>(booksArr.toString(), sType)
                    Log.d("Heres the result", result.toString())

                    booksLiveData.value = result as ArrayList<Books>

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