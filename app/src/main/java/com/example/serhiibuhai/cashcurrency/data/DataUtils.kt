package com.example.serhiibuhai.cashcurrency.data

import retrofit2.Response
import java.io.IOException

object DataUtils {

    @Throws(UnsuccessfulException::class)
    fun <R : Response<*>> executeOrThrowGenericError(block: () -> R): R {
        try {
            val response = block()
            if (response.isSuccessful.not()) {
                throw UnsuccessfulException(response.code())
            }
            return response
        } catch (exception: IOException) {
            throw UnsuccessfulException()
        }
    }
}

