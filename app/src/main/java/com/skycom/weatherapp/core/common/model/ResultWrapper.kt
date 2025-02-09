package com.skycom.weatherapp.core.common.model

sealed class ResultWrapper<out T> {
    data class Success<T>(val data: T) : ResultWrapper<T>()
    data class Error(val message: String? = null, val code: Int? = null) : ResultWrapper<Nothing>()
    data object Loading : ResultWrapper<Nothing>()
    data object Idle : ResultWrapper<Nothing>()
}