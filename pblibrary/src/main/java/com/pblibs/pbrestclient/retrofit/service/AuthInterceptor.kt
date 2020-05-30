package com.pblibs.pbrestclient.retrofit.service

import android.content.Context
import com.pblibs.utility.PBSessionManager
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Proggy Blast on 16/5/20 7:06 PM
 */


class AuthInterceptor(context: Context) : Interceptor {

    private val sessionManager = PBSessionManager.getInstance(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        sessionManager.fetchAuthToken()?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
            requestBuilder.addHeader("Content-Type", "application/json")
            requestBuilder.addHeader("Accept", "application/json")
        }

        return chain.proceed(requestBuilder.build())
    }
}