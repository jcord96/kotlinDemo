package es.jco.demo.data.database.mapper

import es.jco.domain.Geo as GeoDomain
import es.jco.demo.data.database.entity.Geo as GeoEntity

fun GeoEntity.toDomain() = GeoDomain(
    this.id,
    this.lat,
    this.lng
)

fun GeoDomain.toEntity() = GeoEntity(
    this.id,
    this.lat,
    this.lng
)
