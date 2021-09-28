package es.jco.demo.data.server.mapper

import es.jco.demo.data.server.dto.AddressDTO
import es.jco.domain.Address as AddressDomain

fun AddressDTO.toDomain() = AddressDomain(
    null,
    this.street,
    this.suite,
    this.city,
    this.zipcode,
    this.geoDTO?.toDomain()
)

fun AddressDomain.toDTO() = AddressDTO(
    this.street?.trim(),
    this.suite?.trim(),
    this.city?.trim(),
    this.zipcode?.trim(),
    this.geo?.toDTO()
)