package es.jco.demo.data.server.mapper

import es.jco.demo.data.server.dto.GeoDTO
import es.jco.domain.Geo as GeoDomain

fun GeoDTO.toDomain() = GeoDomain(
    null,
    this.lat?.toDouble(),
    this.lng?.toDouble()
)

fun GeoDomain.toDTO() = GeoDTO(
    this.lat.toString(),
    this.lng.toString()
)
