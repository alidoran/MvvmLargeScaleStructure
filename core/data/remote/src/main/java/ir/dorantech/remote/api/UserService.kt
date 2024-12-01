package ir.dorantech.remote.api

import ir.dorantech.remote.dto.UserDto
import ir.dorantech.remote.dto.UserRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("user")
    suspend fun getUser(
        @Body user: UserRequest,
    ): Response<UserDto>
}