package com.dag.hocam.data.user

data class RegisterRequest(
    val username:String,
    val password:String,
    val name:String,
    val email:String
)