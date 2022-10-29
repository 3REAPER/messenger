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
}
