package ir.dorantech.di.database_module

import android.content.Context
import androidx.room.Room

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.dorantech.local.database.MvvmLargeScaleDataBase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        context: Context
    ): MvvmLargeScaleDataBase {
        return Room.databaseBuilder(
            context,
            MvvmLargeScaleDataBase::class.java,
            MvvmLargeScaleDataBase::class.simpleName,
        ).build()
    }
}