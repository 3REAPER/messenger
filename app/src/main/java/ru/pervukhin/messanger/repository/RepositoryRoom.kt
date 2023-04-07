package ru.pervukhin.messanger.repository

import ru.pervukhin.messanger.App
import ru.pervukhin.messanger.data.room.EntityMapper
import ru.pervukhin.messanger.data.room.ProfileDao
import ru.pervukhin.messanger.domain.Profile
import javax.inject.Inject

class RepositoryRoom {
    @Inject
    lateinit var profileDao: ProfileDao

    init {
        App.appComponent.inject(this)
    }

    suspend fun getAllProfile(): List<Profile>{
        var result: List<Profile> = listOf()
        profileDao.getAll().forEach {
            result = result.plus(EntityMapper.toDomainObject(it))
        }
        return result
    }

    suspend fun getById(id: Int): Profile{
        val profileEntity = profileDao.getById(id)
        val profile = if (profileEntity != null){
            EntityMapper.toDomainObject(profileEntity)
        }else{
            Profile(0,"","","","")
        }
        return profile
    }

    suspend fun insert(profile: Profile){
        val lol = getById(profile.id).id
        print(lol)
        if (getById(profile.id).id == 0){
            profileDao.insert(EntityMapper.toEntity(profile))
        }
    }
}