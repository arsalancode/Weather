package com.tomorrow.weather.utils

import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.isNoInternetException() = this is SocketTimeoutException ||
        this is UnknownHostException ||
        this is ConnectException
