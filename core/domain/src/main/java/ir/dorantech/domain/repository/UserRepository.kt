package ir.dorantech.domain.repository

import ir.dorantech.domain.model.User
import ir.dorantech.domain.result.DataResult


interface UserRepository {
    suspend fun getUser(userId: String): DataResult<User>
}