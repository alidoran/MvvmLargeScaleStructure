package ir.dorantech.mapper

import ir.dorantech.domain.model.User
import ir.dorantech.local.entities.UserEntity

fun User.toEntity(): UserEntity = UserEntity(id, name, email)