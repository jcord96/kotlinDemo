package es.jco.domain

data class User (
    var id: Long? = null,
    var name: String? = null,
    var username: String? = null,
    var email: String? = null,
    var address: Address? = Address(),
    var phone: String? = null,
    var website: String? = null,
    var company: Company? = Company()
)