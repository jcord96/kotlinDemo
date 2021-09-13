package es.jco.domain

data class Address (
    var id: Long?,
    var street: String?,
    var suite: String?,
    var city: String?,
    var zipcode: String?,
    var geo: Geo?
)