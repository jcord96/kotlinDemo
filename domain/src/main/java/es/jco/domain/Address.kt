package es.jco.domain

data class Address (
    val id: Int?,
    val street: String?,
    val suite: String?,
    val city: String?,
    val zipcode: String?,
    val geo: Geo?
)