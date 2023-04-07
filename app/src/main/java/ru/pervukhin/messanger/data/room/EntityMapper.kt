package ru.pervukhin.messanger.data.room

import ru.pervukhin.messanger.domain.Profile

class EntityMapper {

    companion object{
        fun toDomainObject(profileEntity: ProfileEntity): Profile{
            return Profile(
                profileEntity.id,
                profileEntity.name,
                "",
                profileEntity.number,
                ""
            )
        }

        fun toEntity(profile: Profile): ProfileEntity{
            return ProfileEntity(
                profile.id,
                profile.name,
                profile.number
            )
        }
    }
}