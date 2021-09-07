package es.jco.demo.data.database.mapper

import es.jco.domain.Company as CompanyDomain
import es.jco.demo.data.database.entity.Company as CompanyEntity

fun CompanyEntity.toDomain() = CompanyDomain(
    this.id,
    this.name,
    this.catchPhrase,
    this.bs
)

fun CompanyDomain.toEntity() = CompanyEntity(
    this.id,
    this.name,
    this.catchPhrase,
    this.bs
)