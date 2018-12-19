package com.example.serhiibuhai.cashcurrency.data

class UnsuccessfulException(responseCode: Int? = null) :
    Exception("Request was not successful. ${responseCode?.let { "Error code: $it" } ?: "Unknown error"}")