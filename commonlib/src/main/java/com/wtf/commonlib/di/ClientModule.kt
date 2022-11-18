package com.wtf.commonlib.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.harveyhaha.yggd.BuildConfig
import com.harveyhaha.yggd.http.LiveDataCallAdapterFactory
import com.harveyhaha.yggd.utils.AppExecutors
import com.wtf.commonlib.api.HttpServiceApi
import com.wtf.commonlib.api.LoginService
import com.wtf.commonlib.config.BASE_API_URL
import com.wtf.commonlib.config.BASE_URL
import com.wtf.commonlib.config.GlobalHttpHandlerImpl
import com.wtf.commonlib.db.AppDatabase
import com.wtf.commonlib.db.AuthTokenDao
import com.wtf.commonlib.db.UserDao
import com.wtf.commonlib.repository.AccountRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

/**
 *
 * @Description:
 * @Author:         wutengfei
 * @CreateDate:     2020/9/24 17:39/
 */
@Module
@InstallIn(SingletonComponent::class)
object ClientModule {
    @Singleton
    @Provides
    fun provideGlobalHttpHandlerImpl(): GlobalHttpHandlerImpl? {
        return GlobalHttpHandlerImpl()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.Main
    }

    @Singleton
    @Provides
    fun provideAppExecutors(): AppExecutors {
        return AppExecutors()
    }

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, "database.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
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

    @Singleton
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

    @Singleton
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

    @Singleton
    @Provides
    fun provideAuthTokenDao(appDatabase: AppDatabase): AuthTokenDao {
        return appDatabase.authTokenDao()
    }

    @Singleton
    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Singleton
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