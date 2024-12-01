package ir.dorantech.di.network_module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.dorantech.remote.api.UserService
import ir.dorantech.remote.network.RetrofitBuilder
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideUserService(): UserService {
        return RetrofitBuilder.createService(UserService::class.java)
    }
}