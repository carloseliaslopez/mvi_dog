package com.example.mvi_dog.utils

interface EntityMapper <Entity, DomainModel>{
    fun mapFromEntity(entity: Entity): DomainModel
    fun mapToEntity(domainModel: DomainModel): Entity
}