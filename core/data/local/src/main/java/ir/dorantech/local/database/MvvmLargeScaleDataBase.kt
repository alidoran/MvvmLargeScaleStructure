package ir.dorantech.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.dorantech.local.dao.UserDao
import ir.dorantech.local.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class MvvmLargeScaleDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
}