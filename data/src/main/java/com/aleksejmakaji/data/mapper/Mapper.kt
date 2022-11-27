package com.aleksejmakaji.data.mapper

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 *
 * @param <E> the entity model input type
 * @param <D> the domain model input type
 */
interface Mapper<E, D> {

    fun mapFromEntity(type: E): D

    fun mapToEntity(type: D): E

    fun mapFromEntity(types: List<E>): List<D> =
        types.map { type ->
            mapFromEntity(type)
        }

    fun mapFromDomain(types: List<D>): List<E> =
        types.map { type ->
            mapToEntity(type)
        }
}
