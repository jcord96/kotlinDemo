package es.jco.domain

data class User (
    var id: Long?,
    var name: String?,
    var username: String?,
    var email: String?,
    var address: Address?,
    var phone: String?,
    var website: String?,
    var company: Company?
)