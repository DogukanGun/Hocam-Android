package com.dag.hocam.data.user

data class AuthenticationResponseData(
    var token:String,
    var username:String,
    var userType:String
)
