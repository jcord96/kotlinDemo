package es.jco.domain

data class Address (
    var id: Long? = null,
    var street: String? = null,
    var suite: String? = null,
    var city: String? = null,
    var zipcode: String? = null,
    var geo: Geo? = Geo()
)