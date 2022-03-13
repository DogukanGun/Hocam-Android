package com.dag.hocam.data.user

enum class UserOperationType {
    LOGIN,
    REGISTER;


    fun toggle():UserOperationType{
        return if (this == LOGIN) REGISTER else LOGIN
    }
}