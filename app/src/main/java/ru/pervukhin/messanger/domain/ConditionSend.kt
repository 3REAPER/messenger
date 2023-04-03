package ru.pervukhin.messanger.domain

class ConditionSend {
    var id: Int = 0
        private set
    var profile: Profile
    var condition: Int

    companion object {
        const val CONDITION_CREATE = 0
        const val CONDITION_SEND = 1
        const val CONDITION_READ = 2
    }

    constructor(profile: Profile, condition: Int) {
        this.profile = profile
        this.condition = condition
    }

    constructor(id: Int, profile: Profile, condition: Int) {
        this.id = id
        this.profile = profile
        this.condition = condition
    }

}