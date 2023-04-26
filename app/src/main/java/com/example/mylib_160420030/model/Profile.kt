package com.example.mylib_160420030.model

class Profile(
    val id: Int,
    val name: String?,
    val email: String?,
    val phone: String?,
    val url: String?,
    val accountBalance: Int?,
    val rentHistory: List<Books>?,
    val wishlist: List<Books>?
) {
//    override fun toString(): String {
//        return "Profile(id=$id, name=$name, email=$email, phone=$phone, url=$url, accountBalance=$accountBalance, rentHistory=$rentHistory)"
//    }
}