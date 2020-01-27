package com.wtf.sample.di.module

import android.app.Application
import androidx.room.Room
import com.wtf.sample.BuildConfig
import com.wtf.sample.api.HttpServiceApi
import com.wtf.sample.api.LoginService
import com.wtf.sample.config.BASE_API_URL
import com.wtf.sample.config.BASE_URL
import com.wtf.sample.config.GlobalHttpHandlerImpl
import com.wtf.sample.db.AppDatabase
import com.wtf.sample.db.AuthTokenDao
import com.wtf.sample.db.UserDao
import com.wtf.sample.repository.AccountRepository
import com.wtf.yggd.di.scope.AppScope
import com.wtf.yggd.http.LiveDataCallAdapterFactory
import com.wtf.yggd.utils.AppExecutors
import dagger.Module
import dagger.Provides
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-10 下午5:11
 */
@Module
class ClientModule {
    @AppScope
    @Provides
    fun provideDb(app: Application): AppDatabase {
        return Room
            .databaseBuilder(app.applicationContext, AppDatabase::class.java, "database.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @AppScope
    @Provides
    fun provideOkHttpClient(globalHttpHandler: GlobalHttpHandlerImpl?): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
        okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        globalHttpHandler?.let {
            okHttpClientBuilder.addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    return chain.proceed(it.httpRequestBefore(chain, chain.request()))
                }
            })
        }
        okHttpClientBuilder.dispatcher(Dispatcher())
        return okHttpClientBuilder
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @AppScope
    @Provides
    fun provideHttpService(okHttpClient: OkHttpClient): HttpServiceApi {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(HttpServiceApi::class.java)
    }

    @AppScope
    @Provides
    fun provideLoginService(okHttpClient: OkHttpClient): LoginService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(LoginService::class.java)
    }

    @AppScope
    @Provides
    fun provideAuthTokenDao(appDatabase: AppDatabase): AuthTokenDao {
        return appDatabase.authTokenDao()
    }

    @AppScope
    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @AppScope
    @Provides
    fun provideAccountRepository(
        appExecutors: AppExecutors,
        authTokenDao: AuthTokenDao,
        userDao: UserDao,
        httpServiceApi: HttpServiceApi,
        loginService: LoginService,
        globalHttpHandler: GlobalHttpHandlerImpl?
    ): AccountRepository {
        return AccountRepository(appExecutors, authTokenDao, userDao, httpServiceApi, loginService, globalHttpHandler)
    }
}