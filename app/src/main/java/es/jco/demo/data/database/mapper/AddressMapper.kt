package es.jco.demo.data.database.mapper

import es.jco.domain.Address as AddressDomain
import es.jco.demo.data.database.entity.Address as AddressEntity

fun AddressEntity.toDomain() = AddressDomain(
    this.id,
    this.street,
    this.suite,
    this.city,
    this.zipcode,
    this.geo?.toDomain()
)

fun AddressDomain.toEntity() = AddressEntity(
    this.id,
    this.street,
    this.suite,
    this.city,
    this.zipcode,
    this.geo?.toEntity()
)