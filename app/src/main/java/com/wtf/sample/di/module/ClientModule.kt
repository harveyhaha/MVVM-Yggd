package com.wtf.sample.di.module

import android.app.Application
import androidx.room.Room
import com.wtf.sample.BuildConfig
import com.wtf.sample.api.HttpServiceApi
import com.wtf.sample.config.BASE_URL
import com.wtf.sample.db.AppDatabase
import com.wtf.sample.db.AuthTokenDao
import com.wtf.sample.db.UserDao
import com.wtf.sample.http.LiveDataCallAdapterFactory
import com.wtf.sample.repository.AccountRepository
import com.wtf.sample.utils.AppExecutors
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-10 下午5:11
 */
@Module
class ClientModule {
    @Singleton
    @Provides
    fun provideDb(app: Application): AppDatabase {
        return Room
            .databaseBuilder(app.applicationContext, AppDatabase::class.java, "database.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideHttpService(): HttpServiceApi {
        val okHttpClientBuilder = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
        okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        return Retrofit.Builder()
            .client(
                okHttpClientBuilder
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .build()
            )
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(HttpServiceApi::class.java)
    }

    @Singleton
    @Provides
    fun provideAppExecutors(): AppExecutors {
        return AppExecutors()
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
        httpServiceApi: HttpServiceApi
    ): AccountRepository {
        return AccountRepository(appExecutors, authTokenDao, userDao, httpServiceApi)
    }
}