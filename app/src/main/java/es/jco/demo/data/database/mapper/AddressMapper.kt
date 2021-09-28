package es.jco.demo.data.database.mapper

import es.jco.demo.data.database.entity.AddressEntity as AddressParentEntity
import es.jco.demo.data.database.entity.AddressParentEntity as AddressEntity
import es.jco.domain.Address as AddressDomain

fun AddressEntity.toDomain() = AddressDomain(
    this.address?.addressId,
    this.address?.street,
    this.address?.suite,
    this.address?.city,
    this.address?.zipcode,
    this.geo?.toDomain()
    )

fun AddressDomain.toEntity(userId: Long?) = AddressEntity(
    AddressParentEntity(
        this.id,
        this.street?.trim(),
        this.suite?.trim(),
        this.city?.trim(),
        this.zipcode?.trim(),
        userId
    ),
    this.geo?.toEntity(this.id)
)