package ir.dorantech.remote.dto

data class UserDto(
    val id: String,
    val name: String,
    val email: String
)

data class UserRequest(val id: String)