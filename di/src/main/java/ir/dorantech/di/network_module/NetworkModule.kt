package ir.dorantech.di.network_module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.dorantech.local.dao.UserDao
import ir.dorantech.local.database.MvvmLargeScaleDataBase
import ir.dorantech.remote.api.UserService
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
    fun provideUserService(): UserService {
        return RetrofitBuilder.createService(UserService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserDao(database: MvvmLargeScaleDataBase): UserDao {
        return database.userDao()
    }
}