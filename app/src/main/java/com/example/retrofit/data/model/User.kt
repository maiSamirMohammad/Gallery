package com.example.retrofit.data.model

import java.io.Serializable

class UsersResponse : ArrayList<User>()
data class User(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
): Serializable