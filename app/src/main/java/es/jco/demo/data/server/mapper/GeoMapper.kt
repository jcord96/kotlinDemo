package es.jco.demo.data.server.mapper

import es.jco.demo.data.server.dto.GeoDTO
import es.jco.domain.Geo as GeoDomain

fun GeoDTO.toDomain() = GeoDomain(
    null,
    this.lat,
    this.lng
)

fun GeoDomain.toDTO() = GeoDTO(
    this.lat,
    this.lng
)
