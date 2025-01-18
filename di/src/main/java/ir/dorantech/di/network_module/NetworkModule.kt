package ir.dorantech.di.network_module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.dorantech.local.dao.UserDao
import ir.dorantech.local.database.MvvmLargeScaleDataBase
import ir.dorantech.remote.api.UserDataSource
import ir.dorantech.remote.network.RetrofitBuilder
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideUserService(): UserDataSource {
        return RetrofitBuilder.createService(UserDataSource::class.java)
    }

    @Provides
    @Singleton
    fun provideUserDao(database: MvvmLargeScaleDataBase): UserDao {
        return database.userDao()
    }
}