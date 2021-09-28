package es.jco.demo.data.server.mapper

import es.jco.demo.data.server.dto.UserDTO
import es.jco.domain.User as UserDomain

fun UserDTO.toDomain() = UserDomain(
    this.id,
    this.name,
    this.username,
    this.email,
    this.addressDTO?.toDomain(),
    this.phone,
    this.website,
    this.companyDTO?.toDomain()
)

fun UserDomain.toDTO() = UserDTO(
        this.id,
        this.name?.trim(),
        this.username?.trim(),
        this.email?.trim(),
        this.address?.toDTO(),
        this.phone?.trim(),
        this.website?.trim(),
        this.company?.toDTO()
)


