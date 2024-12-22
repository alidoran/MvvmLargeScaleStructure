package ir.dorantech.repository

import ir.dorantech.domain.model.User
import ir.dorantech.domain.repository.UserRepository
import ir.dorantech.domain.result.DataError
import ir.dorantech.domain.result.DataResult
import ir.dorantech.local.dao.UserDao
import ir.dorantech.mapper.toDomainModel
import ir.dorantech.mapper.toEntity
import ir.dorantech.domain.model.DataSource
import ir.dorantech.remote.api.UserService
import ir.dorantech.remote.dto.UserRequest
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService,
    private val userDao: UserDao
) : UserRepository {

    override suspend fun getUser(
        userId: String,
        dataSource: DataSource,
    ): DataResult<User> {
        return when (dataSource) {
            DataSource.Combination -> getCombinationUser(userId)
            DataSource.Local -> getLocalUser(userId)
            DataSource.Remote -> getRemoteUser(userId, false)
        }
    }

    private suspend fun getCombinationUser(userId: String): DataResult<User> {
        val localUser = userDao.getUser(userId)
        return if (localUser != null) {
            DataResult.Success(localUser.toDomainModel())
        } else getRemoteUser(userId, true)
    }

    private suspend fun getLocalUser(userId: String): DataResult<User> {
        val localUser = userDao.getUser(userId)
        return if (localUser != null) {
            DataResult.Success(localUser.toDomainModel())
        } else DataResult.Error(DataError.NotFound(Throwable("No data")))
    }

    private suspend fun getRemoteUser(
        userId: String,
        isCombination: Boolean,
    ): DataResult<User> {
        return try {
            val response = userService.getUser(UserRequest(userId))
            if (response.isSuccessful) {
                val userDto = response.body()
                userDto?.let {
                    val user = userDto.toDomainModel()
                    if (isCombination) userDao.insertUser(user.toEntity())
                    DataResult.Success(user)
                } ?: DataResult.Error(DataError.NotFound(Throwable("No data")))
            } else DataResult.Error(DataError.Unknown(Throwable(response.message())))
        } catch (e: Exception) {
            DataResult.Error(DataError.SeverDown(Throwable(e.message ?: "An error occurred")))
        }
    }
}