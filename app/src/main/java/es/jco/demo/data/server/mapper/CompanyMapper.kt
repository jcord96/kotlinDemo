package es.jco.demo.data.server.mapper

import es.jco.demo.data.server.dto.CompanyDTO
import es.jco.domain.Company as CompanyDomain

fun CompanyDTO.toDomain() = CompanyDomain(
    null,
    this.name,
    this.catchPhrase,
    this.bs
)

fun CompanyDomain.toDTO() = CompanyDTO(
    this.name?.trim(),
    this.catchPhrase?.trim(),
    this.bs?.trim()
)