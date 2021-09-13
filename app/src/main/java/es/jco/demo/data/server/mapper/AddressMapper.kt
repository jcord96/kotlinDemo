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
    this.street,
    this.suite,
    this.city,
    this.zipcode,
    this.geo?.toDTO()
)