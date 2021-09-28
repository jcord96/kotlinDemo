package es.jco.demo.data.server.mapper

import es.jco.demo.data.server.dto.GeoDTO
import es.jco.domain.Geo as GeoDomain

fun GeoDTO.toDomain() = GeoDomain(
    null,
    this.lat?.let { if(it.isEmpty() || it.contains("null")) null else it.toDouble() },
    this.lng?.let { if(it.isEmpty() || it.contains("null")) null else it.toDouble() }
)

fun GeoDomain.toDTO() = GeoDTO(
    this.lat.toString(),
    this.lng.toString()
)
