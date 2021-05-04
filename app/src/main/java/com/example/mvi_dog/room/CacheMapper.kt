package com.example.mvi_dog.room

import com.example.mvi_dog.model.Dog
import com.example.mvi_dog.utils.EntityMapper
import javax.inject.Inject

class CacheMapper @Inject
constructor():
    EntityMapper<DogCacheEntity, Dog> {
    override fun mapFromEntity(entity: DogCacheEntity): Dog {
        return Dog(
            id = entity.id,
            url = entity.url,
            height = entity.height,
            width = entity.width
        )
    }

    override fun mapToEntity(domainModel: Dog): DogCacheEntity {
        return DogCacheEntity(
            id = domainModel.id,
            url = domainModel.url,
            height = domainModel.height,
            width = domainModel.width
        )
    }

    fun mapFromEntityList(entities: List<DogCacheEntity>): List<Dog>{
        return entities.map { mapFromEntity(it) }
    }
}
