package com.ademirespinoza.fixturewcqatar2022

import com.ademirespinoza.fixturewcqatar2022.models.FixtureResponse

interface ResponseListener {
    fun didFetch(response: FixtureResponse?, message: String?)
    fun didError(message: String?)
}