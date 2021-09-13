package es.jco.demo.data.database.mapper

import es.jco.demo.data.database.entity.CompanyEntity
import es.jco.domain.Company as CompanyDomain

fun CompanyEntity.toDomain() = CompanyDomain(
    this.companyId,
    this.name,
    this.catchPhrase,
    this.bs
)

fun CompanyDomain.toEntity(userId: Long?) = CompanyEntity(
    this.id,
    this.name,
    this.catchPhrase,
    this.bs,
    userId
)