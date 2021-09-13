package es.jco.demo.data.database.mapper

import es.jco.demo.data.database.entity.UserEntity as UseParentEntity
import es.jco.demo.data.database.entity.UserParentEntity as UserEntity
import es.jco.domain.User as UserDomain

fun UserEntity.toDomain() = UserDomain(
    this.user.userId,
    this.user.name,
    this.user.username,
    this.user.email,
    this.address?.toDomain(),
    this.user.phone,
    this.user.website,
    this.company?.toDomain()
)

fun UserDomain.toEntity() = UserEntity(
    UseParentEntity(
        this.id,
        this.name,
        this.username,
        this.email,
        this.phone,
        this.website
    ),
    this.address?.toEntity(this.id),
    this.company?.toEntity(this.id)
)

