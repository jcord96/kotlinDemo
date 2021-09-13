package es.jco.demo.data.database.mapper

import es.jco.demo.data.database.entity.GeoEntity
import es.jco.domain.Geo as GeoDomain

fun GeoEntity.toDomain() = GeoDomain(
    this.geoId,
    this.lat,
    this.lng
)

fun GeoDomain.toEntity(addressId: Long?) = GeoEntity(
    this.id,
    this.lat,
    this.lng,
    addressId
)
