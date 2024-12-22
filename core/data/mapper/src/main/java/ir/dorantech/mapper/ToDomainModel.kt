package ir.dorantech.mapper

import ir.dorantech.domain.model.User
import ir.dorantech.local.entities.UserEntity
import ir.dorantech.remote.dto.UserDto

fun UserDto.toDomainModel() = User(    id = id,    name = name,    email = email)
fun UserEntity.toDomainModel(): User = User(id, name, email)