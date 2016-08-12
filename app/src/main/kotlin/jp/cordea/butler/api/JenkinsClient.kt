package jp.cordea.butler.api

import android.content.Context
import jp.cordea.butler.BuildConfig
import jp.cordea.butler.model.UserPreference
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Yoshihiro Tanaka on 2016/07/08.
 */

object JenkinsClient {

    private fun getRetrofit(context: Context): Retrofit {
        val user = UserPreference.load(context)
        var isAuth = false
        if (user.username != null && user.apiToken != null) {
            isAuth = true
        }

        val client = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            client.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
        }

        if (isAuth) {
            client.addInterceptor {
                val basic = Credentials.basic(user.username, user.apiToken)
                val request = it.request()
                val builder = request.newBuilder().header("Authorization", basic)
                it.proceed(builder.build())
            }
        }

        return Retrofit.Builder()
                .baseUrl(user.jenkinsUrl!!)
                .client(client.build())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
    }

    fun getJenkinsService(context: Context): IJenkinsService {
        return getRetrofit(context).create(IJenkinsService::class.java)
    }

}
