package ru.pervukhin.messanger.domain

class Profile {
    var id = 0
        private set
    var name: String
    var login: String
    var number: String
    var password: String

    constructor(id: Int, name: String, login: String, number: String, password: String) {
        this.id = id
        this.name = name
        this.login = login
        this.password = password
        this.number = number
    }

    constructor(name: String, login: String, number: String, password: String) {
        this.name = name
        this.login = login
        this.password = password
        this.number = number
    }

    override fun equals(other: Any?): Boolean {
        return other is Profile && (id == other.id && login == other.login && password == other.password && number == other.number)
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + login.hashCode()
        result = 31 * result + number.hashCode()
        result = 31 * result + password.hashCode()
        return result
    }
}
