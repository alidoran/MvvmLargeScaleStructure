package ir.dorantech.repository

import ir.dorantech.domain.model.User
import ir.dorantech.domain.repository.UserRepository
import ir.dorantech.domain.result.DataError
import ir.dorantech.domain.result.DataResult
import ir.dorantech.mapper.toDomainModel
import ir.dorantech.remote.api.UserService
import ir.dorantech.remote.dto.UserRequest
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
) : UserRepository {

    override suspend fun getUser(userId: String): DataResult<User> {
        return try {
            val response = userService.getUser(UserRequest(userId))
            if (response.isSuccessful) {
                response.body()?.toDomainModel()?.let { DataResult.Success(it) }
                    ?: DataResult.Error(DataError.NotFound(Throwable("No data")))
            } else {
                DataResult.Error(DataError.Unknown(Throwable(response.message())))
            }
        } catch (e: Exception) {
            DataResult.Error(DataError.SeverDown(Throwable(e.message ?: "An error occurred")))
        }
    }
}