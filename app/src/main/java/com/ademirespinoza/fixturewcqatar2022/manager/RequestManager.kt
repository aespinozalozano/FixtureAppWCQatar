package com.ademirespinoza.fixturewcqatar2022.manager

import android.content.Context
import com.ademirespinoza.fixturewcqatar2022.models.FixtureResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

class RequestManager(var context: Context) {

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://api.cup2022.ir/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getFixture(listener: Any, id: Int) {
        val callFixture = retrofit.create(CallFixture::class.java)
        val call = callFixture.callFixtures(id)
        call.enqueue(object : Callback<FixtureResponse?> {
            override fun onResponse(
                call: Call<FixtureResponse?>,
                response: Response<FixtureResponse?>,
            ) {
                if (!response.isSuccessful) {
                    listener.didError()
                    return
                }
                listener.didFetch(response.body(), response.message())
            }

            override fun onFailure(call: Call<FixtureResponse?>, t: Throwable) {
                listener.didError()
            }
        })
    }

    fun getFixture(listener: Any) {

    }

    private interface CallFixture {
        @GET("api/v1/team")
        @Headers(
            "Accept: application/json",
            "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2M2Q0M2QyNDA2ZjFjOGQwYTk0ODJkMDIiLCJpYXQiOjE2NzQ4NTM2NjksImV4cCI6MTY3NDk0MDA2OX0.RgCcacvpFsYGFgkX5uA2r1KBmcbNK1j3HLquXwCYoXo",
            "Content-Type: application/json"
        )
        fun callFixtures(
            @Path("id") id: Int,
        ): Call<FixtureResponse>
    }

    open class ResponseListener {

    }

}

private fun Any.didFetch(body: FixtureResponse?, message: String) {

}

private fun Any.didError() {

}
