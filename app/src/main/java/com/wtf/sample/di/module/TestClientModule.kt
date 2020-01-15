package com.wtf.sample.di.module

import android.app.Application
import androidx.room.Room
import com.wtf.sample.BuildConfig
import com.wtf.sample.R
import com.wtf.sample.api.HttpServiceApi
import com.wtf.sample.config.BASE_URL
import com.wtf.sample.db.AppDatabase
import com.wtf.sample.http.LiveDataCallAdapterFactory
import com.wtf.yggd.di.scope.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named


/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-10 下午5:11
 */
@Module
class TestClientModule {
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
    @Named("passwordWarning")
    fun provideString(app: Application): String {
        return app.getString(R.string.password_warning)
    }

    @AppScope
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
        okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
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
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(HttpServiceApi::class.java)
    }

//    @AppScope
//    @Provides
//    fun provideAuthTokenDao(appDatabase: AppDatabase): AuthTokenDao {
//        return appDatabase.authTokenDao()
//    }
//
//    @AppScope
//    @Provides
//    fun provideUserDao(appDatabase: AppDatabase): UserDao {
//        return appDatabase.userDao()
//    }
//
//    @AppScope
//    @Provides
//    fun provideAccountRepository(
//        appExecutors: AppExecutors,
//        authTokenDao: AuthTokenDao,
//        userDao: UserDao,
//        httpServiceApi: HttpServiceApi
//    ): AccountRepository {
//        return AccountRepository(appExecutors, authTokenDao, userDao, httpServiceApi)
//    }
}