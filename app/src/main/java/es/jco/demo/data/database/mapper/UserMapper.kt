package es.jco.demo.data.database.mapper

import es.jco.domain.User as UserDomain
import es.jco.demo.data.database.entity.User as UserEntity

fun UserEntity.toDomain() = UserDomain(
    this.id,
    this.name,
    this.username,
    this.email,
    this.address?.toDomain(),
    this.phone,
    this.website,
    this.company?.toDomain()
)

fun UserDomain.toEntity() = UserEntity(
    this.id,
    this.name,
    this.username,
    this.email,
    this.address?.toEntity(),
    this.phone,
    this.website,
    this.company?.toEntity()
)

