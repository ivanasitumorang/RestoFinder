package com.azuka.base.utils


/**
 * Created by ivanaazuka on 01/10/20.
 * Android Engineer
 */

abstract class Mapper<Entity, Domain, Response> {
    abstract fun mapEntityToDomain(entity: Entity): Domain
    fun mapEntitiesToDomains(entities: List<Entity>): List<Domain> =
        entities.map { entity ->
            mapEntityToDomain(entity)
        }

    abstract fun mapDomainToEntity(dto: Domain): Entity

    abstract fun mapResponseToEntity(response: Response): Entity
    fun mapResponsesToEntities(responses: List<Response>): List<Entity> =
        responses.map { response ->
            mapResponseToEntity(response)
        }
}