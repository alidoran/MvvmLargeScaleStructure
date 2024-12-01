package ir.dorantech.mapper

import ir.dorantech.domain.model.User
import ir.dorantech.remote.dto.UserDto

fun UserDto.toDomainModel() = User(
    id = this.id,
    name = this.name,
    email = this.email
)